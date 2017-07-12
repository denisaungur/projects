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
public class ArmExp extends exp implements Serializable{
    private exp e1;
    private exp e2;
    private int op;
    private static final long serialVersionUID = 1L;
    
    public ArmExp(int o,exp e, exp ee){e1=e;e2=ee;op=o;}
    @Override
    public Integer eval(Idictionary<String,Integer> table,Iheap<Integer> h)throws expression.Exception{
    if(!(e1.eval(table,h) instanceof Integer)){
    throw new Exception("Not in the dictionary");
    }
    if (op==1){return e1.eval(table,h)+e2.eval(table,h);}
    
    if(op==2){return e1.eval(table,h)-e2.eval(table,h);}

    if (op==3) {return e1.eval(table,h)*e2.eval(table,h);}
    
    return 0;
    
    }
    
    @Override
    public String to_string(){
    if (op==1){return ""+e2.to_string()+"+"+""+e1.to_string();}
    
    if(op==2){return ""+e1.to_string()+"-"+""+e2.to_string();}

    if (op==3) {return ""+e1.to_string()+"*"+""+e2.to_string();}
    
    return null;
    }
        
    
}
