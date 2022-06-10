package dominio;

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
}
