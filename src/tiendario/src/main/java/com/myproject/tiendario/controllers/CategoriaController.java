package com.myproject.tiendario.controllers;

import com.myproject.tiendario.models.Categoria;
import com.myproject.tiendario.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Gestión de Categorías", description = "APIs para administrar categorías de productos del inventario")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(
        summary = "Listar todas las categorías",
        description = "Obtiene la lista completa de categorías disponibles en el sistema",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Categoria> getAll() {
        return categoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener categoría por ID",
        description = "Busca y retorna una categoría específica por su identificador único",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Crear nueva categoría",
        description = "Registra una nueva categoría en el sistema. Requiere permisos de administrador o encargado de inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar categoría",
        description = "Modifica los datos de una categoría existente. Requiere permisos de administrador o encargado de inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.buscarPorId(id)
                .map(c -> {
                    c.setNombre(categoria.getNombre());
                    c.setDescripcion(categoria.getDescripcion());
                    return ResponseEntity.ok(categoriaService.guardar(c));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar categoría",
        description = "Elimina una categoría del sistema. Requiere permisos de administrador o encargado de inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ordenados")
    @Operation(
        summary = "Listar categorías ordenadas",
        description = "Obtiene todas las categorías ordenadas alfabéticamente por nombre",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Categoria> getCategoriasOrdenadas() {
        return categoriaService.listarOrdenadoPorNombre();
    }

    @GetMapping("/buscar/{nombre}")
    @Operation(
        summary = "Buscar categorías por nombre",
        description = "Busca categorías que contengan el texto especificado en su nombre (búsqueda parcial)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<Categoria> buscarCategoriasPorTexto(@PathVariable String nombre) {
        return categoriaService.buscarPorNombreContiene(nombre);
    }
}