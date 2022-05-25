/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.reservaHotel;

import com.david.hoteling.entities.Hoteles;
import com.david.hoteling.entities.Reserva;
import com.david.hoteling.json.HotelReader;
import com.david.hoteling.json.HotelWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Named
@FlowScoped("reservaHotel")
public class ReservaHotel implements Serializable {
    String ciudad;
    int idHotel;
    Date fecha;
    String tarjeta;

    @PersistenceContext
    EntityManager em;
    
    Client client;
    WebTarget target,target2;
    
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
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        //es posible que tenga que cambiar reservas por hoteles, porque mucha consultas son en hoteles
        target= client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.hoteles");
        target2= client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.reserva");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
     /*preguntar si se puede hacer con target y como
    public List<Hoteles> getCiudadesDisponibles(){
        System.out.println("Prueba1_____________________________________");
        
        List<Hoteles> list= (List<Hoteles>) target.path("ciudadesDisponibles")
                .request().get();
    
        System.out.println("_____________________________________");
         System.out.println("_____________________________________");
          System.out.println("_____________________________________");
           System.out.println("_____________________________________");
            System.out.println("prueba 4" +list);
            ciudades=list;
        return list;
    }*/
     public List<Hoteles> getCiudadesDisponibles(){
        List<Hoteles> list
                    = em.createNamedQuery("Hoteles.findAllCiudades",Hoteles.class).getResultList();
        return list;
    }
    
     public List<Hoteles> getHotelesCiudades(){
         List<Hoteles> list = em.createNamedQuery("Hoteles.findByCiudad", Hoteles.class).setParameter("ciudad", ciudad).getResultList();
        return list;
    }
     
     public Hoteles getHotel(){
        Hoteles m = target
                .register(HotelReader.class)
                .path("{idHoteles}")
                .resolveTemplate("idHoteles", idHotel)
                .request(MediaType.APPLICATION_JSON)
                .get(Hoteles.class);
        return m;
    }
     
     
     public void addReserva(){
         System.out.println("-------------------------------------");
        System.out.println("prueba tarjeta"+tarjeta);
        System.out.println("prueba fecha"+fecha);
        Reserva m = new Reserva();
        m.setId(1);
        m.setFecha(fecha);
        m.setHotel(idHotel);
        m.setTarjeta(tarjeta);
        m.setEmailusuarios("prueba@añadirReserva");
        target2.register(HotelWriter.class)
                .request()
                .post(Entity.entity(m,MediaType.APPLICATION_JSON));
    }
     
     
}
