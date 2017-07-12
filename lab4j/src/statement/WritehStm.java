/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import domain.ProgState;
import expression.*;
import tools.Idictionary;
import tools.Iheap;
import tools.Istack;

/**
 *
 * @author Denisa
 */
public class WritehStm implements Istm{
    private String var;
    private exp expr;
    
    public WritehStm(String v,exp e){
        var=v;
        expr=e;
    }
    
    public String get_var(){ 
        return var;
    }
    
    public exp get_exp(){ 
        return expr;
    }
    
    @Override
    public String to_string(){
        return "wH("+var+","+expr.to_string()+")";
    }
    
    @Override
   public ProgState execute(ProgState st)throws expression.Exception{
        Idictionary symtable=st.get_symtable();
        Iheap h=st.get_heap();
        Object addr=symtable.find(var);
        if(addr!=null){
        Integer a=(Integer)addr;
        h.update(a,expr.eval(symtable,h));
        return null;
        }
        else{
 //           expression.Exception ex=new expression.Exception("Memory zone not found");
            throw new expression.Exception("Memory zone not found");
        }
      
    }
}
