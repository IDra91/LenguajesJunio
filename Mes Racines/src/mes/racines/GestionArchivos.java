/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;
import java.io.*;
/**
 *
 * @author Manuel
 */
public class GestionArchivos {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    scanner s = new scanner ();
    
    public GestionArchivos(){
        
    }
    
    public String AbrirTexto(File archivo){
        String contenido = "";
        try{
            entrada = new FileInputStream(archivo);
            int ascii;
           while((ascii = entrada.read())!= -1){
               char caracter = (char)ascii;
               contenido += caracter;
           }
        }catch(Exception e){
            
        }
        return contenido;
    }
    
    public String GuardarTexto(File archivo, String contenido){
        String respuesta = null;
        try{
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se ha guardado con éxito :D";
        } catch(Exception e){
            
        }
        return respuesta;
    }
    public boolean Verificar(int i){
        if((s.lista.contains(s.ID.indexOf(i)))||(s.lista.contains(s.Errores.indexOf(i)))||(s.lista.contains(s.vars.indexOf(i)))){
            return true;
        }
        return false;
    }
   
    public String Retorno(int i){
        if(s.lista.contains(s.ID.indexOf(i))){
            return "ID";
        } else if(s.lista.contains(s.vars.indexOf(i))){
            return "Variables";
        } else if(s.lista.contains(s.Errores.indexOf(i))){
            return "Errores";
        } 
        else{
            return "";
        }
    }
    
    
    public void GenerarPagina(){
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
            System.out.println(s.lista.size()+"");
            for(int i = 0; i<s.lista.size(); i++ ){
               System.out.println("sdsdasdasdsad");
               printwriter.println("<tr><td width = 100>"+ i +"</td> <td width = 100>"+ this.Retorno(i) + "</td> <td width = 100>" + s.lista.get(i) + "</td> <td width = 100> Token" + this.Verificar(i) + "</td></tr>");
            }
            printwriter.println("</table>");
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } catch(Exception e){
            
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

    
