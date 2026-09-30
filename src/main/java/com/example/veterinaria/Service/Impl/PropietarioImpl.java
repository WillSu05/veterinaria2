package com.example.veterinaria.Service.Impl;

import com.example.veterinaria.Entity.Propietario;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Repository.PropietarioRepository;
import com.example.veterinaria.Service.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PropietarioImpl implements PropietarioService {
    private final PropietarioRepository propietarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> listarTodos() {
        return propietarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Propietario buscarPorId(Long id) {
        return propietarioRepository.findById(id)
                .orElseThrow(()->
                        new RecursoNoEncontrado("Propietario no Encotrado "+ id));
    }

    @Override
    @Transactional
    public Propietario guardar(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    @Override
    @Transactional
    public Propietario actualizar(Long id, Propietario propietario) {
        Propietario actual = buscarPorId(id);
        actual.setNombre(propietario.getNombre());
        actual.setDocumento(propietario.getDocumento());
        actual.setTelefono(propietario.getTelefono());
        actual.setCorreo(propietario.getCorreo());
        return propietarioRepository.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        if (!propietarioRepository.existsById(id)){
            throw new RecursoNoEncontrado("Propietario no encontrado");
        } propietarioRepository.deleteById(id);
    }
}
