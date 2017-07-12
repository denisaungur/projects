/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;

import domain.ProgState;
import java.io.Serializable;

/**
 *
 * @author Denisa
 */
public class SleepStm implements Istm,Serializable{
    
    private int nr;
    private static final long serialVersionUID = 1L;
    public SleepStm(int n){nr=n;}
    
    public String to_string() 
    {
    return "sleep("+""+nr+")";
    }
    
    public ProgState execute(ProgState st)
    {
    if(nr!=0){nr=nr-1; st.get_stack().push(this);}
    return null;
    }
}
