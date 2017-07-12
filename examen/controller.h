#ifndef CONTROLLER_H
#define CONTROLLER_H
#include "domain.h"
#include "filerepo.h"
#include <vector>

using namespace std;

class Controller{
private:
    FileRepo repo;

public:
    Controller(FileRepo r){repo=r;};

    vector<Product> get_all(){
        return repo.get_all_records();
    }

    void add(Product p){
        try{
            repo.store(p);
    }
        catch(const char* msg){ throw msg;}

    }

    vector<Product> filter_products(double p){
      return repo.filter(p);
    }


};

#endif // CONTROLLER_H

