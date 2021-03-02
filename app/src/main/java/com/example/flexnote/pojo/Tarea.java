package com.example.flexnote.pojo;

public class Tarea {

    int numTarea;
    String titulo, asignatura, profesor, descripcion;


    public Tarea(int numTarea,String titulo, String asignatura, String profesor, String descripcion) {
        this.titulo = titulo;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.descripcion = descripcion;
    }
    public Tarea(){}

    public int getNumTarea() {
        return numTarea;
    }

    public void setNumTarea(int numTarea) {
        this.numTarea = numTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
