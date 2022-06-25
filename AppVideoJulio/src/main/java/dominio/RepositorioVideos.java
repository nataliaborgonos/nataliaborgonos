package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorVideo;

public class RepositorioVideos {

	private Map<String, Video> videos;
	private HashMap<Integer, Video> videosPorID;
	private static RepositorioVideos unicaInstancia;

	private FactoriaDAO factoria;
	private IAdaptadorVideo adaptadorVideo;

	public static RepositorioVideos getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new RepositorioVideos();
		return unicaInstancia;
	}

	private RepositorioVideos() {
		videosPorID = new HashMap<Integer, Video>();
		try {
			factoria = FactoriaDAO.getInstancia();
			List<Video> listaVideos = factoria.getVideoDAO().recuperarTodosVideos();
			adaptadorVideo = factoria.getVideoDAO();
			for (Video video : listaVideos) {
				videosPorID.put(video.getIdBD(), video);
			}
			videos = new HashMap<String, Video>();
			this.cargarVideosBBDD();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}

	}

	private void cargarVideosBBDD() throws DAOException {
		List<Video> videosBD = adaptadorVideo.recuperarTodosVideos();
		for (Video vid : videosBD) {
			videos.put(vid.getUrl(), vid);
		}

	}

	public List<Video> getVideosID() throws DAOException {
		return new LinkedList<Video>(videosPorID.values());
	}

	public Video getVideoID(int id) {
		return videosPorID.get(id);
	}

	public void addVideoID(Video video) {
		videosPorID.put(video.getIdBD(), video);
	}

	public void removeVideoID(Video video) {
		videosPorID.remove(video.getIdBD());
	}

	public void addVideo(Video video) {
		videos.put(video.getTitulo(), video);
	}

	public void removeVideo(Video video) {
		videos.remove(video.getUrl());
	}

	public Video findVideo(Video video) {
		Video v = videos.get(video.getUrl());
		return v;
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
		for (Video v : videos.values()) {
			if (v.getIdBD() == codigo)
				return v;
		}
		return null;

	}

	public Set<String> getTitulos() {
		return videos.keySet();
	}

	public List<Video> getVideos() {
		ArrayList<Video> lista = new ArrayList<Video>();
		for (Video v : videos.values())
			lista.add(v);
		return lista;
	}
}
