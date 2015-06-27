/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;

/**
 *
 * @author Manuel
 */
public class NodoToken {
    public String token;
    public String descripcion;
    public NodoToken siguiente;
    public NodoToken anterior;
    
    public NodoToken(String tokn, String descri, NodoToken sig, NodoToken ant){
        token = tokn;
        descripcion = descri;
        siguiente = sig;
        anterior = ant;
    }
    
    public NodoToken getSiguiente(){
        return siguiente;
    }
    
    public NodoToken getAnterior(){
        return anterior;
    }
    
    public void setSiguiente(NodoToken siguiente){
        this.siguiente = siguiente;
    }
    
    public void setAnterior(NodoToken anterior){
        this.anterior = anterior;
    }
    
    public void setToken(String token){
        this.token = token;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getToken(){
        return token;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
}
