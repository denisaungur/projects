/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4j;
import controller.Controller;
import domain.ProgState;
import tools.*;
import expression.*;
import repository.Irepo;
import repository.Repo;
import statement.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Denisa
 */
public class Lab4j {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  //      test_all();
        GUI form=new GUI();
        form.setVisible(true);
        Istack<Istm> st=new Stack<>();
        Ilist<Integer> l=new List<>();
        Idictionary<String,Integer> d=new Dictionary<>();
        Iheap<Integer> h=new Heap<>();
//        Istm ex1=new IfStm(new ConstExp(2),new SwitchStm(new VarExp("a"),new ));
 //       Istm ex1=new AsgStm("x",new ReadExp());
//        Istm ex1=new SwitchStm(new ReadExp(),new ConstExp(3),new PrintStm(new ConstExp(3)),new ConstExp(2),new PrintStm(new ConstExp(2)),new PrintStm(new ConstExp(0)));
//        Istm ex1=new AsgStm("x",new BoolExp(new ConstExp(0),"!()"));
 //     Istm ex1=new CompStm(new AsgStm("v",new ConstExp(2)),new AllStm("v",new ConstExp(90))) ;

//      Istm ex1=new CompStm(new ForkStm(new CompStm(new AllStm("a",new ConstExp(4)),new WritehStm("a",new ConstExp(300)))),new AllStm("v",new ConstExp(3)));
//        Istm ex1=new CompStm(new AllStm("v",new ConstExp(3)),new WritehStm("v",new ConstExp(300)));
//        Istm ex1=new CompStm(new AllStm("v",new ConstExp(3)),new CompStm(new AllStm("a",new ConstExp(4)),new WritehStm("a",new ConstExp(300))));
 //   Istm ex1=new ForkStm(new AsgStm("v",new ConstExp(3)));
 //       ProgState prg=new ProgState(1,d,st,l,h,ex1);
//        Irepo repo=new Repo(prg);
//        Controller ctrl=new Controller(repo);
 //       try{
  //      repo.serialize();
  //      }catch(IOException e){}
//        System.out.println("Show or Save?");
//       Scanner s=new Scanner(System.in);
//       String op=s.next();
//         ctrl.all_steps(op);
//        try {
//            repo.save();
//            //      prg.to_string();
//            //     ctrl.one_step();
//        } catch (IOException ex) {
//            Logger.getLogger(Lab4j.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public static void test_all(){
    Istack<String> s=new Stack<>();
    s.push("3");
    try{
    System.out.println(s.pop());
    System.out.println(s.get_lenght());
    Ilist<Integer> l=new List<>();
    l.add(3);
    l.add(5);
    System.out.println(l.get_lenght());
    l.remove(3);
    System.out.println(l.get_lenght());    
    Idictionary<String,Integer> d=new Dictionary();
    d.add("a",2);
    System.out.println(d.find("b"));
    ArmExp e=new ArmExp(1,new VarExp("d"),new ConstExp(6));
    System.out.println(e.to_string());
   // System.out.println(e.eval(d));
     }catch(expression.Exception e){}
    }
            
    
}
