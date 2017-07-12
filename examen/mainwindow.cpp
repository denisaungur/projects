#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "controller.h"
#include <vector>
#include <QString>
#include <QMessageBox>

MainWindow::MainWindow(Controller* ctr,QWidget *parent) :
    QMainWindow(parent),
    ctrl(ctr),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_showButton_clicked()
{
    ui->listWidget->clear();
    vector<Product> all=this->ctrl->get_all();
    for (int i=0;i<all.size();i++){
        Product p=all.at(i);
        ui->listWidget->addItem(QString::fromStdString(p.get_name()));
    }
    ui->label->setText(QString::number(all.size()));
}



void MainWindow::on_addButton_clicked()
{
    if (ui->lineEditname->text()==""){
        QMessageBox::information(this,"Empty","There is no name entered.");
    }
    int id=(ui->lineEditid->text()).toInt();
    string name=(ui->lineEditname->text()).toStdString();
    float price=(ui->lineEditprice->text()).toFloat();
    Product p(id,name,price);
    try{
        this->ctrl->add(p);
    }
    catch(const char* msg){ QMessageBox::critical(this,"Invalid",QString::fromUtf8(msg,-1));}
    ui->lineEditid->clear();
    ui->lineEditname->clear();
    ui->lineEditprice->clear();
    ui->listWidget->clear();
    vector<Product> all=this->ctrl->get_all();
    ui->label->setText(QString::number(all.size()));
}

void MainWindow::on_pushButton_clicked()
{

    double p=(ui->lineEdit->text()).toDouble();
    vector<Product> rez=this->ctrl->filter_products(p);
    ui->listWidget->clear();
    for (int i=0;i<rez.size();i++){
        Product p=rez.at(i);
        ui->listWidget->addItem(QString::number(p.get_id())+"  "+QString::fromStdString(p.get_name())+"  "+QString::number(p.get_price()));
    }

}
