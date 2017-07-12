/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;

import domain.ProgState;
import java.io.Serializable;
import tools.Idictionary;
import tools.*;

/**
 *
 * @author Denisa
 */
public class ForkStm implements Istm,Serializable{
    private Istm stm;
    private static final long serialVersionUID = 1L;
    public ForkStm(Istm s){
    stm=s;
    }
    
    @Override
    public String to_string(){
        return "fork("+stm.to_string()+")";
    }
    
    @Override
    public ProgState execute(ProgState st)throws expression.Exception{
        Idictionary symtable=new Dictionary();
        for(String key : st.get_symtable().get_pairs().keySet())
        {symtable.add(key, st.get_symtable().find(key));}
        Iheap h=st.get_heap();
        Ilist out=st.get_output();
        Istack s=new Stack();
//        javax.swing.JTextArea jTextArea1=st.get_text();
        return new ProgState(st.get_id()*10,symtable,s,out,h,stm);
    }
}
