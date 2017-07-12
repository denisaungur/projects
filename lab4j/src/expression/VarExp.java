/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;
import java.io.Serializable;
import tools.*;
/**
 *
 * @author Denisa
 */
public class VarExp extends exp implements Serializable{
    private String id;
//    private int value;
    public VarExp(String i){id=i;}
     private static final long serialVersionUID = 1L;       
    
    @Override
    public Integer eval(Idictionary<String,Integer> table,Iheap<Integer> h) throws expression.Exception{ 
        Integer i=table.find(id);
        if ( !(i instanceof Integer)){
        throw new expression.Exception(id+" not in the dictionary");
        }
        return i;}
    
    @Override
    public String to_string(){return id;}
    
}
