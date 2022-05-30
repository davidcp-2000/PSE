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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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
    
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target= client.target("http://localhost:8080/hoteling/webresources/com.david.hoteling.entities.hoteles");
    }

    @PreDestroy
    public void destroy() {
        client.close();
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
    
    public Hoteles[] getHoteles() {//funciona
        return target
                .path("HotelesDeEmpresa/{emailempresa}")
                .resolveTemplate("emailempresa",request.getUserPrincipal().getName())
                .request()
                .get(Hoteles[].class);
    }
    
    public void addHotel() {
        Hoteles h = new Hoteles();
        h.setId(1);
        h.setNombre(bean.getNombreHoteles());
        h.setCiudad(bean.getCiudadHoteles());
        h.setNumhabitaciones(bean.getNumHabitacionesHoteles());
        h.setPreciohabitacion(bean.getPrecioHabitacionHoteles());
        h.setEmailempresa(request.getUserPrincipal().getName());
        h.setServicios(bean.getServicios());
        target.register(HotelWriter.class)
                .request()
                .post(Entity.entity(h, MediaType.APPLICATION_JSON));
    }
    
    public void deleteHotel() {
        target.path("{idHoteles}")
                .resolveTemplate("idHoteles", bean.getIdHoteles())
                .request()
                .delete();
    }
    
    public void auxModificarHotel(){
        Hoteles h=getHotel();
        bean.setIdHoteles(h.getId());
        bean.setNombreHoteles(h.getNombre());
        bean.setCiudadHoteles(h.getCiudad());
        bean.setNumHabitacionesHoteles(h.getNumhabitaciones());
        bean.setPrecioHabitacionHoteles( h.getPreciohabitacion());
        bean.setEmailEmpresaHoteles(h.getEmailempresa());
        bean.setServicios(h.getServicios());

    }
    
    public void modificarHotel() {
        Hoteles h = new Hoteles();
        System.out.println("--------------------------------------------- prueba modificar hotel");
        h.setId(bean.getIdHoteles());
        
        System.out.println("prueba ciudad: "+bean.getCiudadHoteles());
        
        h.setNombre(bean.getNombreHoteles());
        h.setCiudad(bean.getCiudadHoteles());
        h.setNumhabitaciones(bean.getNumHabitacionesHoteles());
        h.setPreciohabitacion(bean.getPrecioHabitacionHoteles());
        h.setEmailempresa(bean.getEmailEmpresaHoteles());
        h.setServicios(bean.getServicios());
        target.register(HotelWriter.class)
                .path("{id}")
                .resolveTemplate("id", bean.getIdHoteles())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(h, MediaType.APPLICATION_JSON));
    }
    
   
    
    
    
    
}
