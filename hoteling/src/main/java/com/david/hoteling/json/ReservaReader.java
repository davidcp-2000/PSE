/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.json;


import com.david.hoteling.entities.Reserva;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author parra
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaReader implements MessageBodyReader<Reserva>{

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Reserva.class.isAssignableFrom(type);
    }

    @Override
    public Reserva readFrom(Class<Reserva> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream entityStream) throws IOException, WebApplicationException {
        
        Reserva r = new Reserva();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            r.setId(parser.getInt());
                            break;
                        case "hotel":
                            r.setHotel(parser.getInt());
                            break;
                        case "tarjeta":
                            r.setTarjeta(parser.getString());
                            break;
                        case "emailusuarios":
                            System.out.println("prueba 3");
                            String aux =parser.getString();
                            System.out.println("prueba 3"+aux);
                            r.setEmailusuarios(aux);
                            System.out.println("prueba 3"+r.getEmailusuarios());
                            break;
                        case "fecha":
                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                            String auxfecha =parser.getString();
                            try {
                                r.setFecha(formato.parse(auxfecha));
                            } catch (ParseException ex) {
                                Logger.getLogger(ReservaReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return r;
    }
    
}
