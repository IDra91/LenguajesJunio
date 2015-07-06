/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.racines;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author Manuel
 */
public class Variables {
    String variable;
    String cadena;
    int valor;
    
    public Variables(String variable, String cadena, int valor){
        this.variable = variable;
        this.cadena = cadena;
        this.valor = valor;
    }
    
    public void setVariable(String variable){
        this.variable = variable;
    }
    
    public void setCadena(String cadena){
        this.cadena = cadena;
    }
    
    public void setvalor(int valor){
        this.valor = valor;
    }
    
    
    
}
