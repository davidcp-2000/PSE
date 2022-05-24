/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author parra
 */
@Entity
@Table(name = "hoteles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoteles.findAll", query = "SELECT h FROM Hoteles h"),
    @NamedQuery(name = "Hoteles.findById", query = "SELECT h FROM Hoteles h WHERE h.id = :id"),
    @NamedQuery(name = "Hoteles.findByNombre", query = "SELECT h FROM Hoteles h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Hoteles.findByCiudad", query = "SELECT h FROM Hoteles h WHERE h.ciudad = :ciudad"),
    @NamedQuery(name = "Hoteles.findAllCiudades", query = "SELECT DISTINCT h.ciudad FROM Hoteles h"),
    @NamedQuery(name = "Hoteles.findByNumhabitaciones", query = "SELECT h FROM Hoteles h WHERE h.numhabitaciones = :numhabitaciones"),
    @NamedQuery(name = "Hoteles.findByPreciohabitacion", query = "SELECT h FROM Hoteles h WHERE h.preciohabitacion = :preciohabitacion"),
    @NamedQuery(name = "Hoteles.findByEmailempresa", query = "SELECT h FROM Hoteles h WHERE h.emailempresa = :emailempresa")})
public class Hoteles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numhabitaciones")
    private int numhabitaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preciohabitacion")
    private double preciohabitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "emailempresa")
    private String emailempresa;

    public Hoteles() {
    }

    public Hoteles(Integer id) {
        this.id = id;
    }

    public Hoteles(Integer id, String nombre, String ciudad, int numhabitaciones, double preciohabitacion, String emailempresa) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.numhabitaciones = numhabitaciones;
        this.preciohabitacion = preciohabitacion;
        this.emailempresa = emailempresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNumhabitaciones() {
        return numhabitaciones;
    }

    public void setNumhabitaciones(int numhabitaciones) {
        this.numhabitaciones = numhabitaciones;
    }

    public double getPreciohabitacion() {
        return preciohabitacion;
    }

    public void setPreciohabitacion(double preciohabitacion) {
        this.preciohabitacion = preciohabitacion;
    }

    public String getEmailempresa() {
        return emailempresa;
    }

    public void setEmailempresa(String emailempresa) {
        this.emailempresa = emailempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoteles)) {
            return false;
        }
        Hoteles other = (Hoteles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.david.hoteling.entities.Hoteles[ id=" + id + " ]";
    }
    
}
