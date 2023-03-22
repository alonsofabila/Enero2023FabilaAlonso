package com.mayab.desarrollo.servicios;

import com.mayab.desarrollo.entities.Usuario;
import com.mayab.desarrollo.persistance.DAOUsuario;

public class UsuarioServicio {

    private DAOUsuario dao;
    public UsuarioServicio(DAOUsuario d) {
        dao = d; }

    public boolean login(String user, String pass) {  boolean result = false;
        Usuario usuario = dao.findByName(user);
        if(usuario != null) {
            if(usuario.getPassword().equals(pass))
                result  = true;
        }
        return result;
    }

    public Usuario createAccount(String nombre, String password, String email) {
        Usuario user = new Usuario();

        user.setNombre(nombre);
        user.setPassword(password);
        user.setEmail(email);

        if(dao.findByEmail(email)==null && dao.findByName(nombre)==null){
            System.out.println("usuario creado");
            dao.createUser(user);
        }
        else {
            System.out.println("El usuario ya existe");
        }
        return null;
    }

    public String recoverPassword(String email, String password) {

        try {
            if(dao.findByEmail(email) == null) {

                System.out.println("El email no esta registrado");

            } else {

                dao.updatePassword(dao.findByEmail(email), password);
                System.out.println("Password actualizada");
            }
        }catch(Exception e) {
            System.out.println("Error: no fue posible actualizar la contraseña");        }
        return email;
    }
}
