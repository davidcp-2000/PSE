/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.json;

import com.david.hoteling.entities.Reserva;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author parra
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ReservaWriter  implements MessageBodyWriter<Reserva>{

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Reserva.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Reserva t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return -1;
    }

    @Override
    public void writeTo(Reserva t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject()
                .write("id", t.getId())
                .write("hotel", t.getHotel())
                .write("tarjeta", t.getTarjeta())
                .write("emailusuario", t.getEmailusuarios())
                .write("fecha", (JsonValue) t.getFecha())
                .writeEnd();
        gen.flush();
    }
    
}
