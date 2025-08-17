package com.myproject.tiendario.controllers;

import com.myproject.tiendario.services.RolService;
import com.myproject.tiendario.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Gestión de Roles", description = "APIs para administrar roles y permisos del sistema. Solo accesible por administradores")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(
        summary = "Listar todos los roles",
        description = "Obtiene la lista completa de roles disponibles en el sistema (ADMINISTRADOR, ENCARGADO_INVENTARIO, VENDEDOR)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Rol> getAll() {
        return rolService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener rol por ID",
        description = "Busca y retorna un rol específico por su identificador único",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Rol> getById(@PathVariable Long id) {
        return rolService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Crear nuevo rol",
        description = "Registra un nuevo rol en el sistema con nombre y descripción de permisos",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public Rol crearRol(@RequestBody Rol rol) {
        return rolService.guardar(rol);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar rol",
        description = "Modifica los datos de un rol existente incluyendo nombre y descripción de permisos",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        return rolService.buscarPorId(id)
                .map(r -> {
                    r.setNombre(rol.getNombre());
                    r.setDescripcion(rol.getDescripcion());
                    return ResponseEntity.ok(rolService.guardar(r));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar rol",
        description = "Elimina un rol del sistema. Verificar que no tenga usuarios asignados antes de eliminar",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ordenados")
    @Operation(
        summary = "Listar roles ordenados",
        description = "Obtiene todos los roles ordenados alfabéticamente por nombre",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Rol> listarOrdenados() {
        return rolService.listarOrdenadosPorNombre();
    }
    
    @GetMapping("/buscar/{nombre}")
    @Operation(
        summary = "Buscar roles por nombre",
        description = "Busca roles que contengan el texto especificado en su nombre (búsqueda parcial)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Rol> buscarPorNombre(@PathVariable String nombre) {
        return rolService.buscarPorNombre(nombre);
    }
}