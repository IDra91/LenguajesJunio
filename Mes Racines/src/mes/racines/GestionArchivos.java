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
    
    public void GenerarPagina(){
        FileWriter filewriter = null;
        PrintWriter printwriter = null;
        
        try{
            filewriter = new FileWriter("readme.html");
            printwriter = new PrintWriter(filewriter);
            
            printwriter.println("<html>");
            printwriter.println("<head><title> Tabla de Elementos </title></head>");
            printwriter.println("<body>");
            printwriter.println("<right><img src=\"C:\\Users\\Manuel\\Documents\\NetBeansProjects\\Mes Racines\\src\\mes\\racines\\usac.jpg\"></right>");
            printwriter.println("<left><h1><font color =\"green\">Universidad de San Carlos de Guatemala</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Facultad de Ingeniería</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Escuela de ciencias</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Ingeniería en Ciencias y Sistemas</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Lenguajes Formales y de Programación</font></h1></left>");
            printwriter.println("");
            
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } catch(Exception e){
            
        }
    }
}

    
