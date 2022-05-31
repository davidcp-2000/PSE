/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;


import com.david.hoteling.entities.Hoteles;
import com.david.hoteling.entities.Reserva;
import com.david.hoteling.json.HotelReader;
import com.david.hoteling.json.ReservaReader;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author parra
 */
@Named
@RequestScoped
public class ReservasClientBean {
    Client client;
    WebTarget target,target2;
    @Inject
    ReservasBackingBean bean;

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.reserva");
        target2= client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.hoteles");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Reserva[] getReservas() {
        return target
                .path("ReservaCliente/{emailusuarios}")
                .resolveTemplate("emailusuarios",request.getUserPrincipal().getName())
                .request()
                .get(Reserva[].class);
    }

    public void deleteReserva() {
        target.path("{idReserva}")
                .resolveTemplate("idReserva", bean.getIdReserva())
                .request()
                .delete();
    }
    
    public Reserva getReserva() {
        Reserva m = target
                .register(ReservaReader.class)
                .path("{idHoteles}")
                .resolveTemplate("idHoteles", bean.getIdReserva())
                .request(MediaType.APPLICATION_JSON)
                .get(Reserva.class);
        return m;
    }
    
    
    public Hoteles getHotel(int id) {
        System.out.println("prueba flujo get Hotel________________________________________________________________________");
            Hoteles m=target2
                .register(HotelReader.class)
                .path("{idHoteles}")
                .resolveTemplate("idHoteles", id)
                .request(MediaType.APPLICATION_JSON)
                .get(Hoteles.class);
            System.out.println("prueba flujo get Hotel_______________");
        return m;
    }
    public Hoteles auxGetHotel(int id) {
        System.out.println("prueba flujo get Hotel________________________________________________________________________");
            Hoteles m=getHotel(id);
            bean.setH(m);
        return m;
    }
    
     
}
