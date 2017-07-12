/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import domain.ProgState;
import expression.*;
import java.io.Serializable;
import tools.Idictionary;
import tools.Iheap;
import tools.Istack;

/**
 *
 * @author Denisa
 */
public class WhileStm implements Istm,Serializable {
    public exp e;
    public Istm stm;
    private static final long serialVersionUID = 1L;
    public WhileStm(exp ee, Istm s){e=ee;stm=s;}
    
    public exp get_exp(){return e;}
    
    public Istm get_stm(){return stm;}
    
    public void set_exp(exp ex){e=ex;}
    
    @Override
    public String to_string(){
    return "WHILE "+e.to_string()+" "+stm.to_string();
    }
    
    @Override
   public ProgState execute(ProgState st)throws expression.Exception{
        try{
        exp e=get_exp();
        Istm stm=get_stm();
        Idictionary symtable=st.get_symtable();
        Iheap h=st.get_heap();
        if( e.eval(symtable,h)!= 0){
            st.get_stack().push(this);
            st.get_stack().push(stm);
            }
        return null;
        }catch(expression.Exception exp){throw exp;}
    }
}
