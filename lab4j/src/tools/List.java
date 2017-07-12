/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 *
 * @author Denisa
 * @param <T>
 */
public class List<T> implements Ilist<T> {
    private AbstractList<T> list=new ArrayList<>();
    private int l;
 //   private int position;
    
    public List(){
    l=0;
    }
    
    @Override
    public void add(T a){
    list.add(a);
    l++;   
    }
    
    
    @Override
    public void remove(T a){
          list.remove(a);
          l--;
        
    }
    
    
    @Override
    public boolean is_in(T a){
    for (int i=0;i<l;i++){
        if (list.get(i)==a){
        return true;
        }
    }
    return false;
    }
    

    @Override
    public int get_lenght(){return l;}
    
    @Override
    public T get_object(int i){return list.get(i);}
}
