package dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;

public class RepositorioUsuarios {
		
	//	private Map<String, Usuario> usuarios=new HashMap<String,Usuario>();
		private static RepositorioUsuarios unicaInstancia = new RepositorioUsuarios();
		private FactoriaDAO factoria;
		
		private HashMap<Integer, Usuario> asistentesPorID;
		private HashMap<String, Usuario> asistentesPorLogin;
		
		private RepositorioUsuarios (){
			asistentesPorID = new HashMap<Integer, Usuario>();
			asistentesPorLogin = new HashMap<String, Usuario>();
			
			try {
				factoria = FactoriaDAO.getInstancia();
				
				List<Usuario> listaAsistentes = factoria.getUsuarioDAO().recuperarTodosUsuarios();
				for (Usuario usuario : listaAsistentes) {
					System.out.println("lista asistentes: "+usuario.getLogin());
					asistentesPorID.put(usuario.getIdBD(), usuario);
					asistentesPorLogin.put(usuario.getLogin(), usuario);
				}
			} catch (DAOException eDAO) {
				   eDAO.printStackTrace();
			}
		}
		
		public static RepositorioUsuarios getUnicaInstancia() {
			if (unicaInstancia == null) unicaInstancia = new RepositorioUsuarios();
			return unicaInstancia;
		}
		public List<Usuario> getUsuariosPorLogin() throws DAOException {
			return new LinkedList<Usuario>(asistentesPorID.values());
		}

		public List<Usuario> getUsuariosPorId() throws DAOException {
			return new LinkedList<Usuario>(asistentesPorID.values());
		}
		
		public Usuario getUsuario(String login) {
			return asistentesPorLogin.get(login);
		}

		public Usuario getUsuario(int id) {
			return asistentesPorID.get(id);
		}
		
		public void addUsuario(Usuario usuario) {
			asistentesPorID.put(usuario.getIdBD(), usuario);
			asistentesPorLogin.put(usuario.getLogin(), usuario);
		}
		
		public void removeUsuario(Usuario usuario) {
			asistentesPorID.remove(usuario.getIdBD());
			asistentesPorLogin.remove(usuario.getLogin());
		}
		
		public boolean hacerPremium(Usuario usuario) {
			if (usuario.isPremium() == false) {
				usuario.setPremium(true);
				return true;
			} else {
				return false;
			}
		}
		
		public boolean quitarPremium(Usuario usuario) {
			if (usuario.isPremium() == true) {
				usuario.setPremium(false);
				return true;
			} else {
				return false;
			}
		}
		/*
		public void addUsuario(Usuario usu) {
			usuarios.put(usu.getLogin(), usu);
		}
		
		public void removeUsuario(Usuario usu) {
			usuarios.remove(usu.getNombre());
		}
		
		public Usuario findUsuario(String login) {
			if (usuarios.containsKey(login)) {
				Usuario u =usuarios.get(login);
				return u;
			}
			return null;
		}
		
		public Usuario getUsuario(String login) {
			return usuarios.get(login);
		}
		
		public static RepositorioUsuarios getUnicaInstancia() {
			return unicaInstancia;
		}
		
		*/
	}

	
