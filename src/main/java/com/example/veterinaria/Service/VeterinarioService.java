package com.example.veterinaria.Service;

import com.example.veterinaria.Entity.Veterinario;

import java.util.List;

public interface VeterinarioService {
    Veterinario guardar (Veterinario veterinario);
    List<Veterinario> listarTodos();
    Veterinario buscarPorId(Long id);
    Veterinario actualizar(Long id, Veterinario veterinario);
    void eliminar(Long id);
}
