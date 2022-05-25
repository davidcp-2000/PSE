/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.reservaHotel;

import com.david.hoteling.entities.Hoteles;
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
import javax.ws.rs.client.WebTarget;

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
        System.out.println("Prueba1_____________________________________");
        List<Hoteles> list
                    = em.createNamedQuery("Hoteles.findAllCiudades",Hoteles.class).getResultList();
        System.out.println("_____________________________________");
         System.out.println("_____________________________________");
          System.out.println("_____________________________________");
           System.out.println("_____________________________________");
            System.out.println("prueba 4" +list);
        return list;
    }
    
     public List< Hoteles> getHotelesCiudades(){
         System.out.println("Prueba1_____________________________________"+ciudad);
         List< Hoteles> list = em.createNamedQuery("Hoteles.findByCiudad", Hoteles.class).setParameter("ciudad", ciudad).getResultList();
         System.out.println("Prueba2_____________________________________"+list);
        return list;
    }
     
     
}
