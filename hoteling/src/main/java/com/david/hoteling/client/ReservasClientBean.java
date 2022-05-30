/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;


import com.david.hoteling.entities.Reserva;
import com.david.hoteling.json.ReservaReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
    WebTarget target;
    @Inject
    ReservasBackingBean bean;

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.reserva");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Reserva[] getReservas() {
        System.out.println("____________________________________PRUEBA");
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
}
