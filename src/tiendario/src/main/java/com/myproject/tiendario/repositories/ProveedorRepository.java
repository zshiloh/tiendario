package com.myproject.tiendario.repositories;

import com.myproject.tiendario.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);

    List<Proveedor> findAllByOrderByNombreAsc();
}