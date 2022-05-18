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
public class HotelReader implements MessageBodyReader<Hoteles>{

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Hoteles.class.isAssignableFrom(type);
    }

    @Override
    public Hoteles readFrom(Class<Hoteles> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {
        Hoteles hotel = new Hoteles();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            hotel.setId(parser.getInt());
                            break;
                        case "nombre":
                            hotel.setNombre(parser.getString());
                            break;
                        case "ciudad":
                            hotel.setCiudad(parser.getString());
                            break;
                        case "numhabitaciones":
                            hotel.setNumhabitaciones(parser.getInt());
                            break;
                        case "preciohabitacion":
                            hotel.setPreciohabitacion(Double.parseDouble(parser.getString()));
                            break;
                        case "emailempresa":
                            hotel.setEmailempresa(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return hotel;
    }
    
}
