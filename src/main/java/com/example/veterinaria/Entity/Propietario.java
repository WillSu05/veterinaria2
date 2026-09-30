package com.example.veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Table (name = "propietarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "NOMBRE OBLIGATORIO")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "DOCUMENTO OBLIGATORIO")
    @Column(nullable = false, unique = true)
    private String documento;

    @NotBlank(message = "TELEFONO OBLIGATORIO")
    @Column(nullable = false)
    private String telefono;

    @NotBlank(message = "CORREO OBLIGATORIO")
    @Email(message = "INGRESE UN CORREO VALIDO")
    @Column(nullable = false, unique = true)
    private String correo;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("propietario")
    private List<Mascota> mascotas = new ArrayList<>();
}
