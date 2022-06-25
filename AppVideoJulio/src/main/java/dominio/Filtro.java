package dominio;

import java.util.List;

public abstract class Filtro {
		public abstract List<Video> esVideoOk(List<Video> listaVideos, Usuario usuario);
		public abstract String getNombreFiltro();
}
