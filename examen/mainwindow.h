#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include "controller.h"

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(Controller* ctrl,QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_showButton_clicked();


    void on_addButton_clicked();

    void on_pushButton_clicked();

private:
    Ui::MainWindow *ui;
    Controller *ctrl;
};

#endif // MAINWINDOW_H
