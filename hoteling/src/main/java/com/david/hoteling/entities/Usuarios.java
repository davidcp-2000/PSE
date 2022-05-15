/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author parra
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByEmailusuarios", query = "SELECT u FROM Usuarios u WHERE u.emailusuarios = :emailusuarios"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByDni", query = "SELECT u FROM Usuarios u WHERE u.dni = :dni"),
    @NamedQuery(name = "Usuarios.findByFechanacimiento", query = "SELECT u FROM Usuarios u WHERE u.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Usuarios.findByCapitalsocial", query = "SELECT u FROM Usuarios u WHERE u.capitalsocial = :capitalsocial"),
    @NamedQuery(name = "Usuarios.findByDomiciliosocial", query = "SELECT u FROM Usuarios u WHERE u.domiciliosocial = :domiciliosocial"),
    @NamedQuery(name = "Usuarios.findByCif", query = "SELECT u FROM Usuarios u WHERE u.cif = :cif"),
    @NamedQuery(name = "Usuarios.findByAceptada", query = "SELECT u FROM Usuarios u WHERE u.aceptada = :aceptada")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "emailusuarios")
    private String emailusuarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 9)
    @Column(name = "dni")
    private String dni;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "capitalsocial")
    private Double capitalsocial;
    @Size(max = 200)
    @Column(name = "domiciliosocial")
    private String domiciliosocial;
    @Size(max = 9)
    @Column(name = "cif")
    private String cif;
    @Column(name = "aceptada")
    private Boolean aceptada;

    public Usuarios() {
    }

    public Usuarios(String emailusuarios) {
        this.emailusuarios = emailusuarios;
    }

    public Usuarios(String emailusuarios, String password, String nombre, String telefono) {
        this.emailusuarios = emailusuarios;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getEmailusuarios() {
        return emailusuarios;
    }

    public void setEmailusuarios(String emailusuarios) {
        this.emailusuarios = emailusuarios;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Double getCapitalsocial() {
        return capitalsocial;
    }

    public void setCapitalsocial(Double capitalsocial) {
        this.capitalsocial = capitalsocial;
    }

    public String getDomiciliosocial() {
        return domiciliosocial;
    }

    public void setDomiciliosocial(String domiciliosocial) {
        this.domiciliosocial = domiciliosocial;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Boolean getAceptada() {
        return aceptada;
    }

    public void setAceptada(Boolean aceptada) {
        this.aceptada = aceptada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailusuarios != null ? emailusuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.emailusuarios == null && other.emailusuarios != null) || (this.emailusuarios != null && !this.emailusuarios.equals(other.emailusuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.david.hoteling.entities.Usuarios[ emailusuarios=" + emailusuarios + " ]";
    }
    
}
