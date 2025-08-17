package com.myproject.tiendario.repositories;

import com.myproject.tiendario.models.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Long> {

    List<MovimientoStock> findByUsuario_Nombre(String nombreUsuario);

    List<MovimientoStock> findByProducto_Nombre(String nombreProducto);
}