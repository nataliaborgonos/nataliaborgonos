package dominio;

import java.util.LinkedList;
import java.util.List;

public class FiltroMisListas extends Filtro{
	private String nombreFiltro;
	
	public FiltroMisListas() {
		this.nombreFiltro = "MisListas";
	}
	@Override
	public List<Video> esVideoOk(List<Video> listaVideos, Usuario usuario) {
		// TODO Auto-generated method stub
		List<Video> listaFiltrada = new LinkedList<Video>(listaVideos);
		for (ListaVideos l : usuario.getListaVideos()) {
			for (Video v : l.getLista()) {
				if (listaFiltrada.contains(v)) {
					listaFiltrada.remove(v);
					}
			}
		}
		return listaFiltrada;
	}
	@Override
	public String getNombreFiltro() {
		// TODO Auto-generated method stub
		return nombreFiltro;
	}
	
}
