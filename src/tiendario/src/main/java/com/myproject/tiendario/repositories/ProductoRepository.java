package com.myproject.tiendario.repositories;

import com.myproject.tiendario.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoria_NombreContainingIgnoreCase(String nombreCategoria);
}