/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.reserva;

import com.david.hoteling.entities.Hoteles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author parra
 */
@Named
@FlowScoped("booking")
public class Booking implements Serializable {
    
}
