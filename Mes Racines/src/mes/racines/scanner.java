/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

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
    int counter = 0;
    int link = 0;
    public  ArrayList lista = new ArrayList <String>();
    public ArrayList  vars = new ArrayList <String>();
    public ArrayList  padre = new ArrayList <String>();
    public ArrayList  madre = new ArrayList <String>();
    public ArrayList  hermano = new ArrayList <String>();
    public ArrayList  Errores = new ArrayList <String>();
    public ArrayList ID = new ArrayList <String>();
    public ArrayList Segmento = new ArrayList <String>();
    public ArrayList  Personas = new ArrayList <String>();
    public ArrayList  portada = new ArrayList <String>();
    public ArrayList  caracteristica = new ArrayList <String>();
    public ArrayList<Variables> variable = new ArrayList<Variables>();
    ListaPersona lp = new ListaPersona();
    
   
    public scanner(){
    }
    
    //Ya se a quitado el upercase
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
                        
                      
                    } else{
                        estadoActual = -1;
                        
                    } break;
                case 6:
                    aceptacion = true;
                    JOptionPane.showMessageDialog(null, "Felicidades, la cadena es correcta y se han almacenado los datos correctamente :D.");
                    this.GenerarPagina(lista);
                    this.GenerarPDF();
                    System.out.println(padre.get(0));
                    System.out.println(madre.get(0));
                    System.out.println(hermano.get(0));
                    System.out.println(hermano.get(1));
                    System.out.println(vars.get(0));
            }
        }
    }
    
    //Ya se ha quitado el uppercase
    //Se han añadido la listas especializadas y se ha revisado que estén contando...
    public boolean Portada(String analisis){
        String aux = "";
        String port = "";
        String xxx = "";
        ArrayList auxiliar = new ArrayList <String>();
        
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
                            auxiliar.add(caracter);
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
                            auxiliar.add(caracter);
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
                            System.out.println(aux.toUpperCase());
                             lista.add(this.Concaternar(auxiliar));
                             System.out.println("Esto es lo que tiene la puta lista --->"+lista.get(1));
                             auxiliar.clear();
                            aux = "";
                            
                            estadoActual = 9;
                        } else if((aux.toUpperCase().equals("Negrita"))||(aux.toUpperCase().equals("NEGRITA"))||(aux.toUpperCase().equals("negrita"))){
                            System.out.println(aux.toUpperCase());
                            lista.add(this.Concaternar(auxiliar));
                         
                            auxiliar.clear();
                            aux = "";
                            estadoActual = 14;
                        } else if((aux.toUpperCase().equals("Cursiva"))||(aux.toUpperCase().equals("CURSIVA"))||(aux.toUpperCase().equals("cursiva"))){
                           System.out.println(aux.toUpperCase());
                            lista.add(this.Concaternar(auxiliar));
                            
                            auxiliar.clear();
                            aux = "";
                            estadoActual = 14;
                        } else if((aux.toUpperCase().equals("Subrayado"))||(aux.toUpperCase().equals("SUBRAYADO"))||(aux.toUpperCase().equals("subrayado"))){
                            System.out.println(aux.toUpperCase());
                            lista.add(this.Concaternar(auxiliar));
                            
                            auxiliar.clear();
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
                            auxiliar.add(caracter);
                            port = port + caracter;
                        } else{
                            estadoActual = 10;
                        } break;
                    case 11:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 11;
                            port = port + caracter;
                            auxiliar.add(caracter);
                        } else if(Espacios(caracter)){
                            estadoActual = 11;
                            port = port + caracter;
                            auxiliar.add(caracter);
                        } else if(Coma(caracter)){
                            estadoActual = 11;
                            port = port + caracter;
                            auxiliar.add(caracter);
                        } else if(Comillas(caracter)){
                            estadoActual = 12;
                            portada.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista de portada --->"+portada.size());
                            auxiliar.clear();
                        } else if(ParentesisA(caracter)){
                            estadoActual = 12;
                            portada.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista de portada---> "+portada.size());
                            lista.add(this.Concaternar(auxiliar));
                           
                            auxiliar.clear();
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
                           auxiliar.add(caracter);
                        } else{
                            estadoActual = 15;
                        } break;
                    case 16:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 16;
                            xxx = xxx + caracter;
                            auxiliar.add(caracter);
                        } else if(ParentesisC(caracter)){
                            estadoActual = 17;
                            caracteristica.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista de características --->"+caracteristica.size());
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Esto es lo que tiene la puta lista --->"+lista.get(2));
                            auxiliar.clear();
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
    
    //Ya se ha quitado el uppercase
    //Se han añadido las listas especializadas y se ha revisado que estén añadiéndose...
    public boolean Variables(String analisis){
        String aux = "";
        ArrayList auxiliar = new ArrayList <String>();
        ArrayList auxiliar2 = new ArrayList <String>();
        char caracter=' ';
        boolean aceptacion = false;
        int estadoActual = 0; 
        String var = "";
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
                            auxiliar.add(caracter);
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
                            auxiliar.add(caracter);
                        } else if(caracterNumerico(caracter)){
                            estadoActual = 1;
                            var2 = var2 + caracter;
                            auxiliar.add(caracter);
                        } else if(Coma(caracter)){
                            vars.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            estadoActual = 1;
                        } else if(DosPuntos(caracter)){
                            auxiliar.clear();
                            estadoActual = 2;
                        } else{
                            estadoActual = 1;
                        } break;
                    case 2:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 3;
                            aux = aux + caracter;
                            auxiliar2.add(caracter);
                        } else{
                            estadoActual = 2;
                        } break;
                    case 3:
                        if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                            estadoActual = 3;
                            aux = aux + caracter;
                            auxiliar2.add(caracter);
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
                            System.out.println(aux);
                            lista.add(this.Concaternar(auxiliar2));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar2));
                            auxiliar2.clear();
                            aux="";
                            System.out.println("4,entero");
                            flag = 1;
                        } else if((aux.toUpperCase().equals("Cadena"))||(aux.toUpperCase().equals("CADENA"))||(aux.toUpperCase().equals("cadena"))){
                            System.out.println("4,cadena");
                            System.out.println(auxiliar2.size());
                            lista.add(this.Concaternar(auxiliar2));
                            ID.add(this.Concaternar(auxiliar2));
                            auxiliar2.clear();
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
                            //Agregar método para igualar los valores a la lista de variables
                        } else if(PuntoComa(caracter)){
                            
                            System.out.println(var.toUpperCase());
                            lista.add(var);
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
                            //Agregar metodo asignar valor a la lista de variables
                            System.out.println(cad.toUpperCase());
                            
                            
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
    
    //Ya se ha quitado el uppercase
    public boolean Arbol(String analisis){
        String aux = "";        
        ArrayList auxiliar = new ArrayList <String>();
        ArrayList auxiliar2 = new ArrayList <String> ();
        char caracter;
        int counter = 0;
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
                        auxiliar.add(caracter);
                    } else{
                        estadoActual = 0;
                    } break;
                case 1:
                    if(caracterMinuscula(caracter)){
                        estadoActual = 1;
                        aux = aux + caracter;
                        auxiliar.add(caracter);
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
                            System.out.println(aux);
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            aux = "";
                        } else if((aux.toUpperCase().equals("Relacion"))||(aux.toUpperCase().equals("RELACION"))||(aux.toUpperCase().equals("relacion"))){
                            estadoActual = 3;
                            flag1 = 1;
                            System.out.println(aux);
                            lista.add(this.Concaternar(auxiliar));
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            aux = "";
                            
                        }
                    } else{
                        estadoActual = 2;
                    } break;
                case 3:
                    if((caracterMinuscula(caracter))||(caracterMayuscula(caracter))){
                        estadoActual = 4;
                        aux = aux+caracter;
                        auxiliar.add(caracter);
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
                        auxiliar.add(caracter);
                    } else if(DosPuntos(caracter)){
                        
                        if((aux.toUpperCase().equals("ID"))||(aux.toUpperCase().equals("id"))||(aux.toUpperCase().equals("Id"))){
                            estadoActual = 5;
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            aux = "";
                        } else if((aux.toUpperCase().equals("Nombre"))||(aux.toUpperCase().equals("NOMBRE"))||(aux.toUpperCase().equals("nombre"))&&(flag1 == 0)){
                            estadoActual = 69;
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                           ID.add(this.Concaternar(auxiliar));
                           auxiliar.clear();
                            aux = "";
                        } else if((aux.toUpperCase().equals("Edad"))||(aux.toUpperCase().equals("edad"))||(aux.toUpperCase().equals("EDAD"))&&(flag1 == 0)){
                            estadoActual = 10;
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            aux = "";
                        } else if((aux.toUpperCase().equals("Parentesco"))||(aux.toUpperCase().equals("parentesco"))||(aux.toUpperCase().equals("PARENTESCO"))&&(flag1 == 0)){
                            estadoActual = 12;
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            aux = "";
                        }else if((aux.toUpperCase().equals("HIJOS"))||(aux.toUpperCase().equals("Hijos"))||(aux.toUpperCase().equals("hijos"))&&(flag1 == 1)){
                            estadoActual = 15;
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
                            aux = "";
                        } else if((aux.toUpperCase().equals("Hermanos"))||(aux.toUpperCase().equals("hermanos"))||(aux.toUpperCase().equals("HERMANOS"))&&(flag1 == 1)){
                            estadoActual = 15;
                            lista.add(this.Concaternar(auxiliar));
                            System.out.println("Así va la lista --->"+lista.size());
                            ID.add(this.Concaternar(auxiliar));
                            auxiliar.clear();
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
                case 69:
                     if(Comillas(caracter)){
                        estadoActual = 7;
                    } else{
                         estadoActual = 69;
                     } break;
                case 7:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 8;
                        nombre = nombre + caracter;
                        auxiliar2.add(caracter);
                    } 
                    else{
                        estadoActual = 7;
                    } break;
                case 8:
                    if((caracterMayuscula(caracter))||(caracterMinuscula(caracter))){
                        estadoActual = 8;
                        nombre = nombre + caracter;
                        auxiliar2.add(caracter);
                        for(int i = 0; i<auxiliar.size(); i++){
                            System.out.println("Esta puta mierda tiene --->"+auxiliar2.get(i));
                        }
                    } else if(Comillas(caracter)){
                        estadoActual = 9;
                         
                    } else if(Espacios(caracter)){
                        estadoActual = 8;
                        auxiliar2.add(caracter);
                    }else{
                        estadoActual = 8;
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
                        estadoActual = 666;
                    } else if(Espacios(caracter)){
                        estadoActual = 13;
                        parentesco = parentesco + caracter;
                    }
                    else if(Coma(caracter)){
                        estadoActual = 13;
                        parentesco = parentesco + caracter;
                    }
                    else{
                        estadoActual = 13;
                    } break;
                case 666:
                    if((parentesco.toUpperCase().equals("Padre"))||(parentesco.toUpperCase().equals("padre"))||(parentesco.toUpperCase().equals("PADRE"))){
                        padre.add(this.Concaternar(auxiliar2));
                        
                        auxiliar2.clear();
                        estadoActual = 14;
                    } else if ((parentesco.toUpperCase().equals("Madre"))||(parentesco.toUpperCase().equals("madre"))||(parentesco.toUpperCase().equals("MADRE"))){
                        madre.add(this.Concaternar(auxiliar2));
                        System.out.println("Así va la lista de madres--->"+madre.size());
                        System.out.println(madre.get(0));
                        auxiliar2.clear();
                        estadoActual = 14;
                    } else if ((parentesco.toUpperCase().equals("Hijo,Hermano"))||(parentesco.toUpperCase().equals("hijo,hermano"))||(parentesco.toUpperCase().equals("HIJO,HERMANO"))){
                        hermano.add(this.Concaternar(auxiliar2));
                        System.out.println("Así va la lista --->"+hermano.size());
                        auxiliar2.clear();
                        estadoActual = 14;
                    } else{
                        Errores.add(nombre);
                        estadoActual = 14;
                    }
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
                    System.out.println(hipervinculo.toUpperCase());
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
                        System.out.println(comienzo);
                        lista.add(comienzo);
                        Segmento.add(comienzo);
                        comienzo = "";
                        contador --;
                        return true;
                        
                    } else if((comienzo.toUpperCase().equals("Variables"))||(comienzo.toUpperCase().equals("VARIABLES"))||(comienzo.toUpperCase().equals("variables"))){
                        variables = 1;
                        aceptacion = true;
                        System.out.println(comienzo);
                        lista.add(comienzo);
                        Segmento.add(comienzo);
                        comienzo = "";
                        contador --;
                        return true;
                    } else if((comienzo.toUpperCase().equals("Arbol"))||(comienzo.toUpperCase().equals("ARBOL"))||(comienzo.toUpperCase().equals("arbol"))){
                        arbol = 1;
                        aceptacion = true;
                        System.out.println(comienzo);
                        lista.add(comienzo);
                        Segmento.add(comienzo);
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

public boolean Verificar(int i){
        if((lista.contains(ID.indexOf(i)))||(lista.contains(Errores.indexOf(i)))||(lista.contains(vars.indexOf(i)))){
            return true;
        }
        return false;
    }
   
    public String Retorno(int i){
        if(lista.contains(ID.indexOf(i))){
            return "ID";
        } else if(lista.contains(vars.indexOf(i))){
            return "Variables";
        } else if(lista.contains(Errores.indexOf(i))){
            return "Errores";
        } 
        else{
            return "";
        }
    }
    
    
    public void GenerarPagina(ArrayList lista){
        FileWriter filewriter = null;
        PrintWriter printwriter = null;
        
        try{
            filewriter = new FileWriter("readme.html");
            printwriter = new PrintWriter(filewriter);
            
            printwriter.println("<html>");
            printwriter.println("<head><title> Tabla de Elementos </title></head>");
            printwriter.println("<body>");
            printwriter.println("<img src=\"usac.jpg\" width = \"200px\">");
            printwriter.println("<left><h1><font color =\"green\">Universidad de San Carlos de Guatemala</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Facultad de Ingenieria</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Escuela de ciencias</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Ingenieria en Ciencias y Sistemas</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Lenguajes Formales y de Programacion</font></h1></left>");
            printwriter.println("<Table border = 1 Width= 300>");
            printwriter.println("");
            printwriter.println("<tr><td width = 100> No. Token </td> <td width = 100> Token </td> <td width = 100> Lexema </td> <td width = 100> Palabra Reservada </td> <td width = 100> Tipo </td></tr>");
            System.out.println(lista.size()+"");
            for(int i = 0; i<lista.size(); i++ ){
              
               printwriter.println("<tr><td width = 100>"+ i +"</td> <td width = 100>"+ this.Retorno(i) + "</td> <td width = 100>" + lista.get(i) + "</td> <td width = 100> Token" + this.Verificar(i) + "</td></tr>");
            }
            printwriter.println("</table>");
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } catch(Exception e){
            
        }
    }

public String Concaternar(ArrayList  auxiliar){
    String cadena = "";
    for (int i = 0; i<auxiliar.size();i++){
        cadena = cadena + auxiliar.get(i);
        System.out.println("Así va la cadena --->"+cadena);
    }
    return cadena;
}

public void GenerarPDF(){
    Document arbolpdf = new Document();
    FileOutputStream ficheropdf;
    
    try{
        ficheropdf = new FileOutputStream("arbolgenealogico.pdf");
        PdfWriter.getInstance(arbolpdf, ficheropdf).setInitialLeading(20);
    }catch (Exception e){
        System.out.println(e.toString());
        
    }
    
    try{
        arbolpdf.open();
        arbolpdf.add(new Paragraph("Árbol Genealógico de la Familia: "));
        arbolpdf.close();
    }catch (Exception e){
        System.out.println(e.toString());
    }
}

public void Generar(){
        try{
            String dotpath= "C:\\Program Files (x86)\\Graphviz 2.28\\bin\\dot.exe";
            String fileInputPath = "grafo1.txt";
            String fileOutputPath = "arbol.jpg";
            String tParam = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotpath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            
        }
    }

public void GenerarGrafo(String padre, String madre, String hijo, String hermano){
        FileWriter fw = null;
        PrintWriter pw = null;
        try{
            fw = new FileWriter("grafo1.txt");
            pw = new PrintWriter(fw);
            pw.println("diagraph G{ ");
            pw.println(padre+";");
            pw.println(madre+";");
            pw.println(hijo+";");
            pw.println(hermano+";");
            pw.println(padre+" "+"->"+ hermano + " "+ ";");
            pw.println(madre+" "+"->"+ hermano + " "+";");
            pw.println(padre+" "+"->"+ hermano+" "+";");
            pw.println(madre+" "+"->"+ hermano + " "+ ";");
            pw.println(hermano+" "+"->"+ hermano +" "+ ";");
            pw.println("}");
            pw.close();
            
        } catch(Exception e){
            
        }
    }


}

