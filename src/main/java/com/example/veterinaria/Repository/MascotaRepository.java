package com.example.veterinaria.Repository;

import com.example.veterinaria.Entity.Mascota;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByEspecieIgnoreCase(String especie);
    @EntityGraph(attributePaths = {"propietario"})
    List<Mascota> findByPropietarioId(Long propietarioId);

}
