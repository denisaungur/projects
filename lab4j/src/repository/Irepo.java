/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import domain.ProgState;
import statement.*;
import java.io.*;
/**
 *
 * @author Denisa
 */
public interface Irepo {
    
    public void set_stack(Istm s);
    
    public ProgState get_CrntState();
    
    public String to_string();
    
    public void add(ProgState s);
    
    public void serialize() throws IOException;
    
    public void deserialize() throws IOException, ClassNotFoundException;

    
    public void save() throws IOException;
    
     public java.util.List<ProgState> get_list();
     
     public void set_list(java.util.List<ProgState> l);
     
     public void set_at(ProgState p,int i);
     
     public boolean is_in(ProgState p);
     
     public java.util.List<ProgState> one_step() throws expression.Exception;
     
     public void set_l(int l);
}
