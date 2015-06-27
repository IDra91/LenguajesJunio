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
public class NodoPersona {
    public int ID;
    public String Nombre;
    public int Edad;
    String Parentesco;
    public NodoPersona siguiente;
    public NodoPersona anterior;
    
    public NodoPersona(int id, String nombre, int edad, String parentesco, NodoPersona sig, NodoPersona ant){
        ID = id;
        Nombre = nombre;
        Edad = edad;
        Parentesco = parentesco;
        siguiente = sig;
        anterior = ant;
    }
    
    public NodoPersona getAnterior(){
        return anterior;
    }
    
    public NodoPersona getSiguiente(){
        return siguiente;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public int getEdad(){
        return Edad;
    }
    
    public String getParentesco(){
        return Parentesco;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public void setEdad(int Edad){
        this.Edad = Edad;
    }
    
    public void setParentesco(String Parentesco){
        this.Parentesco = Parentesco;
    }
    
    public void setSiguiente(NodoPersona Siguiente){
        this.siguiente = Siguiente;
    }
    
    public void setAnterior(NodoPersona Anterior){
        this.anterior = Anterior;
    }
}
