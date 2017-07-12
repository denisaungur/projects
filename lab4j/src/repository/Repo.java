/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import domain.ProgState;
import statement.*;
import tools.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
/**
 *
 * @author Denisa
 */
public class Repo implements Irepo {
    private java.util.List<ProgState> repo=new ArrayList<ProgState>();
    private int l=0;
    
    @Override
    public java.util.List<ProgState> get_list() { 
        return repo;
    
    }
    
    @Override
    public void add(ProgState p){
        repo.add(p);
        l++;
    }
    
    @Override
    public void set_list(java.util.List<ProgState> l){
        repo=l;
    }
    
    public Repo(ProgState prog){
    repo.add(prog);
    l++;
    }
    
    public Repo(){
        try{
    deserialize();
        }catch(IOException | ClassNotFoundException e){System.out.println(e.toString());}
    }
    
    public void set_CrtSt(ProgState e){repo.add(repo.size(), e);}
    
    @Override
    public ProgState get_CrntState(){
    if(repo.size()!=0){
    return repo.get(repo.size()-1);
    }
    return null;
    }
    
    @Override
    public void set_stack(Istm s){repo.get(repo.size()-1).set_stack(s);}
    
    @Override
    public String to_string(){
        String s=""; 
        for(int  i=0;i<repo.size();i++){
        s=s+repo.get(i).to_string()+"\n";}
        return s;
    }
    
    
    public void serialize()throws IOException{
        ProgState prg=get_CrntState();
        Istack st=prg.get_stack();
        ObjectOutputStream ob=null;
        Istm stm=(Istm)st.get_object(st.get_lenght()-1);
  //      FileOutputStream file=new FileOutputStream("out.txt");
        ob=new ObjectOutputStream(new FileOutputStream("out.txt"));
        ob.writeObject(stm);
        ob.close();
    }
    
    @Override
    public void deserialize() throws IOException, ClassNotFoundException{
        ObjectInputStream in=null;
        in=new ObjectInputStream(new FileInputStream("out.txt"));
        Istm stm=(Istm)in.readObject();
        Istack<Istm> st=new Stack<>();
        Ilist<Integer> l=new tools.List<>();
        Idictionary<String,Integer> d=new Dictionary<>();
        Iheap<Integer> h=new Heap<>();
 //       javax.swing.JTextArea jTextArea1=null;
        ProgState prg=new ProgState(1,d,st,l,h,stm);
        repo.add(0,prg);
        in.close();
 //       System.out.println(prg.get_stack().get_lenght());
    }
    
    
    @Override
    public void save()throws IOException{
        if(get_CrntState()!=null){
        Istack stack=get_CrntState().get_stack();
        Idictionary symtable=get_CrntState().get_symtable();
        Ilist out=get_CrntState().get_output();
        Iheap h=get_CrntState().get_heap();
        try{
            try (FileWriter in = new FileWriter("in.txt")) {
                in.write("ID "+""+get_CrntState().get_id()+"\r\n");
                in.write("Stack\r\n");
            }
            try (FileWriter in1 = new FileWriter("in.txt",true)) {
                for (int i=stack.get_lenght()-1; i>=0 ;i--){
                    //               System.out.println("for");
                    Object o=stack.get_object(i);
                    Istm stm=(Istm)o;
                    in1.write(stm.to_string()+"\r\n");
                }
                in1.write("SymTable\r\n");
                symtable.get_pairs().keySet().stream().forEach((Object s) -> {
                    String str;
                    str = s+"->"+""+symtable.find(s)+"\r\n";
                    
                    
                    
                    try {
                        in1.write(str);
                    } catch (IOException ex) {
                        Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                in1.write("Output \r\n");
                for (int i=0;i<out.get_lenght();i++){
                    in1.write(""+out.get_object(i)+"\r\n");
                }
                in1.write("Heap \r\n");
                for (int i=0;i<h.get_lenght();i++){
                    int j=i+1;
                    String str=""+j+"->"+""+h.get_object(i);
                    in1.write(str+"\r\n");
                }
            }
        }catch(Exception e){System.out.println(e.toString());}
        }
    }
    
    @Override
    public void set_at(ProgState p, int i){
    repo.add(i, p);
    }
    
    @Override
    public boolean is_in(ProgState p){
        return repo.stream().anyMatch((repo1) -> (repo1.get_id() == p.get_id()));
    }
    
    
    @Override
    public java.util.List<ProgState> one_step() throws expression.Exception {
        int len=repo.size();
        for(int i=0;i<len;i++){
        ProgState s;
//            try {
                s = repo.get(i).one_step();
//            } catch (expression.Exception ex) { throw ex;}
 //           }
  //      set_at( s,i);
        if(!(is_in(s))){add(s);} //System.out.print(""+repo.size());}
        
        }
        return repo;
    }
    
    @Override
    public void set_l(int l){
    this.l=l;
    }
    
}
