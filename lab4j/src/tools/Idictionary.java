/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.util.*;
/**
 *
 * @author Denisa
 */
public interface Idictionary<K,T> {
        /**
     * Adds a new pair to the dictionary, if the id is not already there
     * updates the value from the id , if it already exists
     * @param id
     * @param value 
     */
    public void add(K id,T value);
    
    /**
     * verifies if the id is in the dictionary
     * @param id
     * @return true-if it exists, false otherwise
     */
    public boolean is_in(K id);
    
    /**
     * Finds the value from the id 
    */
    public T find(K id);
    
    
    /**
     * Return the number of objects added in the dictionary
     * @return 
     */
    public int get_lenght();
    
    /**
     * returns the list of pairs
     * @return 
     */
    public Map<K,T> get_pairs();
}
