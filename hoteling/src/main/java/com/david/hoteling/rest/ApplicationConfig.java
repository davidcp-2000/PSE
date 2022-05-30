/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.rest;

import java.util.Set;
import javax.inject.Named;
import javax.ws.rs.core.Application;

/**
 *
 * @author parra
 */
@javax.ws.rs.ApplicationPath("webresources")
@Named
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.david.hoteling.json.HotelReader.class);
        resources.add(com.david.hoteling.json.HotelWriter.class);
        resources.add(com.david.hoteling.json.ReservaReader.class);
        resources.add(com.david.hoteling.json.ReservaWriter.class);
        resources.add(com.david.hoteling.json.TarjetaReader.class);
        resources.add(com.david.hoteling.rest.GruposFacadeREST.class);
        resources.add(com.david.hoteling.rest.HotelesFacadeREST.class);
        resources.add(com.david.hoteling.rest.ReservaFacadeREST.class);
        resources.add(com.david.hoteling.rest.UsuariosFacadeREST.class);
    }
    
}
