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

	public void registrarVideo(Video video) {
		// TODO Auto-generated method stub
		Entidad eVideo;
		boolean existe = true;
		
		// Si la entidad está registrada no la registra de nuevo
				try {
					eVideo = servPersistencia.recuperarEntidad(video.getIdBD());
				} catch (NullPointerException e) {
					existe = false;
				}
				if (existe) return;
				
				
				eVideo = new Entidad();
				eVideo.setNombre("video");
				ArrayList<Propiedad> propiedades=new ArrayList<Propiedad>();
				Propiedad p1= new Propiedad("url",video.getUrl());
				propiedades.add(p1);
				Propiedad p2=new Propiedad("titulo", video.getTitulo());
				propiedades.add(p2);
				Propiedad p3=new Propiedad("numreproducciones", String.valueOf(video.getNumReproducciones()));
				propiedades.add(p3);
				Propiedad p4=new Propiedad("listaetiquetas", video.getEtiquetasString());
				propiedades.add(p4);
				eVideo.setPropiedades(propiedades);
				
				// registrar entidad usuario
				eVideo = servPersistencia.registrarEntidad(eVideo);
				// asignar identificador unico, el que genera el servicio de persistencia
				video.setIdBD(eVideo.getId()); 
	}

	public void borrarVideo(Video video) {
		// TODO Auto-generated method stub
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getIdBD());
		servPersistencia.borrarEntidad(eVideo);	
	}

	public void modificarVideo(Video video) {
		// TODO Auto-generated method stub
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getIdBD());
		servPersistencia.eliminarPropiedadEntidad(eVideo, "url");
		servPersistencia.anadirPropiedadEntidad(eVideo, "url", video.getUrl());
		servPersistencia.eliminarPropiedadEntidad(eVideo, "titulo");
		servPersistencia.anadirPropiedadEntidad(eVideo, "titulo", video.getTitulo());
		
		servPersistencia.eliminarPropiedadEntidad(eVideo, "numreproducciones");
		servPersistencia.anadirPropiedadEntidad(eVideo, "numreproducciones", String.valueOf(video.getNumReproducciones()));
		servPersistencia.eliminarPropiedadEntidad(eVideo, "listaetiquetas");
		servPersistencia.anadirPropiedadEntidad(eVideo, "listaetiquetas", video.getEtiquetasString());

	}

	public Video recuperarVideo(int codigo) {
		// TODO Auto-generated method stub
		Entidad eVideo;
		String url;
		String titulo;
		String numreproducciones;
		String listaetiquetas;

		// recuperar entidad
		eVideo = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		url = servPersistencia.recuperarPropiedadEntidad(eVideo, "url");
		titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, "titulo");
		numreproducciones = servPersistencia.recuperarPropiedadEntidad(eVideo, "numreproducciones");
		listaetiquetas = servPersistencia.recuperarPropiedadEntidad(eVideo, "listaetiquetas");

		Video video = new Video(url, titulo);
		video.setIdBD(codigo);
		video.setNumReproducciones(Integer.parseInt(numreproducciones));
		video.setListaEtiquetas(this.getEtiquetasLista(listaetiquetas));
		return video;
	}

	public List<Video> recuperarTodosVideos() {
		// TODO Auto-generated method stub
		List<Entidad> eVideos = servPersistencia.recuperarEntidades("video");
		List<Video> videos = new LinkedList<Video>();
		for (Entidad eVideo : eVideos) {
			videos.add(recuperarVideo(eVideo.getId()));
		}
		return videos;
	}

	public List<Etiqueta> getEtiquetasLista(String etiquetas) {
		List<Etiqueta> listaetiq = new ArrayList<Etiqueta>();
		StringTokenizer strTok = new StringTokenizer(etiquetas, " ");
		while (strTok.hasMoreTokens()) {
			Etiqueta e = new Etiqueta((String)strTok.nextElement());
			listaetiq.add(e);
		}
		return listaetiq;
	}
	
	
}