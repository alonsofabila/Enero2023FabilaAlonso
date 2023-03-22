package com.mayab.desarrollo.main;

import com.mayab.desarrollo.vistas.LoginForm;

import javax.swing.*;

public class TestForm {
    public static void main(String[] args) {
        try{
            LoginForm form = new LoginForm();
            form.setSize(300, 100);
            form.setVisible(true);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
