package com.example.veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Table(name = "Veterinarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "NOMBRE OBLIGATORIO")
    @Column(nullable = false)
    private String nombre;
    @NotBlank(message = "INGRESE LA TARJETA PROFESIONAL")
    @Column(nullable = false, unique = true)
    private String tarjetaProfesional;
    @NotBlank(message = "ESPECIALIDAD OBLIGATORIA")
    @Column(nullable = false)
    private String especialidad;
    @NotBlank(message = "CORREO OBLIGATORIO")
    @Email(message = "INGRESE UN CORREO VALIDO")
    @Column(nullable = false, unique = true)
    private String correo;

    @ManyToMany(mappedBy = "veterinarios")
    @JsonIgnoreProperties("veterinarios")
    private List<Mascota> mascotas = new ArrayList<>();
}
