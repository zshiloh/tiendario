package com.myproject.tiendario.controllers;

import com.myproject.tiendario.services.UsuarioService;
import com.myproject.tiendario.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de Usuarios", description = "APIs para administrar usuarios del sistema. Solo accesible por administradores")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene la lista completa de usuarios registrados en el sistema con sus roles asignados",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Usuario> getAll() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Obtener usuario por ID",
        description = "Busca y retorna un usuario específico por su identificador único",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Crear nuevo usuario",
        description = "Registra un nuevo usuario en el sistema con rol asignado. La contraseña se encripta automáticamente",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Actualizar usuario",
        description = "Modifica los datos de un usuario existente incluyendo nombre, correo, contraseña y rol",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema. Verificar que no tenga movimientos de stock asociados antes de eliminar",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/rol/{rol}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Buscar usuarios por rol",
        description = "Obtiene todos los usuarios que tienen un rol específico (ADMINISTRADOR, ENCARGADO_INVENTARIO, VENDEDOR)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Usuario> buscarPorRol(@PathVariable String rol) {
        return usuarioService.buscarPorRol(rol);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
        summary = "Buscar usuarios por nombre",
        description = "Busca usuarios que contengan el texto especificado en su nombre (búsqueda parcial)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Usuario> buscarPorNombre(@PathVariable String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }
}