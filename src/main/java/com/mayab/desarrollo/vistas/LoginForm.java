package com.mayab.desarrollo.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mayab.desarrollo.persistance.DAOUsuario;
import com.mayab.desarrollo.servicios.UsuarioServicio;

public class LoginForm extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField  textField1, textField2;

    //calling constructor
    public LoginForm()
    {

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form
    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
    {

        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered pasword from the textField2
        JButton update;
        JPanel welcomePanel;

        DAOUsuario dao = new DAOUsuario();
        UsuarioServicio servicio = new UsuarioServicio(dao);


        //check whether the credentials are authentic or not
        if (servicio.login(userValue, passValue)) {  //if authentic, navigate user to a new page

            NewPage page = new NewPage();

            //make page visible to the user
            page.setVisible(true);

            JLabel wel_label = new JLabel("Welcome: " + userValue);
            update = new JButton("Update password");

            welcomePanel = new JPanel(new GridLayout(1, 2));
            welcomePanel.add(wel_label);
            welcomePanel.add(update);

            add(welcomePanel, BorderLayout.CENTER);

            page.getContentPane().add(welcomePanel);

            update.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {



                    ChangePassword updateForm = new ChangePassword();
                    updateForm.setSize(300, 100);
                    updateForm.setVisible(true);
                }
            });

        }
        else{
            //create instance of the NewPage
            NewPage page = new NewPage();

            //make page visible to the user
            page.setVisible(true);

            //create a welcome label and set it to the new page
            JLabel wel_label = new JLabel("Please enter valid username and password");
            page.getContentPane().add(wel_label);

        }
    }

}
