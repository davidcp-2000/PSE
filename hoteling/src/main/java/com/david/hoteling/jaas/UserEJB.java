/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.jaas;

import com.david.hoteling.entities.Grupos;
import com.david.hoteling.entities.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author parra
 */
@Stateless
public class UserEJB {

    @PersistenceContext
    private EntityManager em;

    public Usuarios createUser(Usuarios user,String rol) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Grupos group = new Grupos();
        group.setEmailusuarios(user.getEmailusuarios());
        group.setRol(rol);
        em.persist(user);
        em.persist(group);
        return user;
    }
    
    public Usuarios findByEmail(String email) {
        TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByEmailusuarios",
                Usuarios.class);
        query.setParameter("emailusuarios", email);
        Usuarios user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }
    
}
