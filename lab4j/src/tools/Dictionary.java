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
public class Dictionary<K,T> implements Idictionary<K,T>{
    private Map<K,T> dict=new TreeMap<K,T>();
//   private Pair current;
   private int l=0;
    
    
    @Override
   public Map<K,T> get_pairs(){return dict;}
   
    @Override
    public void add(K id,T value){
   dict.put(id, value);
   l++;
    } 
    
    @Override
    public boolean is_in(K id){
    return dict.containsKey(id);
    }
    
    @Override
    public T find(K id){
        if(!(is_in(id))){
            return null;
        }
     return dict.get(id);
    }
    
    @Override
    public int get_lenght(){return l;}
}
