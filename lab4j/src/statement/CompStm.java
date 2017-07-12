/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import domain.ProgState;
import expression.Exception;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.Istack;

/**
 *
 * @author Denisa
 */
public class CompStm implements Istm,Serializable{
    private Istm first;
    private Istm second;
    private static final long serialVersionUID = 1L;
    public CompStm(Istm f,Istm s){first=f; second=s;}
    
    @Override
    public String to_string(){
    return "("+first.to_string()+";"+second.to_string()+")";
    
    }
    
    public Istm get_first(){return first;}
    
    public Istm get_second(){return second;}
    
    @Override
    public ProgState execute(ProgState st){
            st.set_stack(get_second());
            st.set_stack(get_first());
            return null;
            
    
    }
    
}
