/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.json;

/**
 *
 * @author parra
 */
public class Tarjeta {
    String tarjeta="";
    String confirmacion="";

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
    
    public String toString(){
        return " tarjeta"+tarjeta+ " aceptada:" +confirmacion;
    }
}
