package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorVideo;

public class RepositorioVideos {

	private Map<String, Video> videos;
	private static RepositorioVideos unicaInstancia = new RepositorioVideos();

	private FactoriaDAO dao;
	private IAdaptadorVideo adaptadorVideo;
	
	private RepositorioVideos() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorVideo = dao.getVideoDAO();
			videos = new HashMap<String, Video>();
			this.cargarVideosBBDD();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
		
	}
	
	//Recupera todos los videos de la BBDD para trabajar con ellos
		private void cargarVideosBBDD() throws DAOException {
			List<Video> videosBD = adaptadorVideo.recuperarTodosVideos();
			for (Video vid: videosBD) {
				System.out.println("Cargado Video " + vid.getTitulo());
				System.out.println("Con etiquetas : ");
				for (Etiqueta e : vid.getEtiquetas()) {
					System.out.println(e.getNombreEtiq());
				}
				videos.put(vid.getUrl(), vid);
			}
			
		}
	public void addVideo(Video video) {
		videos.put(video.getUrl(), video);
	}
	
	public void removeVideo(Video video) {
		videos.remove(video.getUrl());
	}
	
	public Video findVideo(Video video) {
		Video v =videos.get(video.getUrl());
		return v;
	}	
	public static RepositorioVideos getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public Video getVideo(String titulo) {
		for (Video v : videos.values()) {
			if (v.getTitulo().equals(titulo)) {
				return v;
			}
		}
		return null;
	}
	
	public Video getVideo(int codigo) {
		for  (Video v: videos.values()) {
			if (v.getIdBD() == codigo) return v;
		}
		return null;
		
	}
	
	public Set<String> getTitulos() {
		return videos.keySet();
	}
	
	public List<Video> getVideos() {
		ArrayList<Video> lista = new ArrayList<Video>();
		for (Video v: videos.values())
			lista.add(v);
		return lista;
	}
}
