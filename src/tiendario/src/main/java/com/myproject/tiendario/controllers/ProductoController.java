package com.myproject.tiendario.controllers;

import com.myproject.tiendario.services.ProductoService;
import com.myproject.tiendario.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Productos", description = "APIs para administrar el catálogo de productos del inventario")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(
        summary = "Listar todos los productos",
        description = "Obtiene el catálogo completo de productos disponibles en el inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Producto> getAll() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener producto por ID",
        description = "Busca y retorna un producto específico por su identificador único",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Crear nuevo producto",
        description = "Registra un nuevo producto en el inventario con toda su información. Requiere permisos de administrador o encargado de inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar producto",
        description = "Modifica los datos de un producto existente incluyendo precio, stock, categoría y proveedor. Requiere permisos de administrador o encargado de inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.buscarPorId(id)
                .map(p -> {
                    p.setNombre(producto.getNombre());
                    p.setDescripcion(producto.getDescripcion());
                    p.setPrecio(producto.getPrecio());
                    p.setStock(producto.getStock());
                    p.setCategoria(producto.getCategoria());
                    p.setProveedor(producto.getProveedor());
                    return ResponseEntity.ok(productoService.guardar(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar producto",
        description = "Elimina un producto del catálogo de inventario. Requiere permisos de administrador o encargado de inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre/{nombre}")
    @Operation(
        summary = "Buscar productos por nombre",
        description = "Busca productos que contengan el texto especificado en su nombre (búsqueda parcial)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Producto> buscarPorNombre(@PathVariable String nombre) {
        return productoService.buscarPorNombre(nombre);
    }
    
    @GetMapping("/buscar/categoria/{categoria}")
    @Operation(
        summary = "Buscar productos por categoría",
        description = "Busca todos los productos que pertenezcan a una categoría específica",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Producto> buscarPorCategoria(@PathVariable String categoria) {
        return productoService.buscarPorCategoria(categoria);
    }
}