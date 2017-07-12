/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Denisa
 * @param <T>
 */
public interface Ilist<T> {
        /*The method adds an object to the list
    pre=a is an object
    post=a is added to the list
    */
    public void add(T a);
    
    /*Removes the object a from the list if it exists
    pre-a is in the list
    post-list without the object a
    */
    public void remove(T a);
    
    
    /*
    Verifies if the object a is in the list
    pre-object a
    post- returns a boolean value corresponding to the calculations
    */
    public boolean is_in(T a);
    
    
    /**
     * returns the lenght of the list
     * @return 
     */
    public int get_lenght();
    
    
    /**
     * returns the object the object from position i
     * @param i
     * @return 
     */
    public Object get_object(int i);
}
