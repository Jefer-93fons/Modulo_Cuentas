/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import ec.edu.espe.arquitectura.dao.UsuarioFacade;
import ec.edu.espe.arquitectura.model.Usuario;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jefferson
 */

@ManagedBean (name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    

    
    /**
     * Creates a new instance of UsuarioController
     */
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
    public UsuarioController() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public String iniciarSesion(){
        String permisos;
        String us;
        
        String redireccion = null;
        try{
            us = usuarioFacade.iniciarSesion(usuario);

            if (us!=null){
                //Almacenar Sesión
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "protected/principal.xhtml?faces-redirect=true";
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario no Registrado",null));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario no Registrado",null));
        }
        
        return redireccion;
    } 
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
    }
    
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
       
            if (us==null){
                context.getExternalContext().redirect("/Modulo_Cuentas-web/");
            }
        } catch (Exception e) {
        }
    }
}
