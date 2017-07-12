/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import domain.ProgState;
import expression.exp;
import java.io.Serializable;
import tools.Idictionary;
import tools.Iheap;
import tools.Istack;
/**
 *
 * @author Denisa
 */
public class AsgStm implements Istm,Serializable{
   private String id;
   private exp e;
    private static final long serialVersionUID = 1L;
   
   public AsgStm(String i,exp e1){id=i; e=e1;}
   @Override
    public String to_string(){
    return id+"="+e.to_string();
    }
    
    public exp get_exp() {return e;}
    
    public String get_id(){return id;}
    
   @Override
    public ProgState execute(ProgState st)throws expression.Exception{
        try{
        exp e=get_exp();
        String id=get_id();
        Idictionary symtable=st.get_symtable();
        Iheap h=st.get_heap();
        int value=e.eval(symtable,h);
        symtable.add(id, value);
        return null;
        }catch(expression.Exception exp){throw exp;}
    }
}
