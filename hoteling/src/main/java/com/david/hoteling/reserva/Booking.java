/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.reserva;


import com.david.hoteling.entities.Hoteles;
import com.david.hoteling.entities.Reserva;
import com.david.hoteling.json.HotelReader;
import com.david.hoteling.json.HotelWriter;
import com.david.hoteling.json.ReservaWriter;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author parra
 */

public class Booking  {
    String ciudad;
    int idHotel;
    Date fecha;
    String tarjeta;
    
    
    @PersistenceContext
    EntityManager em;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
    Client client;
    WebTarget target;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        //es posible que tenga que cambiar reservas por hoteles, porque mucha consultas son en hoteles
        target= client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.reservas");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    ///obtienes todos las ciudades donde se encuentran hoteles 
    public Hoteles[] getCiudadesDisponibles(){
        return target.path("ciudadesDisponibles").request().get(Hoteles[].class);
    }
    
    public Hoteles[] getHotelesCiudades(){
        return target.path("hotelesCiudad/{ciudad}").resolveTemplate("ciudad", ciudad).request().get(Hoteles[].class);
    }
    
    public Hoteles getHotel() {
        Hoteles m = target
                .register(HotelReader.class)
                .path("{idHoteles}")
                .resolveTemplate("idHoteles", idHotel)
                .request(MediaType.APPLICATION_JSON)
                .get(Hoteles.class);
        return m;
    }
    
    
    public void addReserva() {
        Reserva h = new Reserva();
        h.setId(1);
        h.setFecha(fecha);
        h.setHotel(idHotel);
        h.setTarjeta(tarjeta);
        h.setEmailusuarios("prueba2@gmail.com");
        target.register(ReservaWriter.class)
                .request()
                .post(Entity.entity(h, MediaType.APPLICATION_JSON));
    }
}
