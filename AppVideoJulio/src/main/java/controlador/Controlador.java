package controlador;

import dominio.*;
import dominio.Video;
import persistencia.*;

import pulsador.Luz;
import umu.tds.componente.*;

public class Controlador {
	private static Controlador unicaInstancia;
	public Usuario usuarioActual;
	private RepositorioUsuarios repoUsuarios;
	private RepositorioVideos repoVideos;
	private IAdaptadorUsuario adaptadorUsuario;
	private IAdaptadorVideo adaptadorVideo;
	private IAdaptadorListaVideos adaptadorListaVideos;
	private IAdaptadorEtiqueta adaptadorEtiqueta;
	
	public Controlador() {
		repoVideos = RepositorioVideos.getUnicaInstancia();
		repoUsuarios = RepositorioUsuarios.getUnicaInstancia();
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorVideo = factoria.getVideoDAO();
		adaptadorListaVideos=factoria.getListaVideoDAO();
		adaptadorEtiqueta=factoria.getEtiquetaDAO();
	}
	
	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String fechaNacim, String login, String password) {
		if (repoUsuarios.findUsuario(login) != null) {
			//Si el usuario con el login ya esta registrado, no hace nada
			return false;
		} else {
		Usuario usuario = new Usuario(nombre,apellidos,email,fechaNacim,login, password);
		adaptadorUsuario.registrarUsuario(usuario);		
		repoUsuarios.addUsuario(usuario);
		return true;
		}
	}
	
		public boolean login(String login, String password) {
			Usuario usu = repoUsuarios.getUsuario(login);
			if (usu == null) {
				return false;
			} else {
				if (usu.getPassword().equals(password)) {
					usuarioActual = usu;
					return true;
				} else {
					return false;
				}
			}
		}
		
		public Usuario getUsuarioActual() {
			return usuarioActual;
		}
		public void registrarVideo(String url, String titulo) {
			Video video = new Video(url,titulo);
			adaptadorVideo.registrarVideo(video);
			repoVideos.addVideo(video);
		}
		
		public boolean registrarVideo(Video video) {
			for (Video v : repoVideos.getVideos()) {
				if (video.getTitulo().equals(v.getTitulo()) && video.getUrl().equals(v.getUrl())) {
					return false;
				}
			}
			adaptadorVideo.registrarVideo(video);
			repoVideos.addVideo(video);
			return true;
		}

		public void registrarListaVideos(ListaVideos lista) {
			adaptadorListaVideos.registrarListaVideos(lista);
		}
		
		//Convierte en premium al usuario actual.
		public void hacerPremium() {
			if (usuarioActual != null) {
				if (usuarioActual.isPremium() == false) {
					usuarioActual.setPremium(true);
				} else if(usuarioActual.isPremium() == true) {
					usuarioActual.setPremium(false);
					}
			//Ya para confirmar el cambio en la BBDD.
			adaptadorUsuario.modificarUsuario(usuarioActual);
			}
		}
		
		//Añade una etiqueta a un video(buscando el video por url).
		public void addEtiquetaVideo(String url, Etiqueta etiq) {
			Video video = repoVideos.getVideo(url);
			video.addEtiqueta(etiq);
			adaptadorVideo.modificarVideo(video);
		}
		
		//Añade una etiqueta a un video.
		public void addEtiquetaVideo(Video video, Etiqueta etiq) {
			video.addEtiqueta(etiq);
			adaptadorVideo.modificarVideo(video);
		}
		
		//Comprueba si una etiqueta existe en un determinado video.
		public boolean existeEtiqueta(Video video, Etiqueta etiq) {
			for (Etiqueta e : video.getEtiquetas()) {
				if (e.getNombreEtiq().equals(etiq.getNombreEtiq())) {
					return true;
				}
				
			}
			return false;
		}
		
		public void actualizarRecientes(Video ultimoVideo) {
			if (usuarioActual.getRecientes().size() < 5) {
				usuarioActual.getRecientes().add(0, ultimoVideo);
				adaptadorUsuario.modificarUsuario(usuarioActual);
			} else {
				usuarioActual.getRecientes().add(0, ultimoVideo);
				usuarioActual.getRecientes().remove(5);
				adaptadorUsuario.modificarUsuario(usuarioActual);
			}
			
		}
		
}
