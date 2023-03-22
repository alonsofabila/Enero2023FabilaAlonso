package com.mayab.desarrollo.persistance;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import com.mayab.desarrollo.entities.Usuario;
import com.mayab.desarrollo.main.HibernateUtil;

public class DAOUsuario implements IDAOUsuario {

    @Override
    public List<Usuario> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> lista = session.createQuery("FROM Usuario").list();
        return lista;
    }

    @Override
    public Usuario findByYD(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario user = session.get(Usuario.class, id);
        session.close();
        return user;
    }

    @Override
    public int createUser(Usuario u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        int id = (int) session.save(u);
        session.getTransaction().commit();
        session.close();

        return id;
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //delete
        Usuario u = session.get(Usuario.class, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public Usuario updatePassword(Usuario u, String newpass) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Usuario toUpdate = session.get(Usuario.class, u.getId());
        toUpdate.setPassword(newpass);
        session.getTransaction().commit();
        session.close();
        return toUpdate;
    }

    @Override
    public Usuario findByName(String nombre) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query qry = session.createQuery("FROM Usuario WHERE nombre = :nombre").setParameter("nombre", nombre);
            Usuario user = (Usuario) qry.setMaxResults(1).getSingleResult();
            System.out.println(user.toString());
            session.close();
            return user;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query qry = session.createQuery("FROM Usuario WHERE email = :email").setParameter("email", email);
            Usuario user = (Usuario) qry.setMaxResults(1).getSingleResult();
            System.out.println(user.toString());
            session.close();
            return user;
        } catch(Exception e) {
            return null;
        }
    }
}