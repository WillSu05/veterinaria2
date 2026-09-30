package com.example.veterinaria.Repository;

import com.example.veterinaria.Entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional <Veterinario> findByTarjetaProfesional (String tarjetaProfesional);
    Optional <Veterinario> findByCorreo(String correo);
    List<Veterinario> findByEspecialidadIgnoreCase (String especialidad);
    List<Veterinario> findByNombreContainingIgnoreCase (String nombre);
    boolean existsByTarjetaProfesional(String tarjetaProfesional);
    boolean existsByCorreo (String correo);
}
