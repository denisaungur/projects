/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;
import tools.*;
/**
 *
 * @author Denisa
 */
public class ReadhExp extends exp {
    private String var;
    
    public ReadhExp(String v){var=v;}
    
    @Override
    public String to_string(){
    return "rH("+var+")";
    }
    
    @Override
    public Integer eval(Idictionary<String,Integer> sym,Iheap<Integer> h){
        int address= sym.find(var);
        int value=h.get_object(address-1);
        return value;
        
//        System.out.print(""+address+"DAAA");

    }
}
