/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author elviale
 */
public class Materia {
    private int id_mat;
    private String nombre;
    private String periodo;
    private String responsable;

    public Materia() {
       
    }

    public Materia(String nombre, String periodo, String responsable) {
        this.nombre = nombre;
        this.periodo = periodo;
        this.responsable = responsable;
    }

    public Materia(int id_mat, String nombre, String periodo, String responsable) {
        this.id_mat = id_mat;
        this.nombre = nombre;
        this.periodo = periodo;
        this.responsable = responsable;
    }

    public int getId_mat() {
        return id_mat;
    }

    public void setId_mat(int id_mat) {
        this.id_mat = id_mat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
 
    
    
}

    