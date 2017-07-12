#ifndef FILEREPO_H
#define FILEREPO_H
#include "domain.h"
#include <vector>
#include <fstream>

using namespace std;

class FileRepo{
private:
    vector<Product> repo;

public:

    FileRepo(){read_from_file();}

//Stores a product into the vector
    void store(Product p){
        if( p.get_id()<0){
            throw "Invalid id.";
        }

        if( p.get_price()<1.0 || p.get_price()>100.0){
            throw "Invalid price.";
        }


        for (int i=0;i<repo.size();i++){
            Product p2=repo.at(i);
            if(p.get_id()== p2.get_id()){
                throw "The id already exists";
            }
        }

        repo.push_back(p);
        sort();
        write_to_file();

    }

 //Reads from file the products
    void read_from_file(){
        ifstream in("E:/school/semestrul II/OOP/examen/data.txt");
        int id;
        while (in>>id){
            string name;
            double price;
            in>>name;
            in>>price;
            Product p(id,name,price);
            store(p);

        }
        in.close();

    }

//Gests all record stored
    vector<Product> get_all_records(){
        sort();
        return repo;
    }


//Sorts the products by their price
    void sort(){
        Product aux;
        for (int i=0;i<repo.size()-1;i++){
            for (int j=i;j<repo.size();j++){
                if(repo.at(i).get_price()<repo.at(j).get_price()){
                    aux=repo.at(i);
                    repo.at(i)=repo.at(j);
                    repo.at(j)=aux;
                }
            }
        }
    }

//Writes to file the new added products
    void write_to_file(){
        ofstream out("E:/school/semestrul II/OOP/examen/data.txt");
        for (int i=0;i<repo.size();i++){
            Product p=repo.at(i);
            out<<p.get_id()<<endl;
            out<<p.get_name()<<endl;
            out<<p.get_price()<<endl;
        }
        out.close();
    }

//Filters the Products with the price less then p
    vector<Product> filter(double p){
        vector<Product> rez;
        for(int i=0;i<repo.size();i++){
            Product prod=repo.at(i);
            if(prod.get_price()<p){
                rez.push_back(prod);
            }
        }
        return rez;
    }

};



#endif // FILEREPO_H

