package com.example.veterinaria.Controllers;

import com.example.veterinaria.Entity.Mascota;
import com.example.veterinaria.Entity.Propietario;
import com.example.veterinaria.Entity.Veterinario;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@AllArgsConstructor
public class MascotaController {
    private final MascotaService mascotaService;
    @GetMapping("/buscarMascota/{id}")
    public ResponseEntity <Mascota> buscarPorId(@PathVariable Long id) {
        var mascota = mascotaService.buscarPorId(id);
        return ResponseEntity.ok(mascota);
    }
    @GetMapping("/listarMascotas")
    public ResponseEntity <List<Mascota>> listarTodos() {
        var mascotas = mascotaService.listarTodos();
        return ResponseEntity.ok(mascotas);
    }
    @PostMapping("/crearMascota/{propietarioId}")
    public ResponseEntity <Mascota> guardad(@RequestBody Mascota mascota, @PathVariable Long propietarioId) {
        return ResponseEntity.ok(mascotaService.guardad(mascota,propietarioId));
    }

    @PutMapping("/actualizarMascota/{id}")
    public ResponseEntity <Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        Mascota mascotaActualizada = mascotaService.actualizar(id, mascota);
        return ResponseEntity.ok(mascotaActualizada);

    }

    @PostMapping("/{mascotaId}/asignar-veterinario/{veterinarioId}")
    public ResponseEntity <Mascota> asignarVeterinario(@PathVariable Long mascotaId, @PathVariable Long veterinarioId) {
        return ResponseEntity.ok(mascotaService.asignarVeterinario(mascotaId, veterinarioId));

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity <Void> eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
