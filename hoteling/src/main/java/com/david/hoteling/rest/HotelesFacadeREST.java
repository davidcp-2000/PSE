/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.rest;

import com.david.hoteling.entities.Hoteles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author parra
 */
@Stateless
@Path("com.david.hoteling.entities.hoteles")
public class HotelesFacadeREST extends AbstractFacade<Hoteles> {

    @PersistenceContext(unitName = "com.david_hoteling_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public HotelesFacadeREST() {
        super(Hoteles.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Hoteles entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Hoteles entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hoteles find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hoteles> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hoteles> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    //_______________________
    @GET
    @Path("HotelesDeEmpresa/{emailempresa}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hoteles> findHotelesEmpresa(@PathParam("emailempresa") String emailempresa) {
        
        List< Hoteles> list = em.createNamedQuery("Hoteles.findByEmailempresa", Hoteles.class)
                .setParameter("emailempresa", emailempresa).getResultList();
        
        System.out.println(list);
        return list;
    }
    
    @GET
    @Path("CiudadesDisponibles")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hoteles> ciudadesDisponibles() {
        System.out.println("------------------------------------------prueba inicio flujo0.1");
        List< Hoteles> list = em.createNamedQuery("Hoteles.findAllCiudad", Hoteles.class).getResultList();
        System.out.println("------------------------------------------prueba inicio flujo0.2");
        System.out.println(list);
        return list;
    }
}
