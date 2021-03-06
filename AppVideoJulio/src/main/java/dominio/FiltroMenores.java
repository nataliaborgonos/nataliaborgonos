package dominio;

import java.util.LinkedList;
import java.util.List;

public class FiltroMenores extends Filtro {

	private String nombreFiltro;

	public FiltroMenores() {
		this.nombreFiltro = "Menores";
	}

	@Override
	public List<Video> esVideoOk(List<Video> listaVideos, Usuario usuario) {
		List<Video> listaFiltrada = new LinkedList<Video>(listaVideos);
		for (ListaVideos l : usuario.getListaVideos()) {
			for (Video v : l.getLista()) {
				for (Etiqueta e : v.getEtiquetas()) {
					if (e.getNombreEtiq() == nombreFiltro) {
						listaFiltrada.remove(v);
					}
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
