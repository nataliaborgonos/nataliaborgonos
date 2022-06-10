package dominio;

import java.util.HashMap;
import java.util.Map;

public class RepositorioUsuarios {
		
		private Map<String, Usuario> usuarios=new HashMap<String,Usuario>();
		private static RepositorioUsuarios unicaInstancia = new RepositorioUsuarios();
		
		public void addUsuario(Usuario usu) {
			System.out.println("me llega el usuario:"+usu.getLogin());
			usuarios.put(usu.getLogin(), usu);
		}
		
		public void removeUsuario(Usuario usu) {
			usuarios.remove(usu.getNombre());
		}
		
		public Usuario findUsuario(String login) {
		
			System.out.println("llego");
			if (usuarios.containsKey(login)) {
				Usuario u =usuarios.get(login);
				return u;
			}
			
			return null;
		}
		
		public Usuario getUsuario(String login) {
			System.out.println("login: "+login);
			return usuarios.get(login);
		}
		
		public static RepositorioUsuarios getUnicaInstancia() {
			return unicaInstancia;
		}
		
		
	}

	
