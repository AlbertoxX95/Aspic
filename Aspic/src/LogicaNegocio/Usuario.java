/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class Usuario {
    
    private final String idUsuario;
    private final String contrasenia;
    private final String email;
    private final String descripcion;
    private List<String> amigos;
    private List<Imagen> imagenes;
    
    public Usuario(String id, String contraseña, String email, String descripcion){
        this.idUsuario = id;
        this.contrasenia = contraseña;
        this.email = email;
        this.descripcion = descripcion;
        this.imagenes = new ArrayList<Imagen>();
        this.amigos = new ArrayList<String>(); 
    }
    
    public String getIdUsuario(){
        return this.idUsuario;
    }
    public String getContrasenia(){
        return this.contrasenia;
    }
    public String getEmail(){
        return this.email;
    }
    public String gestDescripcion(){
        return this.descripcion;
    }
    public List<Imagen> getImagenes(){
        return this.getImagenes();
    }
    public void aniadirImagen(Imagen img){
        this.imagenes.add(img);
    }
    
    
}