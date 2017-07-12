/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import controller.Controller;
import expression.Exception;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tools.*;
import statement.*;

/**
 *
 * @author Denisa
 */
public class ProgState {
    private Idictionary<String,Integer> symtable;
    private Istack<Istm> stack;
    private Ilist<Integer> output;
    private Istm prog;
    private Istack bk;
    private Iheap heap;
    private int id;
  

    
    public ProgState(int i,Idictionary<String,Integer> d,Istack<Istm> s,Ilist<Integer> l,Iheap<Integer> h,Istm state){
    symtable=d;
    stack=s;
    output=l;
    heap=h;
    stack.push(state);
    bk=new Stack();
    id=i;
    
    }
    
    public Idictionary<String,Integer> get_symtable(){return symtable;}
    
    public Istack<Istm> get_stack(){return stack;}
    
    public Ilist<Integer> get_output(){return output;}
    
    public void set_stack(Istm s){stack.push(s);}
    
    public Iheap<Integer> get_heap(){return heap;}
    
    public int get_id(){return id;}
    
    public String to_string(){
      String string= ""+get_id()+"\n"+
    "exeStack"+"\n"+
    "{";
//    Object[] list=stack.get_list();
     for(int i=stack.get_lenght()-1;i>=0;i--){
    Object o;
        o = stack.get_object(i);
 //   for (int i=0;i<stack.get_lenght();i++){
        Istm s=(Istm)o;
    string=string+s.to_string()+" | ";
    }
    string=string+"}"+
    "\n"
    +"Symbol Table \n";
//    String m = null;
    for(String key : symtable.get_pairs().keySet()){
        
        string=string+key+"->"+""+symtable.find(key)+"\n";
    }
    if(symtable.get_lenght()==0){
    string=string+"---\n";}
    string=string+"Output\n";
    for(int i=0; i<output.get_lenght();i++){
        string=string+output.get_object(i).toString()+"\n";
    }
    if (output.get_lenght()==0){
    string=string+"---\n";
    }
     string=string+"Heap\n";
    for(int i=0; i<heap.get_lenght();i++){
        System.out.println("hei");
        string=string+""+(i+1)+"->"+
       heap.get_object(i).toString()+"\n";
    }
    if (heap.get_lenght()==0){
    string=string+"---\n";
    }
    string=string+"\n";  
    
    
    return string;
    }
    
    public boolean is_not_completed(){
        return stack.get_lenght()!=0;
    }
    
    
        public ProgState one_step() {
        try{
        Istm stm=(Istm)stack.pop();
        return stm.execute(this);
        }catch(expression.Exception e){}
        return null;
        }

}
