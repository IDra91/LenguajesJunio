/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
import java.io.*;

/**
 *
 * @author Manuel
 */
public class GestionA {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    
    public GestionA(){
        
    }
    //Abrir archivos
    public String AbrirTexto(File archivo){
        String contenido = "";
        try{
            entrada = new FileInputStream(archivo);
                int ascii;
            while((ascii = entrada.read())!= -1){
                char caracter = (char)ascii; 
                contenido += caracter;
            }
        } catch(Exception e){
            
        }
        return contenido;
    }
    
    //Guardar Texto
    public String GuardarTexto(File archivo, String contenido){
        String respuesta = null;
        
        try{
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se ha guardado con éxito :D";
        }catch(Exception e){
            
        }
        return respuesta;
        
    }
}
