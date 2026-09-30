package com.example.veterinaria.Controllers;

import com.example.veterinaria.Entity.Veterinario;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Repository.VeterinarioRepository;
import com.example.veterinaria.Service.VeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/veterinario")
@RequiredArgsConstructor
public class VeterinarioController {
    private final VeterinarioService veterinarioService;
    @PostMapping("/crearVeterinario")
    public ResponseEntity <Veterinario> guardar(@RequestBody Veterinario veterinario) {
        Veterinario veterinarioNuevo = veterinarioService.guardar(veterinario);
        return ResponseEntity.ok(veterinarioNuevo);
    }

    @GetMapping("/listarVeterinarios")
    public ResponseEntity <List<Veterinario>> listarTodos() {
        var veterinarios = veterinarioService.listarTodos();
        return ResponseEntity.ok(veterinarios);
    }

    @GetMapping("/buscarVeterinario/{id}")
    public ResponseEntity <Veterinario> buscarPorId(@PathVariable Long id) {
        var veterinario = veterinarioService.buscarPorId(id);
        return ResponseEntity.ok(veterinario);
    }

    @PutMapping("/actualizarVeterinario/{id}")
    public ResponseEntity <Veterinario> actualizar(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        Veterinario veterinarioActualizado = veterinarioService.actualizar(id, veterinario);
        return ResponseEntity.ok(veterinarioActualizado);
    }

    @DeleteMapping("/eliminarVeterinario/{id}")
    public ResponseEntity<Void> eliminar(Long id) {
        veterinarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
