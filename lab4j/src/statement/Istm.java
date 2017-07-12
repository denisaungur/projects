/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;
import java.io.*;
import domain.*;

/**
 *
 * @author Denisa
 */
public interface Istm extends Serializable{
    
    
    public String to_string();
    
    public ProgState execute(ProgState s)throws expression.Exception;
}
