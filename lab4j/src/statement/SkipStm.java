/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statement;

import domain.ProgState;
import tools.Istack;

/**
 *
 * @author Denisa
 */
public class SkipStm implements Istm {
    
    @Override
    public String to_string(){
    return "SKIP";
    }
    
    @Override
   public ProgState execute(ProgState st){
        return null;

    }
    
}
