package dominio;

import java.util.List;

public abstract class Filtro {
	//el video cumple los filtros
		public abstract List<Video> esVideoOk(List<Video> listaVideos, Usuario usuario);
}
