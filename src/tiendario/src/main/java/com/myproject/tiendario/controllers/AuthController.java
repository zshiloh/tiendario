package com.myproject.tiendario.controllers;

import com.myproject.tiendario.configurations.CustomUserDetails;
import com.myproject.tiendario.configurations.JwtUtil;
import com.myproject.tiendario.dto.AuthRequest;
import com.myproject.tiendario.dto.AuthResponse;
import com.myproject.tiendario.models.Usuario;
import com.myproject.tiendario.models.Rol;
import com.myproject.tiendario.services.UsuarioService;
import com.myproject.tiendario.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Autenticación", description = "APIs para login, registro y validación de tokens JWT")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @PostMapping("/login")
    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica un usuario con nombre y contraseña, retorna un token JWT válido para acceder a endpoints protegidos"
    )
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getNombre(),
                            authRequest.getContrasena()));

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Usuario usuario = userDetails.getUsuario();

            String token = jwtUtil.generateToken(
                    usuario.getNombre(),
                    usuario.getRol().getNombre());

            AuthResponse authResponse = new AuthResponse(
                    token,
                    usuario.getNombre(),
                    usuario.getRol().getNombre());

            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Credenciales inválidas");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error en el servidor: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    @Operation(
        summary = "Registrar nuevo usuario",
        description = "Crea una nueva cuenta de usuario con rol de vendedor por defecto. Valida que el nombre de usuario y correo sean únicos"
    )
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.existePorNombre(usuario.getNombre())) {
                return ResponseEntity.badRequest().body("El usuario ya existe");
            }

            if (usuarioService.existePorCorreo(usuario.getCorreo())) {
                return ResponseEntity.badRequest().body("El correo ya está registrado");
            }

            Rol rolVendedor = rolService.buscarPorId(3L).orElse(null);
            if (rolVendedor == null) {
                return ResponseEntity.internalServerError().body("Error: Rol VENDEDOR no encontrado");
            }
            usuario.setRol(rolVendedor);

            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return ResponseEntity.ok("Registrado exitosamente con ID: " + nuevoUsuario.getId());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @PostMapping("/validate")
    @Operation(
        summary = "Validar token JWT",
        description = "Endpoint público que verifica si un token JWT es válido y no ha expirado. Requiere header Authorization con Bearer token"
    )
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);

                if (jwtUtil.validateToken(token, username)) {
                    return ResponseEntity.ok(new AuthResponse(token, username, role));
                }
            }
            return ResponseEntity.badRequest().body("Token inválido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al validar token: " + e.getMessage());
        }
    }
}