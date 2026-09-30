package com.example.veterinaria.Controllers;

import com.example.veterinaria.Entity.Propietario;
import com.example.veterinaria.Exceptions.RecursoNoEncontrado;
import com.example.veterinaria.Service.PropietarioService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietario")
@AllArgsConstructor
public class PropietarioController {
    private final PropietarioService propietarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Propietario>> listarTodos() {
        var propietarios = propietarioService.listarTodos();
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <Propietario> buscarPorId(@PathVariable Long id) {
        var propietario = propietarioService.buscarPorId(id);
        return ResponseEntity.ok(propietario);
    }


    @PostMapping("/crear")
    public ResponseEntity <Propietario> guardar(@RequestBody Propietario propietario) {
        return ResponseEntity.ok(propietarioService.guardar(propietario));
    }

    @PutMapping("/actualizarP/{id}")
    public ResponseEntity <Propietario> actualizar(@PathVariable Long id, @RequestBody Propietario propietario) {
        var propietarioActu =propietarioService.actualizar(id, propietario);
        return ResponseEntity.ok(propietarioActu);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity <Void> eliminar(@PathVariable Long id) {
        propietarioService.eliminar(id);
        return ResponseEntity
                .noContent()
                .build();

    }

}
