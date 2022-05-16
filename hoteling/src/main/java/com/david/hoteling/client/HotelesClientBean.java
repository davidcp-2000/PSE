/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;

import com.david.hoteling.entities.Hoteles;
import com.david.hoteling.json.HotelReader;
import com.david.hoteling.json.HotelWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
@RequestScoped
public class HotelesClientBean {
    Client client;
    WebTarget target;
    @Inject
    HotelesBackingBean bean;
    
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target= client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.hoteles");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public Hoteles[] getHoteles() {
        return target
                .request()
                .get(Hoteles[].class);
    }
    public void deleteHotel() {
        target.path("{idHoteles}")
                .resolveTemplate("idHoteles", bean.getIdHoteles())
                .request()
                .delete();
    }
    public Hoteles getHotel() {
        Hoteles m = target
                .register(HotelReader.class)

                .path("{idHoteles}")
                .resolveTemplate("idHoteles", bean.getIdHoteles())
                .request(MediaType.APPLICATION_JSON)
                .get(Hoteles.class);
        return m;
    }
   
    
    public void addHotel() {
        Hoteles h = new Hoteles();
        h.setId(3);
        h.setNombre(bean.getNombreHoteles());
        h.setCiudad(bean.getCiudadHoteles());
        h.setNumhabitaciones(12);
        h.setPreciohabitacion(12);
        h.setEmailempresa("prueba@gmail.com");
        target.register(HotelWriter.class)
                .request()
                .post(Entity.entity(h, MediaType.APPLICATION_JSON));
    }
}
