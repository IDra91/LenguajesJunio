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
public class ListaToken {
    NodoToken inicio;
    NodoToken fin;
    
    public ListaToken(){
        inicio = null;
        fin = null;
    }
    
    public void InsertarToken(String token, String descripcion){
        if(inicio == null){
            inicio = new NodoToken(token, descripcion, null, null);
            fin = inicio;
        } else{
            NodoToken nuevo = new NodoToken(token, descripcion, null, inicio);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
        }
    }
}
