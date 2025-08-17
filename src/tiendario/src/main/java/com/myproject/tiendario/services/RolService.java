package com.myproject.tiendario.services;

import com.myproject.tiendario.models.Rol;
import com.myproject.tiendario.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    public Optional<Rol> buscarPorId(Long id) {
        return rolRepository.findById(id);
    }

    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    public List<Rol> listarOrdenadosPorNombre() {
        return rolRepository.findAllByOrderByNombreAsc();
    }
    
    public List<Rol> buscarPorNombre(String nombre) {
        return rolRepository.findByNombreContainingIgnoreCase(nombre);
    }
}