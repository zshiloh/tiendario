package com.myproject.tiendario.repositories;

import com.myproject.tiendario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByRol_Nombre(String nombre);

    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    Optional<Usuario> findByNombre(String nombre);

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByNombre(String nombre);

    boolean existsByCorreo(String correo);
}