package com.example.flexnote.pojo;

public class Profesor {

    String nombre, apellido, imagenUrl, fecNac,dni,asignatura;

    public Profesor(String nombre, String apellido, String imagenUrl, String fecNac, String dni, String asignatura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagenUrl = imagenUrl;
        this.fecNac = fecNac;
        this.dni = dni;
        this.asignatura = asignatura;
    }

    public Profesor(){}

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
}
