/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.json;


import com.david.hoteling.entities.Hoteles;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
public class TarjetaReader implements MessageBodyReader<Tarjeta>{

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Tarjeta.class.isAssignableFrom(type);
    }

    @Override
    public Tarjeta readFrom(Class<Tarjeta> type,Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {
        Tarjeta t=new Tarjeta();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "tarjeta":
                            System.out.println("prueba valdavia json tarjeta");
                            t.setTarjeta(parser.getString());
                            break;
                        case "autorizada":
                            
                            
                            System.out.println("prueba valdavia json autorizada");
                            String aux=parser.getString();
                            System.out.println("prueba valdavia"+ aux);
                            t.setConfirmacion(parser.getString());
                            break;
                        
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return t;
    }
    
}
