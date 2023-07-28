package com.Curso2.Curso2.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Usuarios")
@ToString
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "Id")
    private long id;
    @Getter @Setter @Column(name = "Nombre")
    private String Nombre;
    @Getter @Setter @Column(name = "Apellido")
    private String Apellido;
    @Getter @Setter @Column(name = "Email")
    private String Email;
    @Getter @Setter @Column(name = "Telefono")
    private String Telefono;
    @Getter @Setter @Column(name = "Password")
    private String Constrasena;



}
