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
public class AllStm implements Istm{
    private String var;
    private exp e;
    
    public AllStm(String v, exp ee){
    var=v; e=ee;
    }
    
    @Override
    public String to_string(){
    return "new("+var+","+e.to_string()+")";
    }
    
    public String get_v(){return var;}
    
    public exp get_exp(){return e;}
    
    @Override
   public ProgState execute(ProgState st)throws expression.Exception{
        try{
        Idictionary symtable=st.get_symtable();
        Iheap h=st.get_heap();
        if( !(h.is_in(e.eval(symtable,h)))){
            int n=h.add(e.eval(symtable,h));
            symtable.add(var,n);
        }
        
        return null;
        }catch(expression.Exception exp){throw exp;}
    }
}
