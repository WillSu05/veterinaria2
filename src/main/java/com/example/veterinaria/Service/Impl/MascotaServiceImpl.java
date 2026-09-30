package com.example.veterinaria.Service.Impl;

import com.example.veterinaria.Entity.Mascota;
import com.example.veterinaria.Entity.Propietario;
import com.example.veterinaria.Entity.Veterinario;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Repository.HistoriaClinicaRepository;
import com.example.veterinaria.Repository.MascotaRepository;
import com.example.veterinaria.Repository.PropietarioRepository;
import com.example.veterinaria.Repository.VeterinarioRepository;
import com.example.veterinaria.Service.MascotaService;
import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService{
    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("Mascota no Encontrada"+id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> listarTodos() {
        return mascotaRepository.findAll();
    }

    @Override
    @Transactional
    public Mascota guardad(Mascota mascota, Long propietarioId) {
        Propietario propietario = propietarioRepository.findById(propietarioId)
                .orElseThrow(()-> new RecursoNoEncontrado("Propietario no Encontrado con ID: "+ propietarioId));
        mascota.setPropietario(propietario);
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional
    public Mascota actualizar(Long id, Mascota mascota) {
        Mascota mascotaExistente = buscarPorId(id);
        mascotaExistente.setNombre(mascota.getNombre());
        mascotaExistente.setEspecie(mascota.getEspecie());
        mascotaExistente.setRaza(mascota.getRaza());
        mascotaExistente.setEdad(mascota.getEdad());
        mascotaExistente.setHistoriaClinica(mascota.getHistoriaClinica());
        mascotaExistente.setVeterinarios(mascota.getVeterinarios());
        return mascotaRepository.save(mascotaExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!mascotaRepository.existsById(id)){
            throw new RecursoNoEncontrado("Mascota no Encontrada");
        }
        mascotaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Mascota asignarVeterinario(Long mascotaId, Long veterinarioId) {
        Mascota mascota = buscarPorId(mascotaId);
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(()-> new RecursoNoEncontrado("Veterinario no Encontrado con ID: "+ veterinarioId));
        mascota.getVeterinarios().add(veterinario);
        return mascotaRepository.save(mascota);
    }
}
