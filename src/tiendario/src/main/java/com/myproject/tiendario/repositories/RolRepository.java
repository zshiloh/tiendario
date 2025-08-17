package com.myproject.tiendario.repositories;

import com.myproject.tiendario.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {

    List<Rol> findAllByOrderByNombreAsc();

    List<Rol> findByNombreContainingIgnoreCase(String nombre);
}