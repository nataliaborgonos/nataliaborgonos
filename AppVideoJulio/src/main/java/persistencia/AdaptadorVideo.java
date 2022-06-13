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

public class AdaptadorVideo implements IAdaptadorVideo{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorVideo unicaInstancia = null;
	
	public static AdaptadorVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorVideo();
		else
			return unicaInstancia;
	}
	
	private AdaptadorVideo() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}

	@Override
	public void registrarVideo(Video video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarVideo(Video video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarVideo(Video video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Video recuperarVideo(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> recuperarTodosVideos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etiqueta> getEtiquetasLista(String etiquetas) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
