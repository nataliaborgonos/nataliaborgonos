package dominio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private int idBD; //identificador del usuario
	private String nombre;
	private String apellidos;
	private String email;
	private Date fechaNac;
	private String login;
	private String password;
	private boolean esPremium;
	private List<ListaVideos> listaVideos;
	private List<Filtro> filtros;
	private List<Video> recientes; //ordenada

	public Usuario(String nombre, String apellidos, String email, Date fechaNac, String login, String password) {
		this.nombre = nombre;
		this.apellidos=apellidos;
		this.email=email;
		this.fechaNac=fechaNac;
		this.login=login;
		this.password=password;
		this.listaVideos = new LinkedList<ListaVideos>();
		this.recientes = new LinkedList<Video>();
		this.esPremium = false;
		this.idBD=0;
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
	
	public String getPassword() {
		return password;
	}
}
