package controlador;

import java.util.Date;

import dominio.*;

public class Controlador {

	Usuario usuarioActual;
	RepositorioUsuarios repoUsuarios;
	
	public Controlador() {
		
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, Date fechaNacim, String login, String password) {
		//Usuario usuario = new Usuario(nombre,apellidos,email,fechaNacim,login, password);
		if (repoUsuarios.findUsuario(login) != null) {
			//Si el usuario con el login ya esta registrado, no hace nada
			return false;
		} else {
		Usuario usuario = new Usuario(nombre,apellidos,email,fechaNacim,login, password);
		repoUsuarios.addUsuario(usuario);
		return true;
		}
	}
	
		public boolean login(String login, String password) {
			Usuario usu = repoUsuarios.getUsuario(login);
			if (usu == null) {
				System.out.println("No existe el usuario");
				return false;
			} else {
				if (usu.getPassword().equals(password)) {
					usuarioActual = usu;
					
					return true;
				} else {
					return false;
				}
			}
		}
}
