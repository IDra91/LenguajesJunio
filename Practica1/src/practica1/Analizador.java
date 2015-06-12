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
public class Analizador {
    String cadena;
    int estadoActual;
    Boolean EstadoAceptacion1;
    Boolean EstadoAceptacion2;
    Boolean EstadoAceptacionG;
    String etiquetaA;
    String etiquetaC;
    String Nombre;
    int Edad;
    String Complexion;
    String Personalidad;
    String Sexo;
    
    public Analizador(){
        cadena = null;
        estadoActual= 0;
        EstadoAceptacion1 = false;
        EstadoAceptacion2 = false;
        EstadoAceptacionG = false;
        etiquetaA=null;
        etiquetaC=null;
        Nombre=null;
        Edad=0;
        Complexion=null;
        Personalidad=null;
        Sexo=null;
    }
    public void AnalizadorGeneral(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(analisisEtiquetaAbierto(cadena)){
                    estadoActual =1;    
                    }
                    else{
                        estadoActual = -1;
                    }
                case 1:
                    if(analisisEtiquetaAbierto(cadena)){
                    estadoActual = 2;}
                    else{
                        estadoActual = -1;
                    }
                case 2:
                    if(analisisNombre(cadena)){
                    estadoActual = 3;}
                    else{
                        estadoActual = -1;
                    }
                case 3:
                    if(analisisEtiquetaCerrado(cadena)){
                    estadoActual = 4;}
                    else{
                        estadoActual = -1;
                    }
                case 4:
                    if(analisisEtiquetaAbierto (cadena)){
                    estadoActual = 5;    
                    } else{
                        estadoActual = -1;
                    }
                    
                case 5:
                    if(analisisEdad(cadena)){
                        estadoActual = 6;
                    } else{
                        estadoActual = -1;
                    }
                case 6:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 7;
                    } else{
                        estadoActual = -1;
                    }
                case 7:
                    if(analisisEtiquetaAbierto(cadena)){
                        estadoActual = 8;
                    } else{
                        estadoActual = -1;
                    }
                case 8:
                    if(analisisComplexion(cadena)){
                        estadoActual = 9;
                    } else{
                        estadoActual = -1;
                    }
                case 9:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 10;
                    } else{
                        estadoActual = -1;
                    }
                case 10:
                    if(analisisEtiquetaAbierto(cadena)){
                        estadoActual = 11;
                    } else{
                        estadoActual = -1;
                    }
                case 11:
                    if(analisisPersonalidad(cadena)){
                        estadoActual = 12;
                    } else{
                        estadoActual = -1;
                    }
                case 12:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 13;
                    } else{
                        estadoActual = -1;
                    }
                case 13:
                    if(analisisEtiquetaAbierto(cadena)){
                        estadoActual = 14;
                    } else{
                        estadoActual = -1;
                    }
                case 14:
                    if(analisisSexo(cadena)){
                        estadoActual = 15;
                        
                    } else{
                        estadoActual = -1;
                    }
                case 15:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 16;
                    } else{
                        estadoActual = -1;
                    }
                case 16:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 17;
                    }else{ 
                        estadoActual = -1;
                    }
                case 17:
                    EstadoAceptacionG = true;
                    Lista l = new Lista();
                    l.InsertarInicio(Nombre, Edad, Complexion, Personalidad, Sexo);
                    JOptionPane.showMessageDialog(null, "El avatar se ha agregado con éxito :D");
                default:
                    JOptionPane.showMessageDialog(null, "Error léxico, la cadena no coincide con el lenguaje");
            }
        }
    }
    public boolean analisisSexo(String cadena){
        char caracter;
        int estadoActual = 0;
        boolean EstadoAceptacion = false;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Sexo = Sexo + caracter;
                    } else{
                        estadoActual = -1;
                    }
                case 1: 
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Sexo = Sexo + caracter;
                    } else if(Abierto(caracter)){
                        estadoActual = 2;
                    } else{
                        estadoActual = -1;
                    }
                case 2:
                    EstadoAceptacion = true;
                    return true;
                default:
                    return false;
            }
        } return false;
    }
    public boolean analisisComplexion(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Complexion = Complexion + caracter;
                    } else{
                        estadoActual = -1;
                    }
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Complexion = Complexion + caracter;
                    } else if(Abierto(caracter)){
                        estadoActual = 2;
                    }else{
                        estadoActual = -1;
                    }
                case 2:
                    EstadoAceptacion2 = true;
                    return true;
                default:
                    return false;
            }
        }return false;
    }
    
    public boolean analisisPersonalidad(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Personalidad = Personalidad + caracter;
                    } else{
                        estadoActual = -1;
                    }
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Personalidad = Personalidad + caracter;
                    } else if(Abierto(caracter)){
                        estadoActual = 2;
                    } else{
                        estadoActual = -1;
                    }
                case 2:
                    EstadoAceptacion2 = true;
                    return true;
                default:
                    return false;
            }
        } return false;
    }
    public boolean analisisEdad(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(caracterNumerico(caracter)){
                        estadoActual = 1;
                        Edad = Edad + caracter;
                    } else{
                        estadoActual = -1;
                    }
                case 1:
                    if(caracterNumerico(caracter)){
                        estadoActual = 1;
                        Edad = Edad + caracter;
                    } 
                    else if(Abierto(caracter)){
                        estadoActual = 2;
                    }else{
                        estadoActual = -1;
                    }
                case 2:
                    EstadoAceptacion1 = true;
                    return true;
                default: 
                    return false;
            }
        } return false;
    }
    public boolean analisisNombre(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(Comillas(caracter)){
                        estadoActual = 1;
                    } else{
                        estadoActual = -1;
                    }
                case 1:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 2;
                        Nombre = Nombre + caracter;
                    } else{
                        estadoActual = -1;
                    }
                case 2:
                    if(caracterMinuscula(caracter)){
                        estadoActual= 2;
                        Nombre = Nombre + caracter;
                    } else if(Espacios(caracter)){
                        estadoActual = 2;
                        Nombre = Nombre + caracter;
                    } else if(Comillas(caracter)){
                        estadoActual = 3;
                    } else{
                        estadoActual = -1;
                    }
                case 3:
                    EstadoAceptacion1 = true;
                    return true;
                default: 
                    return false;
            }
        } return false;
    }
    
    public boolean analisisEtiquetaCerrado(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(Abierto(caracter)){
                        estadoActual = 1;
                        etiquetaC = etiquetaC + caracter;
                    } else{
                        estadoActual = -1;
                        break;
                    }
                case 1:
                    if(Diagonal(caracter)){
                        estadoActual = 2;
                        etiquetaC = etiquetaC + caracter;
                    } else{
                        estadoActual = -1;
                        break;
                    }
                case 2:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 2;
                        etiquetaC = etiquetaC + caracter;
                    } else if(Cerrado(caracter)){
                        estadoActual = 3;
                        etiquetaC = etiquetaC + caracter;
                    } else{
                        estadoActual = -1;
                        break;
                    }
                case 3:
                    EtiquetaCerrado(etiquetaC);
                    EstadoAceptacion2 = true;
                    return true;
                default:
                    EstadoAceptacion2 = false;
            }
        }return false;
    }
    public boolean analisisEtiquetaAbierto(String cadena){
        char caracter;
        int estadoActual = 0;
        for(int i=0;i<cadena.length();i++){
            caracter = cadena.charAt(i);
            switch(estadoActual){
                case 0:
                    if(Abierto(caracter)){
                        estadoActual= 1;
                        etiquetaA = etiquetaA + caracter;
                    } else{
                        estadoActual= -1;
                        break;
                    }
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual= 1;
                        etiquetaA = etiquetaA + caracter;
                    } else if(Cerrado(caracter)){
                        estadoActual= 2;
                        etiquetaA = etiquetaA+caracter;
                    } else{
                        estadoActual = -1;
                        break;
                    }
                case 2:
                    EtiquetaAbierta(etiquetaA);
                    EstadoAceptacion1 = true;
                    return true;
                default: 
                    return false;
            }
        } return false;
    }
    
    public boolean EtiquetaAbierta(String analisis){
        switch(analisis){
            case "<avatar>":
                return true;

            case "<nombre>":
                return true;
           
            case "<edad>":
                return true;
            
            case "<complexion>":
                return true;
                
            case "<personalidad>":
                return true;
                
            case "<sexo>":
                return true;
                
            default:
                return false;
        }
        
    }
    
    public boolean EtiquetaCerrado(String analisis){
        switch(analisis){
            case "</avatar>":
                return true;

            case "</nombre":
                return true;
           
            case "</edad>":
                return true;
            
            case "</complexion>":
                return true;
                
            case "</personalidad>":
                return true;
                
            case "</sexo>":
                return true;
                
            default:
               
                return false;
               
        }
    }
    
    public boolean caracterMinuscula(char valor){
        if((valor>=97)&&(valor<=122)){
            return true;
        }
        else
            return false;
    }
    public boolean caracterMayuscula(char valor){
        if((valor>=65)&&(valor<=90)){
            return true;
        }
        else
            return false;
        }
    public boolean caracterNumerico(char valor){
        if((valor>=48)&&(valor<=57)){
            return true;
        } else
            return false;
    }
    public boolean Espacios(char valor){
        if((valor == 13)){
            return true;
        } else
            return false;
    }
    public boolean Salto(char valor){
        if((valor== 32)){
            return true;
        } else
            return false;
    }
    public boolean Abierto(char valor){
        if(valor == 60){
            return true;
        } else
            return false;
    }
    public boolean Cerrado(char valor){
        if(valor == 62){
            return true;
        } else
            return false;
    }
    public boolean Diagonal(char valor){
        if(valor == 47){
            return true;            
        } else
            return false;
    }
    
    public boolean Comillas(char valor){
        if(valor == 34){
            return true;
        } else
            return false;
    }

    
   
}
