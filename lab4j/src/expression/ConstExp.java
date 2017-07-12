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
public class ConstExp extends exp implements Serializable{
    private Integer number;
    private static final long serialVersionUID = 1L;
    public ConstExp(int n){number=n;}
    
    public ConstExp(){}
    
    @Override
    public Integer eval(Idictionary<String,Integer> table,Iheap<Integer> h){return number;}
   
    @Override
    public String to_string(){ return ""+number;}
}
