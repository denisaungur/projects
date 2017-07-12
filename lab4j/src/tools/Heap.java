/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.AbstractList;
import java.util.ArrayList;
import expression.*;
/**
 *
 * @author Denisa
 * @param <exp>
 */
public class Heap<T> implements Iheap<T>{
    private AbstractList<T> list=new ArrayList<>();
    private int l;
    
    public Heap(){
        l=0;
    }
    
    @Override
    public int add(T e){
    list.add(e);
    l++;
    return l;
    }
    
    @Override
    public int get_address(T e){
        for(int i=0;i<l;i++){
            if(list.get(i).equals(e)){
            return i+1;
            }
        }
        return 0;
    }
    
    @Override
    public boolean is_in(T e){
        return get_address(e)!=0;
    }
    
    @Override
    public int get_lenght(){
    return list.size();
    }
    
    @Override
    public T get_object(int i){
    return list.get(i);
    }
    
    @Override
    public void update(Integer a, T e){
    
     list.add(a-1, e);
    }
}

