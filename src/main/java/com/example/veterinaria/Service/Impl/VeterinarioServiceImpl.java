package com.example.veterinaria.Service.Impl;

import com.example.veterinaria.Entity.Mascota;
import com.example.veterinaria.Entity.Veterinario;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Repository.MascotaRepository;
import com.example.veterinaria.Repository.VeterinarioRepository;
import com.example.veterinaria.Service.VeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioServiceImpl implements VeterinarioService {
    private final VeterinarioRepository veterinarioRepository;
    private final MascotaRepository mascotaRepository;
    @Override
    @Transactional
    public Veterinario guardar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("Veterinario no Encontrado con ID: " + id));
    }

    @Override
    @Transactional (readOnly = true)
    public Veterinario actualizar(Long id, Veterinario veterinario) {
        Veterinario veterinarioExistente = buscarPorId(id);

        veterinarioExistente.setNombre(veterinario.getNombre());
        veterinarioExistente.setCorreo(veterinario.getCorreo());
        veterinarioExistente.setEspecialidad(veterinario.getEspecialidad());
        veterinarioExistente.setTarjetaProfesional(veterinario.getTarjetaProfesional());
        veterinarioExistente.setMascotas(veterinario.getMascotas());
        return veterinarioRepository.save(veterinarioExistente);

    }

    @Override
    public void eliminar(Long id) {
        if (!veterinarioRepository.existsById(id)){
            throw new RecursoNoEncontrado("Veterinario no encontrado con ID: " + id);
        }veterinarioRepository.deleteById(id);

    }
}
