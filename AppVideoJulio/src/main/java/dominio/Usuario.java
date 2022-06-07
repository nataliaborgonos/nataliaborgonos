package dominio;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private String nombre;
	private String email;
	private boolean esPremium;
	private List<ListaVideos> listaVideos;
	private List<Filtro> filtros;
	private List<Video> recientes; //ordenada

	public Usuario(String nombre, String email) {
		this.nombre = nombre;
		this.listaVideos = new LinkedList<ListaVideos>();
		this.recientes = new LinkedList<Video>();
		this.esPremium = false;
		this.filtros = new LinkedList<Filtro>();
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}
	public List<ListaVideos> getListaVideos() {
		return listaVideos;
	}
}
