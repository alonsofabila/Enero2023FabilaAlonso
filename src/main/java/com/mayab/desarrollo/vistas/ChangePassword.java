package com.mayab.desarrollo.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mayab.desarrollo.persistance.DAOUsuario;
import com.mayab.desarrollo.servicios.UsuarioServicio;

public class ChangePassword extends JFrame implements ActionListener{

    JButton submit;
    JPanel newPanel;
    JLabel userLabel, oldPasswordLabel, newPasswordLabel;
    final JTextField  userField, oldPassField, newPassField;

    public ChangePassword() {

        userLabel = new JLabel();
        userLabel.setText("Username");
        userField = new JTextField(15);

        oldPasswordLabel = new JLabel();
        oldPasswordLabel.setText("Old password");
        oldPassField = new JPasswordField(15);

        newPasswordLabel = new JLabel();
        newPasswordLabel.setText("New password");
        newPassField = new JPasswordField(15);

        submit = new JButton("submit"); //set label to button

        newPanel = new JPanel(new GridLayout(4, 1));
        newPanel.add(userLabel);
        newPanel.add(userField);
        newPanel.add(oldPasswordLabel);
        newPanel.add(oldPassField);
        newPanel.add(newPasswordLabel);
        newPanel.add(newPassField);
        newPanel.add(submit);

        add(newPanel, BorderLayout.CENTER);

        submit.addActionListener(this);
        setTitle("Update password");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String userValue = userField.getText();
        String oldPassValue = oldPassField.getText();
        String newPassValue = newPassField.getText();

        DAOUsuario dao = new DAOUsuario();
        UsuarioServicio servicio = new UsuarioServicio(dao);

        if(servicio.changePassword(userValue, oldPassValue, newPassValue)){


            NewPage page = new NewPage();


            page.setVisible(true);


            JLabel uppass_label = new JLabel("Password updated successfuly");
            page.getContentPane().add(uppass_label);

        }else {


            NewPage page = new NewPage();

            page.setVisible(true);


            JLabel uppass_label = new JLabel("Please enter valid information");
            page.getContentPane().add(uppass_label);
        }

    }

}
