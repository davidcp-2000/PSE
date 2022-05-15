/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.client;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author parra
 */
@Named
@SessionScoped
public class HotelesBackingBean implements Serializable {
    int idHoteles; 
    
    public int getIdHoteles() {
        return idHoteles;
    }

    public void setIdHoteles(int idHoteles) {
        this.idHoteles = idHoteles;
    }
    
}
