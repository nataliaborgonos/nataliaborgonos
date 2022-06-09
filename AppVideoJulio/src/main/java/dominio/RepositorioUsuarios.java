package dominio;

import java.util.HashMap;
import java.util.Map;

public class RepositorioUsuarios {
		
		private Map<String, Usuario> usuarios=new HashMap<String,Usuario>();
		
		public void addUsuario(Usuario usu) {
			usuarios.put(usu.getNombre(), usu);
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
		
		
		
	}

	
