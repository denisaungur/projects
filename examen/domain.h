#ifndef DOMAIN_H
#define DOMAIN_H
#include <string>

using namespace std;

class Product{
private:
    int id;
    string name;
    double price;

public:
    Product (){}
    Product (int i, string n, double p){id=i; name=n; price=p;}

//Gets the id of the product
    int get_id(){return id;}

 //Sets the product's id to the new id i
    void set_id(int i){ id=i;}

//Gets the name of the product
    string get_name(){
      return name;
    }

 //Sets the name of the product with m
    void set_name(string m){ name=m;}


//Gets the price of the product
    double get_price(){ return price; }


//Sets the price of the product to p
    void set_price(double p){price=p; }

//Overloads the operator =
    void operator=(Product p){
        id=p.get_id();
        name=p.get_name();
        price=p.get_price();
    }

};

#endif // DOMAIN_H

