/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import expression.Exception;
/**
 *
 * @author Denisa
 */
public interface Iheap<T> {
    
    public int add(T e);
    
    public int get_address(T e);
    
     public boolean is_in(T e);
     
     public int get_lenght();
     
     public T get_object(int i);
     
      public void update(Integer a,T e);
}
