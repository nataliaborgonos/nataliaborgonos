package dominio;

import java.util.LinkedList;
import java.util.List;

public class FiltroAdultos extends Filtro{

	private String nombreFiltro;
	
	public FiltroAdultos() {
		this.nombreFiltro = "Adultos";
	}
	@Override
	public List<Video> esVideoOk(List<Video> listaVideos, Usuario usuario) {
		List<Video> listaFiltrada = new LinkedList<Video>(listaVideos);
		for (ListaVideos l : usuario.getListaVideos()) {
			for (Video v : l.getLista()) {
				for (Etiqueta e : v.getEtiquetas()) {
					if(e.getNombreEtiq()==nombreFiltro) {listaFiltrada.remove(v);}
					}
			}
		}
		return listaFiltrada;
	}
}
