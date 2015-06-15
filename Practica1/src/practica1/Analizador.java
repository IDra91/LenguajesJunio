/* <avatar><usuario>"Puk"</usuario><edad>99</edad><complexion>delgado</complexion><personalidad>feliz</personalidad><sexo>masculino</sexo></avatar>
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
    int contador;
    
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
        contador = 0;
    }
    public void AnalizadorGeneral(String cadena){
        char caracter;
        int estadoActual = 0;
        boolean finDeAnalisis=false;
        while(!finDeAnalisis){ //El autómata general no aumentará el contador global, de eso se encargarán los mini autómatas
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(analisisEtiquetaAbierto(cadena)){
                    estadoActual =1;    
                    }
                    else{
                        estadoActual = -1;
                    } break;
                case 1:
                    if(analisisEtiquetaAbierto(cadena)){
                    estadoActual = 2;}
                    else{
                        estadoActual = -1;
                    } break;
                case 2:
                    if(analisisNombre(cadena)){
                    estadoActual = 3;}
                    else{
                        estadoActual = -1;
                    } break;
                case 3:
                    if(analisisEtiquetaCerrado(cadena)){
                    estadoActual = 4;}
                    else{
                        estadoActual = -1;
                    } break;
                case 4:
                    if(analisisEtiquetaAbierto (cadena)){
                    estadoActual = 5;    
                    } else{
                        estadoActual = -1;
                    }
                    break;
                case 5:
                    if(analisisEdad(cadena)){
                        estadoActual = 6;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 6:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 7;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 7:
                    if(analisisEtiquetaAbierto(cadena)){
                        estadoActual = 8;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 8:
                    if(analisisComplexion(cadena)){
                        estadoActual = 9;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 9:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 10;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 10:
                    if(analisisEtiquetaAbierto(cadena)){
                        estadoActual = 11;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 11:
                    if(analisisPersonalidad(cadena)){
                        estadoActual = 12;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 12:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 13;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 13:
                    if(analisisEtiquetaAbierto(cadena)){
                        estadoActual = 14;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 14:
                    if(analisisSexo(cadena)){
                        estadoActual = 15;
                        
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 15:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 16;
                    } else{
                        estadoActual = -1;
                    }
                break;
                case 16:
                    if(analisisEtiquetaCerrado(cadena)){
                        estadoActual = 17;
                        contador--;//Como aquí ya terminó de leer la cadena, y los mini automatas dejaron la posición adelantada, lo regresamos uno para que no se salga del tamaño de la cadena
                    }else{ 
                        estadoActual = -1;
                        contador--;//Igual aquí. Sin esto, mandaría excepción al pues el charAt buscaría en una posición fuera de la cadena.
                    }
                break;
                case 17:
                    EstadoAceptacionG = true;
                    Lista l = new Lista();
                    l.InsertarInicio(Nombre, Edad, Complexion, Personalidad, Sexo);
                    JOptionPane.showMessageDialog(null, "El avatar se ha agregado con éxito :D");
                    finDeAnalisis=true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error léxico en: " +cadena.charAt(contador) +" ("+contador + "), la cadena no coincide con el lenguaje");
                    finDeAnalisis=true;
                    return;
                    
            }
        }
    }
    public boolean analisisSexo(String cadena){
        char caracter;
        int estadoActual = 0;
        boolean EstadoAceptacion = false;
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Sexo = Sexo + caracter;
                    } else{
                        estadoActual = -1;
                    } break;
                case 1: 
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Sexo = Sexo + caracter;
                    } else if(Abierto(caracter)){
                        estadoActual = 2;
                    } else{
                        estadoActual = -1;
                    } break;
                case 2:
                    contador--; //Instrucción que fue necesario poner de emergencia, ya que estabas evaluando un carater después de donde terminan los números, y necesitas dejarlo justo donde terminan.
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
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Complexion = Complexion + caracter;
                    } else{
                        estadoActual = -1;
                    }
                    break;
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Complexion = Complexion + caracter;
                    } else if(Abierto(caracter)){
                        estadoActual = 2;
                    }else{
                        estadoActual = -1;
                    }
                break;
                case 2:
                    contador--; //Instrucción que fue necesario poner de emergencia, ya que estabas evaluando un carater después de donde terminan los números, y necesitas dejarlo justo donde terminan.
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
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Personalidad = Personalidad + caracter;
                    } else{
                        estadoActual = -1;
                    } break;
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        Personalidad = Personalidad + caracter;
                    } else if(Abierto(caracter)){
                        estadoActual = 2;
                    } else{
                        estadoActual = -1;
                    } break;
                case 2:
                    contador--; //Instrucción que fue necesario poner de emergencia, ya que estabas evaluando un carater después de donde terminan los números, y necesitas dejarlo justo donde terminan.
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
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(caracterNumerico(caracter)){
                        estadoActual = 1;
                        Edad = Edad + caracter;
                    } else{
                        estadoActual = -1;
                    }
                break;
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
                break;
                case 2:
                    contador--; //Instrucción que fue necesario poner de emergencia, ya que estabas evaluando un carater después de donde terminan los números, y necesitas dejarlo justo donde terminan.
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
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(Comillas(caracter)){
                        estadoActual = 1;
                    } else{
                        estadoActual = -1;
                    } break;
                case 1:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 2;
                        Nombre = Nombre + caracter;
                    } else{
                        estadoActual = -1;
                    } break;
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
                    } break;
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
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(Abierto(caracter)){
                        estadoActual = 1;
                        etiquetaC = etiquetaC + caracter;
                    } else{
                        estadoActual = -1;
                        break;
                    } break;
                case 1:
                    if(Diagonal(caracter)){
                        estadoActual = 2;
                        etiquetaC = etiquetaC + caracter;
                    } else{
                        estadoActual = -1;
                        break;
                    } break;
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
                    } break;
                case 3:
                    EtiquetaCerrado(etiquetaC);
                    EstadoAceptacion2 = true;
                    return true;
                default:
                    EstadoAceptacion2 = false;
                    return false;
            }
        }
        if (estadoActual==3) {  
            return true; //Si la cadena ya terminó, y el estado quedó en 3
        }else
        return false;
    }
    public boolean analisisEtiquetaAbierto(String cadena){
        char caracter;
        int estadoActual = 0;
        for(contador = contador;contador<cadena.length();contador++){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(Abierto(caracter)){
                        estadoActual= 1;
                        etiquetaA = etiquetaA + caracter;
                    } else{
                        estadoActual= -1;
                        break;
                    }
                break;
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
                break;
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
                System.out.println("Etiqueta");
                return true;
                
            case "<usuario>":
                System.out.println("Etiqueta");
                return true;
           
            case "<edad>":
                System.out.println("Etiqueta");
                return true;
            
            case "<complexion>":
                System.out.println("Etiqueta");
                return true;
                
            case "<personalidad>":
                System.out.println("Etiqueta");
                return true;
                
            case "<sexo>":
                System.out.println("Etiqueta");
                return true;
                
            default:
                return false;
               
        }
        
    }
    
    public boolean EtiquetaCerrado(String analisis){
        switch(analisis){
            case "</avatar>":
                System.out.println("Etiqueta");
                return true;

            case "</usuario>":
                System.out.println("Etiqueta");
                return true;
           
            case "</edad>":
                System.out.println("Etiqueta");
                return true;
            
            case "</complexion>":
                System.out.println("Etiqueta");
                return true;
                
            case "</personalidad>":
                System.out.println("Etiqueta");
                return true;
                
            case "</sexo>":
                System.out.println("Etiqueta");
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
