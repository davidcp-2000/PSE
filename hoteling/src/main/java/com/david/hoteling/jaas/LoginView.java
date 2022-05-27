/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.david.hoteling.jaas;


import com.david.hoteling.entities.Usuarios;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 *
 * @author parra
 */

@Named
@SessionScoped
public class LoginView implements Serializable{
    private String email;
    private String password;
    private Usuarios user;
    @Inject
    private UserEJB userEJB;
    
    
    public Usuarios getAuthenticatedUser() {
        return user;
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

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }
    
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login incorrecto!", null));
            return "login";
        }
        this.user = userEJB.findByEmail(request.getUserPrincipal().getName());
        if (request.isUserInRole("cliente")) {
            return "/cliente/listadoReservas.xhtml?faces-redirect=true";
        } else if (request.isUserInRole("empresa")) {
            return "/empresa/listadoHoteles.xhtml?faces-redirect=true";
        } else {
            return "login";
        }
    }
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            System.out.println("Fallo durante el proceso de logout!");
        }
        return "/index?faces-redirect=true";
    }
}
