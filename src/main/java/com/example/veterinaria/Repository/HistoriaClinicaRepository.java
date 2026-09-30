package com.example.veterinaria.Repository;

import com.example.veterinaria.Entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository <HistoriaClinica, Long> {
    Optional<HistoriaClinica> findByMascotaId (Long id);
    List<HistoriaClinica> findByFechaApertura(LocalDate fechaApertura);
    List<HistoriaClinica> findByFechaAperturaBetween (LocalDate fechaInicio, LocalDate fechaFin);
    boolean existsByMascotaId(Long mascotaId);

}
