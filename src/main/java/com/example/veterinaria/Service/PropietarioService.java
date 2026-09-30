package com.example.veterinaria.Service;

import com.example.veterinaria.Entity.Propietario;

import java.util.List;

public interface PropietarioService {
    List<Propietario> listarTodos();

    Propietario buscarPorId(Long id);

    Propietario guardar(Propietario propietario);

    Propietario actualizar(Long id, Propietario propietario);

    void eliminar(Long id);
}
