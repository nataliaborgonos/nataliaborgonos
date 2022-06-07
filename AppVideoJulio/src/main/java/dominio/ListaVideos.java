package dominio;

import java.util.LinkedList;

import java.util.List;

public class ListaVideos {
	private String nombreLista;
	private List<Video> videos;

	public ListaVideos(String nombreLista) {
		this.nombreLista = nombreLista;
		this.videos = new LinkedList<Video>();
	}

	public String getNombreLista() {
		return nombreLista;
	}
	public List<Video> getLista(){
		return videos;
	}
}
