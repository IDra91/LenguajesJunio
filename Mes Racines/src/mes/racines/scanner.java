/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;

import java.util.LinkedList;
import javax.swing.JOptionPane;

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
    String nombre;
    String parentesco;
    int id;
    int edad;
    
    int arbol;
    int encabezado;
    int variables;
    int contador = 0;
    int link;
    ListaToken lt = new ListaToken();
    ListaPersona lp = new ListaPersona();
    public scanner(){
        
        
    }
    
    
    public void ScannerGeneral(String analisis){
        char caracter;
        int estadoActual = 0;
        boolean aceptacion = false;
        while(!aceptacion){
            caracter = cadena.charAt(contador);
            switch(estadoActual){
                case 0:
                    if(Comienzo(cadena)){
                        estadoActual = 1;
                    } else{
                        estadoActual = -1;
                    } break;
                case 1:
                    if((Portada(cadena))||(Variables(cadena))||Arbol(cadena)){
                        estadoActual = 2;
                    } else{
                        estadoActual = -1;
                    } break;
                case 2:
                    if(Comienzo(cadena)){
                        estadoActual = 3;
                    } else{
                        estadoActual = -1;
                    } break;
                case 3:
                    if((Portada(cadena))||(Variables(cadena))||Arbol(cadena)){
                        estadoActual = 4;
                    } else{
                        estadoActual = -1;
                    } break;
                case 4:
                    if(Comienzo(cadena)){
                        estadoActual = 5;
                    } else{
                        estadoActual = -1;
                    } break;
                case 5:
                    if((Portada(cadena))||(Variables(cadena))||Arbol(cadena)){
                        estadoActual = 6;
                        contador --;
                    } else{
                        estadoActual = -1;
                        contador --;
                    } break;
                case 6:
                    aceptacion = true;
                    JOptionPane.showMessageDialog(null, "Felicidades, la cadena es correcta y se han almacenado los datos correctamente :D.");
            }
        }
    }
    
    
    public boolean Portada(String analisis){
        String aux = "";
        String port = "";
        String xxx = "";
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0;
        String dim = "";
        int flag, flag1, flag2, flag3, flag4 = 0;
        if(encabezado == 1){
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
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 1;
                            aux = aux + caracter;
                        } else if(ParentesisA(caracter)){
                            estadoActual = 2;
                        } else if(llaveCerrada(caracter)){
                            aceptacion = true;
                            encabezado = 0;
                            contador --;
                            return aceptacion;
                        } 
                        else{
                            estadoActual = 1;
                        } break;
                    case 2:
                        if((aux.toUpperCase().equals("Imagen"))||(aux.toUpperCase().equals("IMAGEN"))||(aux.toUpperCase().equals("imagen"))){
                           lt.InsertarToken(aux, "Palabra reservada");
                           aux = "";
                            estadoActual = 3; 
                        } else if((aux.toUpperCase().equals("Texto"))||(aux.toUpperCase().equals("TEXTO"))||(aux.toUpperCase().equals("texto"))){
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                            estadoActual = 9;
                        } else if((aux.toUpperCase().equals("Negrita"))||(aux.toUpperCase().equals("NEGRITA"))||(aux.toUpperCase().equals("negrita"))){
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                            estadoActual = 14;
                        } else if((aux.toUpperCase().equals("Cursiva"))||(aux.toUpperCase().equals("CURSIVA"))||(aux.toUpperCase().equals("cursiva"))){
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                            estadoActual = 14;
                        } else if((aux.toUpperCase().equals("Subrayado"))||(aux.toUpperCase().equals("SUBRAYADO"))||(aux.toUpperCase().equals("subrayado"))){
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                            estadoActual = 14;
                        } break;
                    case 3:
                        if(Comillas(caracter)){
                            estadoActual = 4;
                        } else{
                            estadoActual = 3;
                        } break;
                    case 4:
                        if(Hiper(cadena)){
                            estadoActual = 5;
                        } else{
                            estadoActual = 4;
                        }break;
                    case 5:
                        if(Comillas(caracter)){
                            estadoActual = 6;
                        } else{
                            estadoActual = 5;
                        }break;
                    case 6:
                        if(caracterNumerico(caracter)){
                            estadoActual = 7;
                            dim = dim + caracter;
                        } else{
                            estadoActual = 6;
                        } break;
                    case 7:
                        if(caracterNumerico(caracter)){
                            estadoActual = 7;
                            dim = dim + caracter;
                        } else if(Coma(caracter)){
                            estadoActual = 7;
                        } else if(ParentesisC(caracter)){
                            estadoActual = 8;
                        } else{
                            estadoActual = 7;
                        } break;
                    case 8:
                        if(PuntoComa(caracter)){
                            estadoActual = 0;
                        } else{
                            estadoActual = 8;
                        } break;
                    case 9:
                        if(Comillas(caracter)){
                            estadoActual = 10;
                        } else{
                            estadoActual = 9;
                        } break;
                    case 10:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 11;
                            port = port + caracter;
                        } else{
                            estadoActual = 10;
                        } break;
                    case 11:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 11;
                            port = port + caracter;
                        } else if(Espacios(caracter)){
                            estadoActual = 11;
                            port = port + caracter;
                        } else if(Coma(caracter)){
                            estadoActual = 11;
                            port = port + caracter;
                        } else if(Comillas(caracter)){
                            estadoActual = 12;
                            lt.InsertarToken(port, "ID");
                        } else{
                            estadoActual = 11;
                            port = port + caracter;
                        } break;
                    case 12:
                        if(ParentesisC(caracter)){
                            estadoActual = 13;
                        } else{
                            estadoActual = 12;
                        } break;
                    case 13:
                        if(PuntoComa(caracter)){
                            estadoActual = 0;
                        } else{
                            estadoActual = 13;
                        } break;
                    case 14:
                        if(ParentesisA(caracter)){
                            estadoActual = 15;
                        } else{
                            estadoActual = 14;
                        } break;
                    case 15:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 16;
                           xxx = xxx + caracter;
                        } else{
                            estadoActual = 15;
                        } break;
                    case 16:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 16;
                            xxx = xxx + caracter;
                        } else if(ParentesisC(caracter)){
                            estadoActual = 17;
                            lt.InsertarToken(xxx, "ID");
                        } break;
                    case 17:
                        if(PuntoComa(caracter)){
                            estadoActual = 0;
                        } else{
                            estadoActual = 17;
                        }
                }
            }
        }
        return aceptacion;
    }
    
    
    public boolean Variables(String analisis){
        String aux = "";
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0; 
        int var = 0;
        int valor = 0;
        String var2 = "";
        String cad = "";
              int  flag = 0; 
              int flag1 = 0;
        if(variables == 1){
            for(contador = contador; contador<cadena.length() ; contador++){
                caracter = cadena.charAt(contador);
                switch(estadoActual){
                    case 0:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 1;
                            var2 = var2 + caracter;
                        } else if(llaveCerrada(caracter)){
                            aceptacion = true;
                            variables = 0;
                            contador --;
                            return aceptacion;
                        }  
                        
                        else{
                            estadoActual = 0;
                        } break;
                    case 1:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 1;
                            var2 = var2 + caracter;
                        } else if(caracterNumerico(caracter)){
                            estadoActual = 1;
                            var2 = var2 + caracter;
                        } else if(Coma(caracter)){
                            estadoActual = 1;
                        } else if(DosPuntos(caracter)){
                            estadoActual = 2;
                        } else{
                            estadoActual = 1;
                        } break;
                    case 2:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 3;
                            aux = aux + caracter;
                        } else{
                            estadoActual = 2;
                        } break;
                    case 3:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 3;
                            aux = aux + caracter;
                        } else if(DosPuntos(caracter)){
                            estadoActual = 4;
                        } 
                        else{
                            estadoActual = 3;
                        } break;
                    case 4:
                        if((aux.toUpperCase().equals("entero"))||(aux.toUpperCase().equals("ENTERO"))||(aux.toUpperCase().equals("Entero"))){
                            estadoActual = 5;
                            flag = 1;
                        } else if((aux.toUpperCase().equals("Cadena"))||(aux.toUpperCase().equals("CADENA"))||(aux.toUpperCase().equals("cadena"))){
                            estadoActual = 6;
                            flag1 = 1;
                        } else{
                            estadoActual = 4;
                        }break;
                    case 5:
                        if(Igual(caracter)){
                            estadoActual = 5;
                        } else if(caracterNumerico(caracter)){
                            estadoActual = 5;
                            valor = valor+ caracter;
                        } else if(PuntoComa(caracter)){
                            estadoActual = 0;
                            var = valor;
                        } else{
                            estadoActual = 5;
                        } break;
                    case 6:
                        if(Comilla(caracter)){
                            estadoActual = 7;
                        } else {
                            estadoActual = 6;
                        } break;
                    case 7:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 7;
                            cad = cad + caracter;
                        } else if(Espacios(caracter)){
                            estadoActual = 7;
                            cad = cad + caracter;
                        }
                        else if(Comilla(caracter)){
                            estadoActual = 8;
                            var2 = cad;
                        } else{
                            estadoActual = 7;
                        } break;
                    case 8:
                        if(PuntoComa(caracter)){
                            estadoActual = 0;
                        }
                }
            }
        }
      
        return aceptacion;
    }
    
    
    public boolean Arbol(String analisis){
        String aux = "";
        
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0;
        int flag = 0;
        int flag1 = 0;
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
                        
                        
                        
                    }
                else{
                       estadoActual = 1;         
                                } break;
                case 2:
                    if(llaveAbierta(caracter)){
                        
                        if((aux.toUpperCase().equals("Persona"))||(aux.toUpperCase().equals("PERSONA"))||(aux.toUpperCase().equals("persona"))){
                            estadoActual = 3;
                            flag = 1;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        } else if((aux.toUpperCase().equals("Relación"))||(aux.toUpperCase().equals("RELACIÓN"))||(aux.toUpperCase().equals("relación"))){
                            estadoActual = 3;
                            flag1 = 1;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                            
                        }
                    } else{
                        estadoActual = 2;
                    } break;
                case 3:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 4;
                        aux = aux+caracter;
                    } else if(llaveCerrada(caracter)){
                    aceptacion = true;
                    arbol = 0;
                    contador --;
                    return true;
                    } 
                    else{
                        estadoActual = 3;
                    }break;
                case 4:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 4;
                        aux = aux + caracter;
                    } else if(DosPuntos(caracter)){
                        
                        if((aux.toUpperCase().equals("ID"))||(aux.toUpperCase().equals("id"))||(aux.toUpperCase().equals("Id"))){
                            estadoActual = 5;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        } else if((aux.toUpperCase().equals("Nombre"))||(aux.toUpperCase().equals("NOMBRE"))||(aux.toUpperCase().equals("nombre"))&&(flag1 == 0)){
                            estadoActual = 7;
                           lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        } else if((aux.toUpperCase().equals("Edad"))||(aux.toUpperCase().equals("edad"))||(aux.toUpperCase().equals("EDAD"))&&(flag1 == 0)){
                            estadoActual = 10;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        } else if((aux.toUpperCase().equals("Parentesco"))||(aux.toUpperCase().equals("parentesco"))||(aux.toUpperCase().equals("PARENTESCO"))&&(flag1 == 0)){
                            estadoActual = 12;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        }else if((aux.toUpperCase().equals("HIJOS"))||(aux.toUpperCase().equals("Hijos"))||(aux.toUpperCase().equals("hijos"))&&(flag1 == 1)){
                            estadoActual = 15;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        } else if((aux.toUpperCase().equals("Hermanos"))||(aux.toUpperCase().equals("hermanos"))||(aux.toUpperCase().equals("HERMANOS"))&&(flag1 == 1)){
                            estadoActual = 15;
                            lt.InsertarToken(aux, "Palabra reservada");
                            aux = "";
                        }
                    } else{
                        estadoActual = 4;
                    } break;
                case 5:
                    if(caracterNumerico(caracter)){
                        estadoActual = 6;
                        id = id + caracter; 
                    } else{
                        estadoActual = 5;
                    } break;
                case 6:
                    if(caracterNumerico(caracter)){
                        estadoActual = 6;
                        id = id + caracter;
                        
                    } else if(PuntoComa(caracter)){
                        estadoActual = 3;
                    }
                     else{
                        estadoActual = 6;
                    } break;
                
                case 7:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 8;
                        nombre = nombre + caracter;
                    } else{
                        estadoActual = 7;
                    } break;
                case 8:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 9;
                        nombre = nombre + caracter;
                    } else if(Comillas(caracter)){
                        estadoActual = 9;
                         
                    } else if(Espacios(caracter)){
                        estadoActual = 8;
                    }else{
                        estadoActual = 9;
                    } break;
                case 9:
                    if(PuntoComa(caracter)){
                        estadoActual = 3;
                    } else{
                        estadoActual = 9;
                    } break;
                
                case 10:
                    if(caracterNumerico(caracter)){
                        estadoActual = 11;
                        edad = edad + caracter;
                       
                    } else{
                        estadoActual = 10;
                    } break;
                case 11:
                    if(caracterNumerico(caracter)){
                        estadoActual = 11;
                        edad = edad + caracter;
                        
                    } else if(PuntoComa(caracter)){
                        estadoActual = 3;
                    } else{
                        estadoActual = 11;
                    } break;
               
                case 12:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 13;
                       parentesco = parentesco + caracter;
                    } else{
                        estadoActual = 12;
                    } break;
                case 13:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 13;
                        parentesco = parentesco + caracter;
                    } else if(PuntoComa(caracter)){
                        estadoActual = 14;
                    } else if(Espacios(caracter)){
                        estadoActual = 13;
                        parentesco = parentesco + caracter;
                    }
                    else{
                        estadoActual = 13;
                    } break;
                case 14:
                    if(llaveCerrada(caracter)){
                      estadoActual = 0;
                      lp.InsertarPersona(id, nombre, edad, parentesco);
                      id = 0;
                      nombre = "";
                      edad = 0;
                      parentesco = "";
                      flag = 0;
                    } else{
                        estadoActual = 14;
                    } break;
                case 15:
                    if(ParentesisA(caracter)){
                        estadoActual = 16;
                    } else{
                        estadoActual = 15;
                    }
                break;
                case 16:
                    if(caracterNumerico(caracter)){
                        estadoActual = 17;
                        id = id + caracter;
                    } else{
                        estadoActual = 16;
                    } break;
                case 17:
                    if(caracterNumerico(caracter)){
                        estadoActual= 17;
                        id = id + caracter;
                    } else if(ParentesisC(caracter)){
                        estadoActual = 18;
                    } else{
                        estadoActual = 17;
                    } break;
                case 18: 
                    if(DosPuntos(caracter)){
                        estadoActual = 19;
                    } else{
                        estadoActual = 18;
                    } break;
                case 19:
                    if(caracterNumerico(caracter)){
                        estadoActual = 20;
                        //Agregar a una cadena
                    } else{
                        estadoActual = 19;
                    } break;
                case 20:
                    if(caracterNumerico(caracter)){
                        estadoActual = 20;
                        //Agregar a una cadena
                    } else if(Coma(caracter)){
                        estadoActual = 20;
                    } else if(PuntoComa(caracter)){
                        estadoActual = 3;
                    } break;
                case 666:
                    
                    
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
                        lt.InsertarToken(comienzo, "Palabra reservada");
                        comienzo = "";
                        
                        return true;
                        
                    } else if((comienzo.toUpperCase().equals("Variables"))||(comienzo.toUpperCase().equals("VARIABLES"))||(comienzo.toUpperCase().equals("variables"))){
                        variables = 1;
                        aceptacion = true;
                        lt.InsertarToken(comienzo, "Palabra reservada");
                        comienzo = "";
                        
                        return true;
                    } else if((comienzo.toUpperCase().equals("Arbol"))||(comienzo.toUpperCase().equals("ARBOL"))||(comienzo.toUpperCase().equals("arbol"))){
                        arbol = 1;
                        aceptacion = true;
                        lt.InsertarToken(comienzo, "Palabra reservada");
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
    
    public boolean Igual(char valor){
        if(valor==61){
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
    
    public boolean ParentesisA(char valor){
        if(valor == 40){
            return true;
        } else 
            return false;
    }
    public boolean ParentesisC(char valor){
        if(valor == 41){
            return true;
        } else 
            return false;
    }
    
    public boolean Comilla(char valor){
        if(valor== 39){
            return true;
        } else
            return false;
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
    public boolean Coma(char valor){
        if(valor == 44 ){
            return true;
        } else 
            return false;
    }
    public boolean Punto(char valor){
        if(valor == 46){
            return true;
        } else 
            return false;
    }
}
