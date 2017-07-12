/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import domain.ProgState;
import expression.exp;
import tools.Idictionary;
import tools.Iheap;
import tools.Ilist;
import tools.Istack;
/**
 *
 * @author Denisa
 */
public class IfStm implements Istm {
   private exp a;
   private Istm s1;
   private Istm s2;
           
   public IfStm(exp e, Istm s,Istm ss){a=e;s1=s;s2=ss;}
   
   public IfStm(exp e, Istm s){
   a=e; s1=s; s2=null;
   }
   
   public exp get_exp(){return a;}
   
   public Istm get_then(){return s1;}
   
   public Istm get_else(){return s2;}
   
   public void set_else(){s2=new SkipStm();}
   
   @Override
    public String to_string(){
    if( s2==null){
    return "IF("+a.to_string()+") THEN "+s1.to_string();
    }
    return "IF("+a.to_string()+") THEN "+s1.to_string()+" ELSE "+s2.to_string();
    }       
    
   @Override
    public ProgState execute(ProgState st)throws expression.Exception{
        try{
        exp exp=get_exp();
        Istm then=get_then();
        Istm els=get_else();
        if( els==null){
        set_else();
        st.get_stack().push(this);
        }
        Idictionary symtable=st.get_symtable();
        Iheap h=st.get_heap();
        if ( exp.eval(symtable,h)!= 0){
            if(then instanceof AsgStm){
            AsgStm then1=(AsgStm)then;
            exp e=then1.get_exp();
            String id=then1.get_id();
            int value=e.eval(symtable,h);
            symtable.add(id, value);
            }
            if(then instanceof PrintStm){
            PrintStm then1=(PrintStm)then;
            exp e=then1.get_exp();
            Ilist list=st.get_output();
            int value=e.eval(symtable,h);
            list.add(value);
            }
            
        }else{
            if(els instanceof AsgStm){
            AsgStm then1=(AsgStm)els;
            exp e=then1.get_exp();
            String id=then1.get_id();
            int value=e.eval(symtable,h);
            symtable.add(id, value);
            }
            if(els instanceof PrintStm){
            PrintStm then1=(PrintStm)els;
            exp e=then1.get_exp();
            Ilist list=st.get_output();
            int value=e.eval(symtable,h);
            list.add(value);
            }}
            return null;
        }catch(expression.Exception exp){throw exp;}

    }
}
