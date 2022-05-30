/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;

import com.david.hoteling.entities.Usuarios;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class UsuariosClientBean {
    Client client;
    WebTarget target,target2;
    @Inject
    UsuariosBackingBean bean;

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.usuarios");
        target2 = client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.grupos");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public Usuarios getUsuario(){
        Usuarios m = em.createNamedQuery("Usuarios.findByEmailusuarios", Usuarios.class).setParameter("emailusuarios", request.getUserPrincipal().getName()).getSingleResult();
        return m;
    }
}
