/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;

import java.util.LinkedList;

/**
 *
 * @author Manuel
 */
public class scanner {
    String cadena;
    String palabraRe;
    String comienzo;
    String finalizar;
    String hipervinculo;
    int arbol;
    int encabezado;
    int variables;
    int contador = 0;
    int link;
    LinkedList <Token> listaToken = new LinkedList <Token>();
    public scanner(){
        
    }
    
    
    
    
    public boolean Variables(String analisis){
        
        return false;
    }
    
    
    public boolean Arbol(String analisis){
        String aux = "";
        int ID = 0;
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0;
        
        if(arbol == 1){
        for(contador = contador; contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 1;
                        aux = aux + caracter;
                    } else{
                        estadoActual = 0;
                    } break;
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        aux = aux + caracter;
                    } else if(DosPuntos(caracter)){
                        estadoActual = 2;
                        //Agregar a lista
                        aux = "";
                        
                    }
                else{
                       estadoActual = 1;         
                                } break;
                case 2:
                    if(llaveAbierta(caracter)){
                        estadoActual = 3;
                    } else{
                        estadoActual = 2;
                    } break;
                case 3:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 4;
                        //Agregar a lista de tokens
                    } else{
                        estadoActual = 3;
                    }break;
                case 4:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 4;
                        //Agregar a lista de tokens
                    } else if(DosPuntos(caracter)){
                        estadoActual = 5;
                    } else{
                        estadoActual = 4;
                    } break;
                case 5:
                    if(caracterNumerico(caracter)){
                        estadoActual = 6;
                        ID = ID + caracter; 
                    } else{
                        estadoActual = 5;
                    } break;
                case 6:
                    if(caracterNumerico(caracter)){
                        estadoActual = 6;
                        ID = ID + caracter;
                        //guardar para más adelante
                    } else if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 7;
                        aux = aux + caracter;
                    } else{
                        estadoActual = 6;
                    } break;
                case 7: 
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 7;
                        aux = aux + caracter;
                    } else if(Comillas(caracter)){
                        estadoActual = 8;
                    } else{
                        estadoActual = 7;
                    } break;
                case 8:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 9;
                        aux = aux + caracter;
                    } else{
                        estadoActual = 8;
                    } break;
                case 9:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 9;
                        aux = aux + caracter;
                    } else if(Comillas(caracter)){
                        estadoActual = 10;
                         // almacenar aux en una lista y vaciar
                    } else if(Espacios(caracter)){
                        estadoActual = 9;
                    }else{
                        estadoActual = 9;
                    } break;
                case 10:
                    if(PuntoComa(caracter)){
                        estadoActual = 11;
                    } else{
                        estadoActual = 10;
                    } break;
                case 11:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 12;
                        //agregar a un string;
                    } else{
                        estadoActual = 11;
                    } break; 
                case 12:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 12;
                        //agregar a un string;
                    } else if(DosPuntos(caracter)){
                        estadoActual = 13;
                    } else{
                        estadoActual = 12;
                    } break;
                case 13:
                    if(caracterNumerico(caracter)){
                        estadoActual = 14;
                        // agregar a una cadena
                    } else{
                        estadoActual = 13;
                    } break;
                case 14:
                    if(caracterNumerico(caracter)){
                        estadoActual = 14;
                        //agregar a una cadena
                    } else if(PuntoComa(caracter)){
                        estadoActual = 15;
                    } else{
                        estadoActual = 14;
                    } break;
                case 15:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 16;
                    } else{
                        estadoActual = 15;
                    } break;
                case 16:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 16;
                        //Ir agregando a cadena
                    } else if(DosPuntos(caracter)){
                        estadoActual = 17;
                    } else{
                        estadoActual = 16;
                    } break;
                case 17:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 18;
                        //Agregar a cadena
                    } else{
                        estadoActual = 17;
                    } break;
                case 18:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 18;
                        //Agregar a cadena
                    } else if(PuntoComa(caracter)){
                        estadoActual = 19;
                    } else{
                        estadoActual = 18;
                    } break;
                case 19:
                    if(llaveCerrada(caracter)){
                      estadoActual = 20;
                    } else{
                        estadoActual = 19;
                    } break;
                case 20:
                    if(llaveCerrada(caracter)){
                    aceptacion = true;
                       return true;
                       //igualar la variable arbol a 0
                    }
                    else{
                        estadoActual = 0;
                    }
                default: 
                     //ir agregando a errores
        }
         
        }
        if (estadoActual==19) {  
            return true; //Si la cadena ya terminó, y el estado quedó en 19
            
        }else
        return false;
        
        } return false;}
    
    public boolean Hiper(String analisis){
        link = 0;
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0;
        for(contador = contador; contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 1;
                        hipervinculo = hipervinculo + caracter;
                    } else{
                        estadoActual = 0;
                    } break;
                    
                case 1:
                    //Terminar el autómata de los hipervinculos
            }
        }
        return false;
    }
    
    public boolean Comienzo(String analisis){
        arbol = 0;
        encabezado = 0;
        variables = 0;
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0;
        for(contador = contador; contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 1;
                        comienzo = comienzo + caracter;
                    } else{
                        estadoActual = 0;
                    } break;
                case 1:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 1;
                        comienzo = comienzo + caracter;
                    } else if(llaveAbierta(caracter)){
                        estadoActual = 2;
                    }else{ estadoActual = 1;
                            } break;
                case 2:
                    if((comienzo.toUpperCase().equals("Encabezado"))||(comienzo.toUpperCase().equals("ENCABEZADO"))||(comienzo.toUpperCase().equals("encabezado"))){
                        encabezado = 1;
                        aceptacion = true;
                        //Añadir a la lista de palabras reservadas
                        comienzo = "";
                        
                        return true;
                        
                    } else if((comienzo.toUpperCase().equals("Variables"))||(comienzo.toUpperCase().equals("VARIABLES"))||(comienzo.toUpperCase().equals("variables"))){
                        variables = 1;
                        aceptacion = true;
                        //Añadir a la lista de palabras reservadas
                        comienzo = "";
                        
                        return true;
                    } else if((comienzo.toUpperCase().equals("Arbol"))||(comienzo.toUpperCase().equals("ARBOL"))||(comienzo.toUpperCase().equals("arbol"))){
                        arbol = 1;
                        aceptacion = true;
                        //Añadir a la lista de palabras reservadas
                        comienzo = "";
                        
                        return true;
                    }
            }
        }
        
        return false;
    }
    
    public boolean SlashInverted(char valor){
        if(valor == 92){
            return true;
            
        } else{
            return false;
        }
    }
    public boolean PuntoComa(char valor){
        if(valor ==59){
            return true;
        } else{
            return false;
        }
    }
    
    public boolean Slash(char valor){
        if(valor==47){
            return true;
        } else{
            return false;
        }
    }
    
    public boolean DosPuntos(char valor){
        if(valor== 58){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean llaveAbierta(char valor){
        if(valor== 123){
            return true;
        } else
            return false;
    }
    public boolean llaveCerrada(char valor){
        if(valor== 125){
            return true;
        } else{
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
    public boolean Comillas(char valor){
        if((valor== 34)){
            return true;
        } else
            return false;
    }
}
