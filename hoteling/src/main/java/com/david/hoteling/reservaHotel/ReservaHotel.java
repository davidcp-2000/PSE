/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.reservaHotel;

import com.david.hoteling.entities.Hoteles;
import com.david.hoteling.entities.Reserva;
import com.david.hoteling.jaas.LoginView;
import com.david.hoteling.json.HotelReader;
import com.david.hoteling.json.HotelWriter;
import com.david.hoteling.json.ReservaWriter;
import com.david.hoteling.json.Tarjeta;
import com.david.hoteling.json.TarjetaReader;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@FlowScoped("reservaHotel")
public class ReservaHotel implements Serializable {
    String ciudad;
    int idHotel;
    Date fecha;
    String tarjeta;

    @PersistenceContext
    EntityManager em;
    
    Client client;
    WebTarget target,target2,target3;
    
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
        target3=client.target("http://valdavia.infor.uva.es:8080/hoteling/webresources/tarjetas");
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
         System.out.println("------------------------------------------prueba inicio flujo");
         List<Hoteles> list = em.createNamedQuery("Hoteles.findAllCiudad", Hoteles.class).getResultList();
        
         System.out.println("------------------------------------------prueba inicio flujo1");
         
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
     
     
     public String addReserva(){
         try {
         System.out.println("-------------------------------------try  "+autorizarReserva());
         }catch(NullPointerException e){
             System.out.println("---------------------------------- add reserva catch");
         }
         
         FacesContext context = FacesContext.getCurrentInstance();
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
         if(autorizarReserva().equals("si")){
            Reserva m = new Reserva();
            m.setId(1);
            m.setFecha(fecha);
            m.setHotel(idHotel);
            m.setEmailusuarios(request.getUserPrincipal().getName());
            m.setTarjeta(tarjeta);
            target2.register(ReservaWriter.class)
                .request()
                .post(Entity.entity(m,MediaType.APPLICATION_JSON));
            return "confirmarReserva.xhtml";
         }else{
            return "datosReserva.xhtml";
         }
        
    }
     
     public String autorizarReserva(){
         System.out.println("______________________________________________________________prueba valdivia");
         System.out.println("prueba tarjeta valdavia"+tarjeta);
         Tarjeta m = target3
                 .path("{tarjeta}")
                 .resolveTemplate("tarjeta",tarjeta )
                 .register(TarjetaReader.class)
                 .request(MediaType.APPLICATION_JSON)
                 .get(Tarjeta.class);
         try{
             System.out.println("prueba valdavid valor m si no hay tarjeta"+m.toString());
             System.out.println("------------------------------------------------ prueba try :"+m.getConfirmacion()); 
             if(m.getConfirmacion().equals("si")){
                 return "si";
             }else{
                 return "han rechazado el pago";
             }
                
         }catch(NullPointerException e){
             System.out.println("------------------------------------------------ prueba catch"); 
             return "tarjeta no encontrada porfavor introduzca otra tarjeta";  
         }
     }
     
     
     
}



