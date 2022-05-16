/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.json;

import com.david.hoteling.entities.Hoteles;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
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
public class HotelWriter implements MessageBodyWriter<Hoteles> {

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Hoteles.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Hoteles t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return -1;
    }

    @Override
    public void writeTo(Hoteles t,Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
        OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject()
                .write("id", t.getId())
                .write("nombre", t.getNombre())
                .write("ciudad", t.getCiudad())
                .write("numhabitaciones", t.getNumhabitaciones())
                .write("preciohabitacion", t.getPreciohabitacion())
                .write("emailempresa", t.getEmailempresa())
                .writeEnd();
        gen.flush();
    }
    
}
