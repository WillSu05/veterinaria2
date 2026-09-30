package com.example.veterinaria.Controllers;

import com.example.veterinaria.Entity.HistoriaClinica;
import com.example.veterinaria.Entity.Mascota;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Service.HistoriaClinicaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/historiaClinica")
@AllArgsConstructor
public class HistoriaClinicaController {
    private final HistoriaClinicaService historiaClinicaService;

    @GetMapping("/listarHistorias")
    public ResponseEntity <List<HistoriaClinica>> listarTodos() {
        var historiasClinicas = historiaClinicaService.listarTodos();
        return ResponseEntity.ok(historiasClinicas);
    }

    @GetMapping("/buscarHistoria/{id}")
    public ResponseEntity <HistoriaClinica> buscarPorId(@PathVariable Long id) {
        var historia = historiaClinicaService.buscarPorId(id);
        return ResponseEntity.ok(historia);

    }

    @PostMapping("/crearHistoria/{mascotaID}")
    public ResponseEntity <HistoriaClinica> crear(@RequestBody HistoriaClinica historia, @RequestParam Long mascotaID) {
        HistoriaClinica nuevaHistoriaClinica = historiaClinicaService.crear(historia, mascotaID);
        return ResponseEntity.ok(nuevaHistoriaClinica);

    }

    @PutMapping("/actualizarHistoria/{id}")
    public ResponseEntity<HistoriaClinica> actualizar(@PathVariable Long id,@Valid @RequestBody HistoriaClinica historia) {
        HistoriaClinica historiaActualizada = historiaClinicaService.actualizar(id, historia);
        return ResponseEntity.ok(historiaActualizada);
    }

    @DeleteMapping("/eliminarHistoria/{id}")
    public ResponseEntity <Void> eliminar(@PathVariable Long id) {
        historiaClinicaService.eliminar(id);
        return ResponseEntity.noContent().build();


    }
}
