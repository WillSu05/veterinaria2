package com.example.veterinaria.Service;

import com.example.veterinaria.Entity.Mascota;

import java.util.List;

public interface MascotaService {
    Mascota buscarPorId (Long id);
    List<Mascota> listarTodos();
    Mascota guardad(Mascota mascota, Long propietarioId);
    Mascota actualizar(Long id, Mascota mascota);
    void eliminar (Long id);
    Mascota asignarVeterinario(Long mascotaId, Long veterinarioId);
}
