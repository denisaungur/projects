/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;
import java.io.Serializable;
import tools.*;
/**
 *
 * @author Denisa
 */
public class BoolExp extends exp implements Serializable {
    private exp e1;
    private exp e2;
    String op;
    private static final long serialVersionUID = 1L;
    public BoolExp(exp e,exp ee, String o){
    e1=e; e2=ee; op=o;
    }
    
    public BoolExp(exp e,String o){
        e1=e;op=o;e2=null;
    }
    
    public exp get_exp1(){return e1;}
    
    public exp get_exp2(){return e2;}
    
    public String get_operator(){return op;}
    
    @Override
    public Integer eval(Idictionary<String,Integer> d,Iheap<Integer> h) throws expression.Exception{
        try{
    if("<".equals(op)){
        if(e1.eval(d,h)<e2.eval(d,h)){
        return 1;
        } return 0;}
    if("<=".equals(op)){
        if(e1.eval(d,h)<=e2.eval(d,h)){
        return 1;
        } return 0;        
        }
    if("==".equals(op)){
        if(e1.eval(d,h)==e2.eval(d,h)){
        return 1;
        } return 0;        
        } 
    if("!=".equals(op)){
        if(e1.eval(d,h)!=e2.eval(d,h)){
        return 1;
        } return 0;        
        } 
    if(">".equals(op)){
        if(e1.eval(d,h)>e2.eval(d,h)){
        return 1;
        } return 0;        
        }
    if(">=".equals(op)){
        if(e1.eval(d,h)>=e2.eval(d,h)){
        return 1;
        } return 0;        
        }
    if("&&".equals(op)){
        if(e1.eval(d,h)>0 && e2.eval(d,h)>0){
        return 1;
        } return 0;        
        }
    if("||".equals(op)){
        if(e1.eval(d,h)>0 || e2.eval(d,h)>0){
        return 1;
        } return 0;        
        }
    if("!()".equals(op)){
        if(e1.eval(d,h)==0){
        return 1;
        } return 0;        
        }
        }catch(expression.Exception exp){throw exp;}
        return null;
    }
    
    
    @Override
    public String to_string(){
        if(op.equals("!()")){
        return "!("+e1.to_string()+")";
        }
        return e1.to_string()+op+e2.to_string();
    }
}
