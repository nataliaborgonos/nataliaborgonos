package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepositorioVideos {

	private Map<String, Video> videos;
	private static RepositorioVideos unicaInstancia = new RepositorioVideos();

	
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
	
	public List<Video> getVideos() {
		ArrayList<Video> lista = new ArrayList<Video>();
		for (Video v: videos.values())
			lista.add(v);
		return lista;
	}
}
