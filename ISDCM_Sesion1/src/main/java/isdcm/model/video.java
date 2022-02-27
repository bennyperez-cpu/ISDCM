/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
public class video {
    private String titulo;
    private String autor;
    private Date fecha_creacion;
    private Time duracion;
    private Integer reproducciones;
    private String descripcion;
    private String formato;
    
    
    public video() {
    }

    public video(String titulo, String autor, Date fecha_creacion, Time duracion, String descripcion, String formato) {
        this.titulo = titulo;
        this.autor = autor;
        this.fecha_creacion = fecha_creacion;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.formato = formato;
    }
    

    public String getTitle() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public String getAuthor() {
        return autor;
    }

    public void setAuthor(String autor) {
        this.autor = autor;
    }

    public Date getCreatedAt() {
        return fecha_creacion;
    }

    public void setCreatedAt(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Time getDuration() {
        return duracion;
    }

    public void setDuration(Time duracion) {
        this.duracion = duracion;
    }

    public Integer getVisualizations() {
        return reproducciones;
    }

    public void setVisualizations(Integer reproducciones) {
        this.reproducciones = reproducciones;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormat() {
        return formato;
    }

    public void setFormat(String formato) {
        this.formato = formato;
    }

}
