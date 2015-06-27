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
public class ListaPersona {
    NodoPersona inicio;
    NodoPersona fin;
    
    public ListaPersona(){
        inicio = null;
        fin = null;
    }
    
    public void InsertarPersona(int ID, String Nombre, int Edad, String Parentesco){
        if(inicio == null){
            inicio = new NodoPersona(ID, Nombre, Edad, Parentesco, null, null);
            fin = inicio;
        } else{
            NodoPersona nuevo = new NodoPersona(ID, Nombre, Edad, Parentesco, null, inicio);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
        }
    }
}
