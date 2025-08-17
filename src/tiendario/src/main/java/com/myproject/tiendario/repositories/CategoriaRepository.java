package com.myproject.tiendario.repositories;

import com.myproject.tiendario.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByOrderByNombreAsc();

    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}