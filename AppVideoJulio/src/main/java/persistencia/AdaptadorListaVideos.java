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

	public void registrarListaVideos(ListaVideos lista) {
		// TODO Auto-generated method stub
		Entidad eLista;
		boolean existe = true;
		
		// Si la entidad está registrada no la registra de nuevo
				try {
					eLista = servPersistencia.recuperarEntidad(lista.getIdBD());
				} catch (NullPointerException e) {
					existe = false;
				}
				if (existe) return;
				
				
				//Registramos primero los objetos
				AdaptadorVideo adaptadorVideo = AdaptadorVideo.getUnicaInstancia();
				for (Video video : lista.getLista())
					adaptadorVideo.registrarVideo(video);
				
				
				eLista = new Entidad();
				eLista.setNombre("lista");
				ArrayList<Propiedad> propiedades=new ArrayList<Propiedad>();
				Propiedad p1= new Propiedad("nombre",lista.getNombreLista());
				propiedades.add(p1);
				Propiedad p2= new Propiedad("listavideos", getVideosString(lista.getLista()));
				propiedades.add(p2);
				eLista.setPropiedades(propiedades);
				
				
				// registrar entidad usuario
				eLista = servPersistencia.registrarEntidad(eLista);
				// asignar identificador unico
				// Se aprovecha el que genera el servicio de persistencia
				lista.setIdBD(eLista.getId());	
	}

	public void borrarListaVideos(ListaVideos lista) {
		// TODO Auto-generated method stub
		Entidad eLista = servPersistencia.recuperarEntidad(lista.getIdBD());
		
		servPersistencia.borrarEntidad(eLista);
	}

	public void modificarListaVideos(ListaVideos lista) {
		// TODO Auto-generated method stub
		Entidad eLista = servPersistencia.recuperarEntidad(lista.getIdBD());

		servPersistencia.eliminarPropiedadEntidad(eLista, "nombre");
		servPersistencia.anadirPropiedadEntidad(eLista, "nombre", lista.getNombreLista());
		
		String videos = getVideosString(lista.getLista());
		servPersistencia.eliminarPropiedadEntidad(eLista, "listavideos");
		servPersistencia.anadirPropiedadEntidad(eLista, "listavideos", videos);
		
	}

	public ListaVideos recuperarListaVideos(int codigo) {
		// TODO Auto-generated method stub
		// si no, lo recupera de la base de datos
					Entidad eLista;
					String nombre;
					
					// recuperar entidad
					eLista = servPersistencia.recuperarEntidad(codigo);

					// recuperar propiedades que no son objetos
					nombre = servPersistencia.recuperarPropiedadEntidad(eLista, "nombre");

					ListaVideos lista = new ListaVideos(nombre);
					lista.setIdBD(codigo);
					
					lista.setLista(this.getListaVideosLista(servPersistencia.recuperarPropiedadEntidad(eLista, "listavideos")));
					return lista; 
	}

	public List<ListaVideos> recuperarTodasListas() {
		// TODO Auto-generated method stub
		List<Entidad> eListas = servPersistencia.recuperarEntidades("lista");
		List<ListaVideos> lista = new LinkedList<ListaVideos>();

		for (Entidad eLista : eListas) {
			lista.add(recuperarListaVideos(eLista.getId()));
		}
		return lista;
	}

	public String getVideosString(List<Video> listaVideos) {
		String videos = "";
		for (Video video : listaVideos) {
			videos+= video.getIdBD() + " ";
		}
		return videos.trim(); 
	}

	public List<Video> getListaVideosLista(String videos) {
		List<Video> listavideos = new ArrayList<Video>();
		StringTokenizer strTok = new StringTokenizer(videos, " ");
		while (strTok.hasMoreTokens()) {
			Video v = repoVideos.getVideo(Integer.valueOf((String) strTok.nextElement()));
			listavideos.add(v);
		}
		return listavideos;
	}
	


}
