/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.util.*;
import expression.*;

/**
 *
 * @author Denisa
 * @param <T>
 */
public class Stack<T> implements Istack<T> {
    private AbstractList<T> list=new ArrayList<>();
    private T last;
    private int l;
   
    public Stack(){
    l=0;
    }
    
    @Override
    public T pop()throws expression.Exception{
        if(l==0){
            expression.Exception e=new expression.Exception("Empty Stack.");
            throw e;}
    T e=last;
    if (l>=2){
    last=list.get(l-2);
//    System.out.println(e);
    }
    else if (l==1){
    last=null;
//    System.out.println(e);
    }
    list.remove(l-1);
    l--;

    return e;
    }
    
    
    @Override
    public void push(T a){
    list.add(a);
    l++;
    last=a;
    
    }
    
    
    public int get_lenght(){ return l;}
    
    
    public boolean is_emplty(){
    return list.isEmpty();
    
    }
    
    @Override
    public AbstractList<T> get_list(){
    return list;
    
    }
    
    @Override
    public T get_object(int i){
    return list.get(i);
    }
    
}
