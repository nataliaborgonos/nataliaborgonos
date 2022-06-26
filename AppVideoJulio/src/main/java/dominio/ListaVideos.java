package dominio;

import java.util.LinkedList;

import java.util.List;

public class ListaVideos {
	private int IdBD;
	private String nombreLista;
	private List<Video> videos;

	public ListaVideos(String nombreLista) {
		this.IdBD=0;
		this.nombreLista = nombreLista;
		this.videos = new LinkedList<Video>();
	}

	public String getNombreLista() {
		return nombreLista;
	}
	public List<Video> getLista(){
		return videos;
	}
	
	public void setLista(List<Video> videos) {
		this.videos=videos;
	}
	
	public void setIdBD(int idBD) {
		this.IdBD = idBD;
	}
	
	public int getIdBD() {
		return IdBD;
	}

	public void addVideo(Video video) {
		this.videos.add(video);
	}
	
	public void removeVideo(Video video) {
		this.videos.remove(video);
	}
	
}
