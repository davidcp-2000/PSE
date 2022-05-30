/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author parra
 */
@Named
@SessionScoped
public class ReservasBackingBean implements Serializable{
    int idReserva;
    Date fecha;
    int idHotel;
    String Tarjeta;
    String email;
    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(String Tarjeta) {
        this.Tarjeta = Tarjeta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}
