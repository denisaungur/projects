/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import expression.exp;
import domain.ProgState;
import java.io.Serializable;
import tools.Idictionary;
import tools.Iheap;
import tools.Ilist;
import tools.Istack;
/**
 *
 * @author Denisa
 */
public class PrintStm implements Istm,Serializable{
   private exp e;
   private static final long serialVersionUID = 1L;
   public PrintStm(exp e1){e=e1;}
   
   public PrintStm(){}
   
   public exp get_exp(){return e;}
   
   @Override
   public String to_string(){
   return "print("+e.to_string()+")";}
   
   @Override
   public ProgState execute(ProgState st)throws expression.Exception{
        try{
        exp e=get_exp();
        Ilist list=st.get_output();
        Idictionary symtable=st.get_symtable();
        Iheap h=st.get_heap();
        int value=e.eval(symtable,h);
        list.add(value);
        return null;
        }catch(expression.Exception exp){throw exp;}
    }
   
    
    
}
