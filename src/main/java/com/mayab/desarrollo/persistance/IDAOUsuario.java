package com.mayab.desarrollo.persistance;

import java.util.List;
import com.mayab.desarrollo.entities.Usuario;
import com.mayab.desarrollo.main.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;

public interface IDAOUsuario {
    public List<Usuario> findAll();
    public Usuario findByYD(int id);
    public int createUser(Usuario usuario);
    public boolean deleteUser(int id);
    public Usuario updatePassword(Usuario usuario, String newpassword);
    public Usuario findByName(String nombre);
    public Usuario findByEmail(String email);
}
