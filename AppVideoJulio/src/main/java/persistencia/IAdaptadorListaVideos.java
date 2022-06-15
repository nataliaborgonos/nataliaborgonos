package persistencia;

import java.util.List;

import dominio.*;

public interface IAdaptadorListaVideos {
	public void registrarListaVideos(ListaVideos lista);
	public void borrarListaVideos(ListaVideos lista);
	public void modificarListaVideos(ListaVideos lista);
	public ListaVideos recuperarListaVideos(int codigo);
	public List<ListaVideos> recuperarTodasListas();

}
