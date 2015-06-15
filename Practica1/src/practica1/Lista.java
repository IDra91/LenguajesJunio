/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import javax.swing.JOptionPane;

/**
 *
 * @author Manuel
 */
public class Lista {
    Nodo inicio;
    Nodo fin;
    
    public Lista(){
        inicio = null;
        fin = null;
    }
    
    public void InsertarInicio(String nombre, int edad, String complexion, String personalidad, String sexo){
        if(inicio == null){
            inicio = new Nodo(nombre, edad, complexion, personalidad, sexo, null, null);
            fin = inicio;
            System.out.println("Se han añadido los datos :D");
        }else{
            Nodo nuevo = new Nodo(nombre, edad, complexion, personalidad, sexo, null, inicio);
            inicio.SetAnterior(nuevo);
            inicio = nuevo;
            System.out.println("Se han añadido los datos :D");
        }
      
    }
    public String obtenerInicio(){
        String dato = inicio.getSexo();
        inicio = inicio.getSiguiente();
        if(inicio!=null){
            inicio.SetAnterior(null);            
        }
        else
            fin = null;
        return dato;
    }
    public String obtenerFin(){
        String dato = fin.getNombre();
        fin = fin.getAnterior();
        if(fin != null){
            fin.SetSiguiente(null);
        }else
            inicio = null;
        return dato;
    }
    public void BuscarPerfil(String nombre){
        Nodo temp = inicio;
        while(temp!=null){
            if(temp.getNombre().equals(nombre)){
                JOptionPane.showMessageDialog(null, "Se ha encontrado el Perfil que deseaba");
                break;
            } else{ temp = temp.getSiguiente();
            }
                JOptionPane.showMessageDialog(null, "El perfil que buscaba no existe");
        }
    }
    
    
}
