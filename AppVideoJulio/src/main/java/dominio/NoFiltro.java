package dominio;

import java.util.List;

public class NoFiltro extends Filtro{

	private String nombreFiltro;
	
	public NoFiltro() {
		this.nombreFiltro = "NoFiltro";
	}

	public String getNombreFiltro() {
		return nombreFiltro;
	}
	@Override
	public List<Video> esVideoOk(List<Video> listaVideos, Usuario usuario) {
		return listaVideos;
	}

}
