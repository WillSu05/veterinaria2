package com.example.veterinaria.Service.Impl;

import com.example.veterinaria.Entity.HistoriaClinica;
import com.example.veterinaria.Entity.Mascota;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Repository.HistoriaClinicaRepository;
import com.example.veterinaria.Repository.MascotaRepository;
import com.example.veterinaria.Service.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HistoriaClinica> listarTodos() {
        return historiaClinicaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public HistoriaClinica buscarPorId(Long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("Historia Clinica no Encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public HistoriaClinica crear(HistoriaClinica historia, Long mascotaID) {
        Mascota mascota = mascotaRepository.findById(mascotaID)
                .orElseThrow(()-> new RecursoNoEncontrado("Mascota no encontrada con ID: " + mascotaID));
        if (historiaClinicaRepository.existsById(mascotaID)){
            throw new RecursoNoEncontrado("Mascota con ID: "+mascotaID + "Ya cuenta con historial asignado.");
        }
        if (historia.getFechaApertura() == null){
            historia.setFechaApertura(LocalDate.now());
        }
        historia.setMascota(mascota);
        return historiaClinicaRepository.save(historia);

    }

    @Override
    @Transactional
    public HistoriaClinica actualizar(Long id, HistoriaClinica historia) {
        HistoriaClinica historiaExistente = buscarPorId(id);

        historiaExistente.setAntecedentes(historia.getAntecedentes());
        historiaExistente.setObservaciones(historia.getObservaciones());

        return historiaClinicaRepository.save(historiaExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!historiaClinicaRepository.existsById(id)){
            throw new RecursoNoEncontrado("No se puedo eliminar. Historia clinica no encontrada con ID: "+ id);
        }
        historiaClinicaRepository.deleteById(id);

    }
}
