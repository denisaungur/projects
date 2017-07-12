/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.AbstractList;

/**
 *
 * @author Denisa
 * @param <T>
 */
public interface Istack<T> {
       /**
  * The method adds an object to the stack
  * pre:a-the object
  * post:the stack with the object a on the top of the list
  * @param a 
  */    
    public void push(T a);

   
    
    /*
    The method gets the object from the top of the list
    pre-the stack is not empty
    post-the object from the top of the stack
    */
    public Object pop()throws expression.Exception;

    
    /**
     * Returns the list of objects
     * @return 
     */
    public AbstractList<T> get_list();
    
    
    /**
     * returns the length of the stack
     * @return 
     */
    public int get_lenght();
    
    
    /**
     * Gets the object from position 1
     * @param i
     * @return 
     */
    public T get_object(int i);
    
}
