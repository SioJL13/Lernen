package com.siomarajimenezl.lernen;

/**
 * Created by Alejandro on 2/19/16.
 */
public class Tutors {

    private String Nombre;
    private String Course;
    private String Degree;
    private String Descripcion;

    public Tutors(String nombre, String course, String degree, String descripcion) {
        this.Nombre = nombre;
        this.Course = course;
        this.Degree = degree;
        this.Descripcion = descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
