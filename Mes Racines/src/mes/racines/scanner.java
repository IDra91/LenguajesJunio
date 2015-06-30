/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel
 */
public class scanner {
   String cadena = "";
    String palabraRe = "";
    String comienzo = "";
    String finalizar = "";
    String hipervinculo = "";
    String nombre = "";
    String parentesco = "";
    int id = 0;
    int edad = 0;    
    int arbol = 0;
    int encabezado = 0;
    int variables = 0;
    int contador = 0;
    int link = 0;
    ArrayList lista = new ArrayList();
    ArrayList vars = new ArrayList();
    ArrayList padre = new ArrayList();
    ArrayList madre = new ArrayList();
    ArrayList hermano = new ArrayList();
    ArrayList PalabraReservada = new ArrayList();
    ArrayList ID = new ArrayList();
    ArrayList Segmento = new ArrayList();
    ListaPersona lp = new ListaPersona();
    
    public scanner(){
    }
    
    
    public void ScannerGeneral(String analisis){
        char caracter;
        int estadoActual = 0;
        boolean aceptacion = false;
        System.out.println("longitud:"+analisis.length());
        while(!aceptacion){
            
            caracter = analisis.charAt(contador);
            System.out.println("General-> Estado="+estadoActual+",contador="+contador+",caracter="+caracter);
                
            switch(estadoActual){
                case 0:
                    if(Comienzo(analisis)){
                        estadoActual = 1;
                    } else{
                        estadoActual = -1;
                    } break;
                case 1:
                    if((Portada(analisis))||(Variables(analisis))||Arbol(analisis)){
                        estadoActual = 2;
                    } else{
                        estadoActual = -1;
                    } break;
                case 2:
                    if(Comienzo(analisis)){
                        estadoActual = 3;
                    } else{
                        estadoActual = -1;
                    } break;
                case 3:
                    if(Variables(analisis)){
                        estadoActual = 4;
                    } else{
                        estadoActual = -1;
                    } break;
                case 4:
                    if(Comienzo(analisis)){
                        estadoActual = 5;
                    } else{
                        estadoActual = -1;
                    } break;
                case 5:
                    if(Arbol(analisis)){
                        estadoActual = 6;
                        contador--;
                        JOptionPane.showMessageDialog(null, "Felicidades, la cadena es correcta y se han almacenado los datos correctamente :D.");
                      
                    } else{
                        estadoActual = -1;
                        
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
        char caracter=' ';
        boolean aceptacion = false;
        int estadoActual = 0;
        String dim = "";
        int flag, flag1, flag2, flag3, flag4 = 0;
        if(encabezado == 1){
            for(contador = contador; contador<analisis.length();contador++){
                caracter = analisis.charAt(contador);
                System.out.println("Portada-> Estado="+estadoActual+",contador="+contador+",caracter="+caracter);
                switch(estadoActual){
                    case 0:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 1;
                            aux = aux + caracter;
                        } else{
                            if(llaveCerrada(caracter)){
                            aceptacion = true;
                            encabezado = 0;
                            contador --;
                            return aceptacion;
                        } 
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
                         if((aux.toUpperCase().equals("Texto"))||(aux.toUpperCase().equals("TEXTO"))||(aux.toUpperCase().equals("texto"))){
                            lista.add(aux.toUpperCase());
                            aux = "";
                            estadoActual = 9;
                        } else if((aux.toUpperCase().equals("Negrita"))||(aux.toUpperCase().equals("NEGRITA"))||(aux.toUpperCase().equals("negrita"))){
                            lista.add(aux.toUpperCase());
                            aux = "";
                            estadoActual = 14;
                        } else if((aux.toUpperCase().equals("Cursiva"))||(aux.toUpperCase().equals("CURSIVA"))||(aux.toUpperCase().equals("cursiva"))){
                            lista.add(aux.toUpperCase());
                            aux = "";
                            estadoActual = 14;
                        } else if((aux.toUpperCase().equals("Subrayado"))||(aux.toUpperCase().equals("SUBRAYADO"))||(aux.toUpperCase().equals("subrayado"))){
                            lista.add(aux.toUpperCase());
                            aux = "";
                            estadoActual = 14;
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
                        } else if(ParentesisA(caracter)){
                            estadoActual = 12;
                            lista.add(port.toUpperCase());
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
                            lista.add(xxx.toUpperCase());
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
        char caracter=' ';
        boolean aceptacion = false;
        int estadoActual = 0; 
        int var = 0;
        int valor = 0;
        String var2 = "";
        String cad = "";
              int  flag = 0; 
              int flag1 = 0;
        if(variables == 1){
            for(contador = contador; contador<analisis.length() ; contador++){
                caracter = analisis.charAt(contador);
                System.out.println("Variables-> Estado="+estadoActual+",contador="+contador+",caracter="+caracter);
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
                        } else if(Igual(caracter)){
                            estadoActual = 4;
                            contador--;
                        }
                        else if(PuntoComa(caracter)){
                            estadoActual = 4;
                            contador--;
                        } 
                        else{
                            estadoActual = 3;
                        } break;
                    case 4:
                        System.out.println(aux);
                        if((aux.toUpperCase().equals("entero"))||(aux.toUpperCase().equals("ENTERO"))||(aux.toUpperCase().equals("Entero"))){
                            estadoActual = 5;
                            aux="";
                            System.out.println("4,entero");
                            flag = 1;
                        } else if((aux.toUpperCase().equals("Cadena"))||(aux.toUpperCase().equals("CADENA"))||(aux.toUpperCase().equals("cadena"))){
                            System.out.println("4,cadena");
                            estadoActual = 6;
                            aux="";
                            flag1 = 1;
                        } else{
                            System.out.println("4,else");
                            estadoActual = -1;System.out.println("ERROR variable:estado4, leyendo etiqueta");
                        }
                        contador--;
                        break;
                    case 5:
                        if(Igual(caracter)){
                            estadoActual = 5;
                        } else if(caracterNumerico(caracter)){
                            estadoActual = 5;
                            valor = valor+ caracter;
                        } else if(PuntoComa(caracter)){
                            var = valor;
                            vars.add(var);
                           
                            estadoActual = 0;
                            
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
                            vars.add(cad.toUpperCase());
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
        for(contador = contador; contador<analisis.length();contador++){
            caracter = analisis.charAt(contador);
            System.out.println("Arbol-> Estado="+estadoActual+",contador="+contador+",caracter="+caracter);
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
                            lista.add(aux.toUpperCase());
                            aux = "";
                        } else if((aux.toUpperCase().equals("Relacion"))||(aux.toUpperCase().equals("RELACION"))||(aux.toUpperCase().equals("relacion"))){
                            estadoActual = 3;
                            flag1 = 1;
                            lista.add(aux.toUpperCase());
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
                            lista.add(aux.toUpperCase());;
                            aux = "";
                        } else if((aux.toUpperCase().equals("Nombre"))||(aux.toUpperCase().equals("NOMBRE"))||(aux.toUpperCase().equals("nombre"))&&(flag1 == 0)){
                            estadoActual = 7;
                           lista.add(aux.toUpperCase());;
                            aux = "";
                        } else if((aux.toUpperCase().equals("Edad"))||(aux.toUpperCase().equals("edad"))||(aux.toUpperCase().equals("EDAD"))&&(flag1 == 0)){
                            estadoActual = 10;
                            lista.add(aux.toUpperCase());
                            aux = "";
                        } else if((aux.toUpperCase().equals("Parentesco"))||(aux.toUpperCase().equals("parentesco"))||(aux.toUpperCase().equals("PARENTESCO"))&&(flag1 == 0)){
                            estadoActual = 12;
                            lista.add(aux.toUpperCase());
                            aux = "";
                        }else if((aux.toUpperCase().equals("HIJOS"))||(aux.toUpperCase().equals("Hijos"))||(aux.toUpperCase().equals("hijos"))&&(flag1 == 1)){
                            estadoActual = 15;
                            lista.add(aux.toUpperCase());
                            aux = "";
                        } else if((aux.toUpperCase().equals("Hermanos"))||(aux.toUpperCase().equals("hermanos"))||(aux.toUpperCase().equals("HERMANOS"))&&(flag1 == 1)){
                            estadoActual = 15;
                            lista.add(aux.toUpperCase());
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
                        cadena = cadena + caracter;
                    } else{
                        estadoActual = 19;
                    } break;
                case 20:
                    if(caracterNumerico(caracter)){
                        estadoActual = 20;
                        cadena = cadena + caracter;
                    } else if(Coma(caracter)){
                        estadoActual = 20;
                    } else if(PuntoComa(caracter)){
                        estadoActual = 3;
                    } break;
                
                    
                    
                default: 
                     //ir agregando a errores
        }
         
        }
       
        
        } return false;}
    
    public boolean Hiper(String analisis){
        link = 0;
        char caracter;
        boolean aceptacion = false;
        int estadoActual = 0;
        for(contador = contador; contador<analisis.length();contador++){
            caracter = analisis.charAt(contador);
            System.out.println("Hiper-> Estado="+estadoActual+",contador="+contador+",caracter="+caracter);
            switch(estadoActual){
                case 0:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 1;
                        hipervinculo = hipervinculo + caracter;
                    } else{
                        estadoActual = 0;
                    } break;
                    
                case 1:
                   if(DosPuntos(caracter)){
                       estadoActual = 2;
                       hipervinculo = hipervinculo + caracter;
                   } else{
                       estadoActual = 1;
                   } break;
                case 2:
                    if(Slash(caracter)){
                        estadoActual = 3;
                        hipervinculo = hipervinculo + caracter;
                    } else{
                        estadoActual = 2;
                    } break;
                case 3:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 4;
                        hipervinculo = hipervinculo + caracter;
                    } else{
                        estadoActual = 3;
                    } break;
                case 4:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))||(SlashInverted(caracter))){
                        estadoActual = 4;
                        hipervinculo = hipervinculo + caracter;
                    } else if(Punto(caracter)){
                        estadoActual = 5;
                        hipervinculo = hipervinculo + caracter;
                    } else{
                        estadoActual = 4;
                    } break;
                case 5:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 5;
                        hipervinculo = hipervinculo + caracter;
                    } else if(Comillas(caracter)){
                        estadoActual = 6;
                    } else{
                        estadoActual = 5;
                    } break;
                case 6:
                    lista.add(hipervinculo.toUpperCase());
                    aceptacion = true;
                    contador --;
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
        for(contador = contador; contador<analisis.length();contador++){
            caracter = analisis.charAt(contador);
            System.out.println("Comienzo-> Estado="+estadoActual+",contador="+contador+",caracter="+caracter);
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
                        lista.add(comienzo.toUpperCase());
                        comienzo = "";
                        contador --;
                        return true;
                        
                    } else if((comienzo.toUpperCase().equals("Variables"))||(comienzo.toUpperCase().equals("VARIABLES"))||(comienzo.toUpperCase().equals("variables"))){
                        variables = 1;
                        aceptacion = true;
                        lista.add(comienzo.toUpperCase());
                        comienzo = "";
                        contador --;
                        return true;
                    } else if((comienzo.toUpperCase().equals("Arbol"))||(comienzo.toUpperCase().equals("ARBOL"))||(comienzo.toUpperCase().equals("arbol"))){
                        arbol = 1;
                        aceptacion = true;
                        lista.add(comienzo.toUpperCase());
                        comienzo = "";
                        contador --;
                        return true;
                    }
            }
        }
        
        return false;
    }
    
    public boolean SlashInverted(char valor){
        if(valor == '\\'){
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
        if(valor== '’'){
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
        if((valor== '”')){
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
