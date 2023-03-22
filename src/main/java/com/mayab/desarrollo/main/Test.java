package com.mayab.desarrollo.main;

import com.mayab.desarrollo.servicios.UsuarioServicio;
import org.hibernate.Session;
import com.mayab.desarrollo.entities.Usuario;
import com.mayab.desarrollo.persistance.DAOUsuario;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		//Add new user object
		System.out.println("----Creando usuario----");
		Usuario user = new Usuario();
		user.setNombre("Alonso");
		user.setPassword("megustanmamonas");
		user.setEmail("jorgealonsofabila@gmail.com");

		session.save(user);

		session.getTransaction().commit();
		session.close();

		DAOUsuario dao = new DAOUsuario();

		Usuario user2 = new Usuario();
		user2.setNombre("Valeria");
		user2.setPassword("123456");
		user2.setEmail("val@gmail.com");

		int idUser = dao.createUser(user2);
		System.out.println("ID: " + idUser);

		//update password
		System.out.println("\n-----Actualizacion de contraseña-----");
		dao.createUser(dao.updatePassword(user2, "1822"));

		//find user
		System.out.println("-----Buscar por ID-----");
		Usuario u = dao.findByYD(4);
		System.out.println("Nombre: " + u.getNombre() + " Email: " + u.getEmail() + " Password: " + u.getPassword());

		//find all
		System.out.println("\n----Retorno de todos los usuarios----");
		List<Usuario> usuarios = dao.findAll();
		for (Usuario us : usuarios) {

			System.out.println("Nombre: " + us.getNombre() + " Email: " + us.getEmail() + " Password: " + us.getPassword());
		}

		System.out.println("\n-----Haciendo Login-----");
		UsuarioServicio servicio = new UsuarioServicio(dao);
		boolean isLogin = servicio.login("Alonso", "megustanmamonas");
		System.out.println("\nLogin: " + isLogin);

		//delete user
		System.out.println("\n-----Eliminar usuario-----");
		if (dao.deleteUser(7)) {

			System.out.println("Usuario eliminado");
		}


	}
}
