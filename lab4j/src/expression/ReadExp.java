/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tools.*;
/**
 *
 * @author Denisa
 */
public class ReadExp extends exp{
   private Integer ob;
    
   public void set_ob(int nr){ob=nr;}
   
   public ReadExp(){}
   
   @Override
   public Integer eval(Idictionary<String,Integer> d,Iheap<Integer> h){
       System.out.println("Enter the value:");
       Scanner s=new Scanner(System.in);
       while(!(s.hasNextInt())){
           System.out.println("Please enter an integer.");
            s=new Scanner(System.in);
       }
       ob=s.nextInt();
//       ReadFrame f=new ReadFrame();
//       f.setVisible(true);
////       ob=f.get_nr();
//       JOptionPane p=new JOptionPane();
//       p.setLocation(0, 1);
//      p.showMessageDialog(f, "helloo");
//       System.out.println(ob);
       return ob;    
     }
   
   @Override
   public String to_string(){
   return "read()";
   }
}
