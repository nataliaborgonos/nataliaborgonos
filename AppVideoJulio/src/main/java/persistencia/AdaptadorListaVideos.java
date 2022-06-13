package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import dominio.*;

public class AdaptadorListaVideos implements IAdaptadorListaVideos {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaVideos unicaInstancia = null;
	private RepositorioVideos repoVideos = RepositorioVideos.getUnicaInstancia();
	
	public static AdaptadorListaVideos getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorListaVideos();
		else
			return unicaInstancia;
	}
	
	private AdaptadorListaVideos() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}

	@Override
	public void registrarListaVideos(ListaVideos lista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarListaRecientes(List<Video> lista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarListaVideos(ListaVideos lista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarListaVideos(ListaVideos lista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ListaVideos recuperarListaVideos(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaVideos> recuperarTodasListas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVideosString(List<Video> listaVideos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getListaVideosLista(String videos) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
