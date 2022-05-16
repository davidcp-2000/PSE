/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author parra
 */
@Named
@SessionScoped
public class HotelesBackingBean implements Serializable {
    int idHoteles;
    String nombreHoteles;
    String ciudadHoteles;
    int numHabitacionesHoteles;
    float precioHabitacionHoteles;
    String emailEmpresaHoteles;

    public String getNombreHoteles() {
        return nombreHoteles;
    }

    public void setNombreHoteles(String nombreHoteles) {
        this.nombreHoteles = nombreHoteles;
    }

    public String getCiudadHoteles() {
        return ciudadHoteles;
    }

    public void setCiudadHoteles(String ciudadHoteles) {
        this.ciudadHoteles = ciudadHoteles;
    }

    public int getNumHabitacionesHoteles() {
        return numHabitacionesHoteles;
    }

    public void setNumHabitacionesHoteles(int numHabitacionesHoteles) {
        this.numHabitacionesHoteles = numHabitacionesHoteles;
    }

    public float getPrecioHabitacionHoteles() {
        return precioHabitacionHoteles;
    }

    public void setPrecioHabitacionHoteles(float precioHabitacionHoteles) {
        this.precioHabitacionHoteles = precioHabitacionHoteles;
    }

    public String getEmailEmpresaHoteles() {
        return emailEmpresaHoteles;
    }

    public void setEmailEmpresaHoteles(String emailEmpresaHoteles) {
        this.emailEmpresaHoteles = emailEmpresaHoteles;
    }
    
    public int getIdHoteles() {
        return idHoteles;
    }

    public void setIdHoteles(int idHoteles) {
        this.idHoteles = idHoteles;
    }
    
}
