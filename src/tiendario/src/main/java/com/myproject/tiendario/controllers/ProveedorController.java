package com.myproject.tiendario.controllers;

import com.myproject.tiendario.services.ProveedorService;
import com.myproject.tiendario.models.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Gestión de Proveedores", description = "APIs para administrar proveedores y sus datos de contacto")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(
        summary = "Listar todos los proveedores",
        description = "Obtiene la lista completa de proveedores registrados en el sistema",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Proveedor> getAll() {
        return proveedorService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener proveedor por ID",
        description = "Busca y retorna un proveedor específico por su identificador único",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        return proveedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Crear nuevo proveedor",
        description = "Registra un nuevo proveedor con su información de contacto y dirección",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.guardar(proveedor);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar proveedor",
        description = "Modifica los datos de un proveedor existente incluyendo nombre, contacto y dirección",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.buscarPorId(id)
                .map(p -> {
                    p.setNombre(proveedor.getNombre());
                    p.setContacto(proveedor.getContacto());
                    p.setDireccion(proveedor.getDireccion());
                    return ResponseEntity.ok(proveedorService.guardar(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar proveedor",
        description = "Elimina un proveedor del sistema. Verificar que no tenga productos asociados antes de eliminar",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{nombre}")
    @Operation(
        summary = "Buscar proveedores por nombre",
        description = "Busca proveedores que contengan el texto especificado en su nombre (búsqueda parcial)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Proveedor> buscarPorNombre(@PathVariable String nombre) {
        return proveedorService.buscarPorNombre(nombre);
    }
    
    @GetMapping("/ordenados")
    @Operation(
        summary = "Listar proveedores ordenados",
        description = "Obtiene todos los proveedores ordenados alfabéticamente por nombre",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Proveedor> listarOrdenados() {
        return proveedorService.listarOrdenadosPorNombre();
    }
}