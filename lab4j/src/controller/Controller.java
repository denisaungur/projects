/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import repository.*;
import expression.*;
import statement.*;
import tools.*;
import domain.ProgState;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.function.Function;
/**
 *
 * @author Denisa
 */
public class Controller {
    private Irepo repo;
    private ProgState st;
    
    public Controller(Irepo r){repo=r; st=r.get_CrntState();}
    
    public String to_string(){return  st.to_string();}
   
    
    public Irepo get_repo(){return repo;}
    
    public String all_steps(String op){
        String s="";
        s=s+"    Original Program\n"+to_string();
        int i=1;
//        if(op.equals("save")){
//        while(st.get_stack().get_lenght()!=0){
//            try {
//                //        System.out.println("    STEP "+""+i);
//                repo.save();
//            } catch (IOException ex) {
//                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            st.one_step();
//            i++;
//            
//        }
//        }
//        else{
        while(true){
 //           repo.set_list(remove_completed(repo.get_list()));
        
        java.util.List<ProgState> list=remove_completed(repo.get_list());
        repo.set_list(list);
        if(list.size()==0){
            return s;
        }
        else{
           s=s+"    STEP "+""+i+"\n";
            try {
                s=s+one_step_for_all(list,op);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        i++;
//        repo.set_list(remove_completed(repo.get_list()));
        }
           
       
        }
        
 //       System.out.println(""+repo.get_list().size());
    }
    
    public String one_step_for_all(java.util.List<ProgState> list,String op) throws InterruptedException {
        java.util.List<Callable<ProgState>> callList;
        callList=list.stream()
                .map(p ->(Callable<ProgState>)() ->p.one_step())
                .collect(Collectors.toList());
        ExecutorService executor=Executors.newFixedThreadPool(8);
        java.util.List<ProgState> newPrgList =
                executor.invokeAll(callList). stream()
                        .map(future -> { try {
                            return future.get();
                        }
                        catch(InterruptedException e) {
                            return null;
                        }
                        catch(java.lang.Exception ex){return null;}
                        } )
                        .filter(p -> p!=null)
                        . collect(Collectors.toList());
        
        list.addAll(newPrgList);
        executor.shutdown();
        repo.set_list(list);
        if(op=="save"){
            try {
                //        System.out.println("    STEP "+""+i);
                repo.save();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "SAVED";
        
        }else{
       return repo.to_string();}
    }
    
    public java.util.List<ProgState> remove_completed(java.util.List<ProgState> list){
//        repo.set_l(list.size()-1);
        return list.stream()
                   .filter(p->p.is_not_completed())
                   .collect(Collectors.toList());
    
    }
    
    

    
  
    
    //    public java.util.List<ProgState> one_step_all(java.util.List<ProgState> list){
//        java.util.List<Callable<ProgState>> callList = list.stream()
//                                               .forEach(p->p.one_step());
//                                              
//    }
    
    
//    public void all_steps(String op){
//        System.out.println("    Original Program");
//        to_string();
//        int i=1;
//        if(op.equals("save")){
//        while(repo.get_list().size()!=0){
//            try {
//                //        System.out.println("    STEP "+""+i);
//                repo.save();
//            } catch (IOException ex) {
//                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            st.one_step();
//            i++;
//            
//        }
//        }
//        else{
//        while(repo.get_list().size()!=0){
// //           remove_completed(repo.get_list());
//            System.out.println("    STEP "+""+i);
//            int l=repo.get_list().size();
//            ProgState s=repo.get_list().get(x).one_step();
//            if(s.get_id()!=1){
//                repo.add(s);
//            }
//           
//            repo.to_string();   
////            System.out.print();
//            
// //       repo.set_at(s,x);  
//        
//            i++;
//        repo.set_list(remove_completed(repo.get_list()));
//        }
//    
//    }
//    }
}
