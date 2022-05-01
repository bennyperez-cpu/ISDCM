/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.model;

/**
 *
 * @author alumne
 */
public class usuario {
    private String nombre;
    private String apellido;
    private String correo_electronico;
    private String nombre_de_usuario;
    private String contrasenha;

    public usuario() {
        this.nombre = "";
        this.apellido = "";
        this.correo_electronico = "";
        this.nombre_de_usuario = "";
        this.contrasenha = "";
    }

    public usuario(String nombre, String apellido, String correo_electronico, String nombre_de_usuario, String contrasenha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_electronico = correo_electronico;
        this.nombre_de_usuario = nombre_de_usuario;
        this.contrasenha = contrasenha;
    }

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

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getNombre_de_usuario() {
        return nombre_de_usuario;
    }

    public void setNombre_de_usuario(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
    
}
