/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;

import domain.ProgState;
import expression.exp;
import tools.Iheap;
import tools.Istack;

/**
 *
 * @author Denisa
 */
public class SwitchStm implements Istm{
    private exp e;
    private Istm case1;
    private exp e1;
    private Istm case2;
    private exp e2;
    private Istm def;
    
    public SwitchStm(exp ee,exp ee1, Istm c,exp ee2,Istm cc, Istm d){
    e=ee; case1=c; case2=cc; def=d; e1=ee1; e2=ee2;
    }
    
    public exp get_exp(){ return e;}
    
    public Istm get_c1(){ return case1;}
    
    public exp get_exp1(){return e1;}
    
    public Istm get_c2(){ return case2;}
    
    public exp get_exp2(){ return e2;}
    
    public Istm get_def(){ return def;}
    
    @Override
    public String to_string() 
    {
    return "Switch:"+e.to_string()+" case"+e1.to_string()+": "+case1.to_string()+" case"+e2.to_string()+": "+case2.to_string()+" DEFAULT:"+def.to_string();
    }
    
    @Override
   public ProgState execute(ProgState st)throws expression.Exception{
        try{
        Iheap h=st.get_heap();
        int e=get_exp().eval(st.get_symtable(),h);
        int e1=get_exp1().eval(st.get_symtable(),h);
        int e2=get_exp2().eval(st.get_symtable(),h);
        Istm s1=get_c1();
        Istm s2=get_c2();
        Istm d=get_def();
        if( e==e1){st.get_stack().push(s1);}
        if( e==e2){st.get_stack().push(s2);}
        else{st.get_stack().push(d);}
       return null;
        }catch(expression.Exception exp){throw exp;}
    }
}