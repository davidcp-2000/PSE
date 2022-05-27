/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.jaas;

import com.david.hoteling.entities.Usuarios;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author parra
 */

@Named
@SessionScoped
public class RegisterView implements Serializable {
    //columnas en comun para todos los usuarios
    private String nombre;
    private String email;
    private String password;
    private String confirmPassword;
    private String telefono="983882158";
    //columnas solo para clientes
    private String dni;
    private Date fechaNacimiento;
    //columna solo para empresas
    private double capitalSocial;
    private String domicilioSocial;
    private String cif;
    private boolean aceptada;

    
    @Inject
    private UserEJB userEJB;
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public String getDomicilioSocial() {
        return domicilioSocial;
    }

    public void setDomicilioSocial(String domicilioSocial) {
        this.domicilioSocial = domicilioSocial;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }
    
    
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Las contraseñas no coinciden");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }
        UIInput uiInputEmail = (UIInput) components.findComponent("email");
        String email = uiInputEmail.getLocalValue() == null ? "" : uiInputEmail.getLocalValue().toString();
        if (userEJB.findByEmail(email) != null) {
            FacesMessage msg = new FacesMessage("Ya existe un usuario con ese email");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }
    }
    
    public String registerCliente() {
        Usuarios user = new Usuarios(email, password,nombre,telefono);
        userEJB.createUser(user,"cliente");
        System.out.println("Nuevo usuario creado con e-mail: " + email + " y nombre:" + nombre+ " contraseña:"+ password +" telefono:"+telefono);
    return "regok";
    }
    
    public String registerEmpresa() {
        System.out.println("_________________________________________________________________________________");
        System.out.println("cif:" + cif);
        Usuarios user = new Usuarios(email, password, nombre, telefono,cif,domicilioSocial,capitalSocial,aceptada);
        userEJB.createUser(user,"empresa");
        System.out.println("Nuevo usuario creado con e-mail: " + email + " y nombre:" + nombre + " contraseña:" + password + " telefono:" + telefono+" cif: "+cif);
        return "regok";
    }
    
}
