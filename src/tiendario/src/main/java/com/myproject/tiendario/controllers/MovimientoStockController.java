package com.myproject.tiendario.controllers;

import com.myproject.tiendario.models.MovimientoStock;
import com.myproject.tiendario.services.MovimientoStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Gestión de Inventario", description = "APIs para registrar y consultar movimientos de stock (entradas y salidas)")
public class MovimientoStockController {

    @Autowired
    private MovimientoStockService movimientoStockService;

    @GetMapping
    @Operation(
        summary = "Listar todos los movimientos",
        description = "Obtiene el historial completo de movimientos de stock del inventario",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<MovimientoStock> getAll() {
        return movimientoStockService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener movimiento por ID",
        description = "Busca y retorna un movimiento de stock específico por su identificador único",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<MovimientoStock> obtenerPorId(@PathVariable Long id) {
        return movimientoStockService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar movimiento de stock",
        description = "Registra un nuevo movimiento de entrada o salida de inventario. Actualiza automáticamente el stock del producto",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public MovimientoStock registrarMovimiento(@RequestBody MovimientoStock movimiento) {
        return movimientoStockService.guardar(movimiento);
    }

    @GetMapping("/usuario/{nombreUsuario}")
    @Operation(
        summary = "Buscar movimientos por usuario",
        description = "Obtiene todos los movimientos de stock realizados por un usuario específico",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<MovimientoStock> buscarPorUsuario(@PathVariable String nombreUsuario) {
        return movimientoStockService.buscarPorUsuario(nombreUsuario);
    }

    @GetMapping("/producto/{nombreProducto}")
    @Operation(
        summary = "Buscar movimientos por producto",
        description = "Obtiene el historial completo de movimientos de un producto específico",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public List<MovimientoStock> buscarPorProducto(@PathVariable String nombreProducto) {
        return movimientoStockService.buscarPorProducto(nombreProducto);
    }
}