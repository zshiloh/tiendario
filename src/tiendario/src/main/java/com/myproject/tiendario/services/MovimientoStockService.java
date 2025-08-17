package com.myproject.tiendario.services;

import com.myproject.tiendario.models.MovimientoStock;
import com.myproject.tiendario.models.Producto;
import com.myproject.tiendario.repositories.MovimientoStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoStockService {

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @Autowired
    private ProductoService productoService;

    public List<MovimientoStock> listarTodos() {
        return movimientoStockRepository.findAll();
    }

    public Optional<MovimientoStock> buscarPorId(Long id) {
        return movimientoStockRepository.findById(id);
    }

    @Transactional
    public MovimientoStock guardar(MovimientoStock movimientoStock) {

        MovimientoStock movimientoGuardado = movimientoStockRepository.save(movimientoStock);

        Optional<Producto> productoOpt = productoService.buscarPorId(movimientoStock.getProducto().getId());

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            int stockActual = producto.getStock();

            if ("ENTRADA".equalsIgnoreCase(movimientoStock.getTipo())) {

                producto.setStock(stockActual + movimientoStock.getCantidad());
            } else if ("SALIDA".equalsIgnoreCase(movimientoStock.getTipo())) {

                int nuevoStock = stockActual - movimientoStock.getCantidad();

                if (nuevoStock < 0) {
                    throw new RuntimeException("Stock insuficiente. Stock actual: " + stockActual +
                            ", Cantidad solicitada: " + movimientoStock.getCantidad());
                }

                producto.setStock(nuevoStock);
            }

            productoService.guardar(producto);
        }

        return movimientoGuardado;
    }

    public List<MovimientoStock> buscarPorUsuario(String nombreUsuario) {
        return movimientoStockRepository.findByUsuario_Nombre(nombreUsuario);
    }

    public List<MovimientoStock> buscarPorProducto(String nombreProducto) {
        return movimientoStockRepository.findByProducto_Nombre(nombreProducto);
    }
}