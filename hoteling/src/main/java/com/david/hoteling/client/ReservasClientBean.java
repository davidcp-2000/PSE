/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;


import com.david.hoteling.entities.Reserva;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

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
        return target
                .request()
                .get(Reserva[].class);
    }

    public void deleteReserva() {
        target.path("{idReserva}")
                .resolveTemplate("idReserva", bean.getIdReserva())
                .request()
                .delete();
    }
}
