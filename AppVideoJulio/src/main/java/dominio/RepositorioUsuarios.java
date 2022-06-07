package dominio;

import java.util.Map;

public class RepositorioUsuarios {
		
		private Map<String, Usuario> usuarios;
		
		public void addUsuario(Usuario usu) {
			usuarios.put(usu.getNombre(), usu);
		}
		
		public void removeUsuario(Usuario usu) {
			usuarios.remove(usu.getNombre());
		}
		
		public Usuario findUsuario(Usuario usu) {
			Usuario u =usuarios.get(usu.getNombre());
			return u;
		}
		
		
	}

	
