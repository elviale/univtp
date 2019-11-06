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
public class Cursada {
    private int id_curs;
    private Alumno alumno;
    private Materia materia;
    private double nota;

    public Cursada() {
    }

    public Cursada(Alumno alumno, Materia materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Cursada(int id_curs, Alumno alumno, Materia materia, double nota) {
        this.id_curs = id_curs;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public int getId_curs() {
        return id_curs;
    }

    public void setId_curs(int id_curs) {
        this.id_curs = id_curs;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

    