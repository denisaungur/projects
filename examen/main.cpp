#include "mainwindow.h"
#include <QApplication>
#include "controller.h"
#include "domain.h"
#include <vector>
#include "filerepo.h"
#include <assert.h>
#include <iostream>

//Tests the metods from repository
void tets_repo(){
    FileRepo r=FileRepo();
    assert(r.filter(20).size()==2);
    Product p(1,"notebook",5);
    try{
        r.store(p);
        assert(false);
    }
    catch(const char* msg){ cout<<msg;}
    vector<Product> all=r.get_all_records();
    assert(all.size()==3);



}


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    FileRepo repo=FileRepo();
    Controller* ctrl=new Controller(repo);
    MainWindow w(ctrl);
    w.show();

    return a.exec();
}
