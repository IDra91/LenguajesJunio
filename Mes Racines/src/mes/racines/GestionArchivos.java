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
        if((s.lista.contains(s.ID.indexOf(i)))||(s.lista.contains(s.PalabraReservada.indexOf(i)))||(s.lista.contains(s.vars.indexOf(i)))){
            return true;
        }
        return false;
    }
   
    public String Retorno(int i){
        if(s.lista.contains(s.ID.indexOf(i))){
            return "ID";
        } else if(s.lista.contains(s.vars.indexOf(i))){
            return "Variables";
        } else{
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
            printwriter.println("<right><img src=\"C:\\Users\\Manuel\\Documents\\NetBeansProjects\\Mes Racines\\src\\mes\\racines\\usac.jpg\"></right>");
            printwriter.println("<left><h1><font color =\"green\">Universidad de San Carlos de Guatemala</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Facultad de Ingeniería</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Escuela de ciencias</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Ingeniería en Ciencias y Sistemas</font></h1></left>");
            printwriter.println("<left><h1><font color =\"green\">Lenguajes Formales y de Programación</font></h1></left>");
            printwriter.println("<Table border = 1 Width= 300");
            printwriter.println("<td width = 100> No. Token </td> <td width = 100> Token </td> <td width = 100> Lexema </td> <td width = 100> Palabra Reservada </td> <td width = 100> Tipo </td>");
            for(int i = 0; i<s.lista.size(); i++ ){
               printwriter.println("<td width = 100>"+ s.lista.indexOf(i) +"</td> <td width = 100>"+ this.Retorno(i) + "</td> <td width = 100>" + s.lista.get(i) + "</td> <td width = 100> Token" + this.Verificar(i) + "</td>");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } catch(Exception e){
            
        }
    }
}

    
