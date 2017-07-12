/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;

/**
 *
 * @author Denisa
 */
public class Exception extends Throwable{
    String e;
    
    public Exception(String ss){e=ss;
    System.out.println(e);
    }
    
    public String toString(){return e;}
}
