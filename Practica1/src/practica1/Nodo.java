/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 *
 * @author Manuel
 */
public class Nodo {
    public String Nombre;
    public int Edad;
    public String Complexion;
    public String Personalidad;
    public String Sexo;
    public Nodo Anterior;
    public Nodo Siguiente;
    
    public Nodo(String nombre, int edad, String complexion, String personalidad, String sexo, Nodo anterior, Nodo siguiente){
        Nombre = nombre;
        Edad = edad;
        Complexion = complexion;
        Personalidad = personalidad;
        Sexo = sexo;
        Anterior = anterior;
        Siguiente = siguiente;
    }
    public Nodo getSiguiente(){
        return Siguiente;
    }
    public Nodo getAnterior(){
        return Anterior;
    }
    public void SetAnterior(Nodo anterior){
        this.Anterior = anterior;
    }
    public void SetSiguiente(Nodo siguiente){
        this.Siguiente = siguiente;
    }
    public void setNombre(String nombre){
        this.Nombre = nombre;
    }
    public void setEdad(int edad){
        this.Edad = edad;
    }
    public void setComplexion(String complexion){
        this.Complexion = complexion;
    }
    public void setPersonalidad(String personalidad){
        this.Personalidad = personalidad;
    }
    public void setSexo(String sexo){
        this.Sexo = sexo;
    }
    public String getNombre(){
        return Nombre;
    }
    public int getEdad(){
        return Edad;
    }
    public String getComplexion(){
        return Complexion;
    }
    public String getPersonalidad(){
        return Personalidad;
    }
    public String getSexo(){
        return Sexo;
    }
}

