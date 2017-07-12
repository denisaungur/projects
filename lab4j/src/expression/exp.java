/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;
import tools.*;
import java.io.*;
/**
 *
 * @author Denisa
 */
public abstract class exp implements Serializable {
//    public Integer eval(Idictionary<String,Integer> table)throws Exception{return 0;}
    
   public Integer eval(Idictionary<String,Integer> table,Iheap<Integer> h)throws expression.Exception{return 0;}
    
    public abstract String to_string();
}

