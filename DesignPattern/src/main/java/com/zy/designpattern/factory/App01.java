package com.zy.designpattern.factory;

import javax.swing.text.html.HTMLDocument;

public class App01 {

    public static Dialog dialog;

    public static void main(String[] args) {
        configuration();
        runBusinessLogic();
    }
    static void configuration(){
        if (System.getProperty("os.name").equals("Windows 11")) {
           dialog =  new WindowsDialog();
        }else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic(){
        dialog.renderWindow();
    }

}
