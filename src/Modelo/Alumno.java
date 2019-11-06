/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author elviale
 */
public class Alumno {
     private int id_alum;
    private String nombre;
    private int dni;
    private String domicilio;
    private LocalDate fechaNac;

    public Alumno() {
    }

    public Alumno(int id_alum, String nombre, int dni, String domicilio, LocalDate fechaNac) {
        this.id_alum = id_alum;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.fechaNac = fechaNac;
    }
    

    public Alumno(String nombre, int dni, String domicilio, LocalDate fechaNac) {
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.fechaNac = fechaNac;
    
    }

    public int getId_alum() {
        return id_alum;
    }

    public void setId_alum(int id_alum) {
        this.id_alum = id_alum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }
    
}
   