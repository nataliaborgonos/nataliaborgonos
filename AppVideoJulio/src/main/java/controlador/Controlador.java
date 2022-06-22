package controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;

import dominio.*;
import dominio.Video;
import persistencia.*;

import pulsador.Luz;
import umu.tds.componente.CargadorVideos;
import umu.tds.componente.VideoListener;
import umu.tds.componente.Videos;


public class Controlador implements VideoListener{
	private static Controlador unicaInstancia;
	public Usuario usuarioActual;
	private RepositorioUsuarios repoUsuarios;
	private RepositorioVideos repoVideos;
	private IAdaptadorUsuario adaptadorUsuario;
	private IAdaptadorVideo adaptadorVideo;
	private IAdaptadorListaVideos adaptadorListaVideos;
	private IAdaptadorEtiqueta adaptadorEtiqueta;
	private ListaVideos top_ten=new ListaVideos("top ten");
	
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
			System.out.println("registrando: "+video.getTitulo());
			adaptadorVideo.registrarVideo(video);
			repoVideos.addVideo(video);
		}
		
		public void enteradoCambioVideos(EventObject arg0) {
			// TODO Auto-generated method stub
			System.out.println("entro");
			CargadorVideos cv=(CargadorVideos) arg0.getSource();
			Videos videos=cv.getArchivoVideos();
			for(umu.tds.componente.Video video : videos.getVideo()) {
				Video nuevo=new Video(video.getURL(), video.getTitulo());
				for (String etiqueta : video.getEtiqueta()) {
					System.out.println("añado etiquetas");
					Etiqueta etiq = new Etiqueta(etiqueta);
					nuevo.addEtiqueta(etiq);
				}
				this.registrarVideo(nuevo);
			}
			for(String v : repoVideos.getTitulos()) {
				System.out.println("Video en el repo: " + v);
			}
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
		
		public boolean isPremium() {
			if(usuarioActual.isPremium()) {
				return true;
			}
			return false;
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

		
		//Función para la búsqueda de videos en la pestaña explorar.
		//Tendrá en cuenta el filtro activo, el titulo a buscar y las etiquetas.
		public List<Video> buscarVideo(List<Video> original, List<String> etiquetas, String titulo) {
			List<Video> listaResultado = new ArrayList<Video>();
			if (etiquetas.size() > 0) {
				for (String e : etiquetas) {
					for (Video v : original) {
						for (Etiqueta etiq : v.getEtiquetas()) {
							if (e.equals(etiq.getNombreEtiq()) && v.getTitulo().toLowerCase().contains(titulo.toLowerCase()) && listaResultado.contains(v) == false) {
								listaResultado.add(v);
							}
						}
					}
				
				}
			} else {
				for (Video v : original) {
					if (v.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
						listaResultado.add(v);
					}
				}
			}
			
			List<Video> conFiltro = usuarioActual.getFiltroPremium().esVideoOk(listaResultado, usuarioActual);
			
			for (Video v : listaResultado) {
				System.out.println("Resultado Búsqueda : " + v.getTitulo());
			}
			
			
			return conFiltro;
		}
		
		public ListaVideos buscarListas(String titulo){
			
			for(ListaVideos l : usuarioActual.getListaVideos()){
				
				if(l.getNombreLista().equals(titulo)){
					
					return l;
				}
			}
			
			return null;
		}
		
		//Crea una lista de video
		public boolean crearLista(String titulo){
			
			ListaVideos l1 = new ListaVideos(titulo);
			if(usuarioActual.addListaVideos(l1)){
				adaptadorListaVideos.registrarListaVideos(l1);
				adaptadorUsuario.modificarUsuario(usuarioActual);
				return true;
			}
			return false;
		}
		
		//Elimina una lista de video
			public boolean eliminarLista(String titulo){
				
				ListaVideos lista = buscarListas(titulo);
				if (lista != null) {
				usuarioActual.removeListaVideos(lista);
				adaptadorListaVideos.borrarListaVideos(lista);
				adaptadorUsuario.modificarUsuario(usuarioActual);
				return true;
				}
				return false;
				
			}
		
		//Método para añadir un video a una lista de videos
		public void addVideoLista(ListaVideos lista, Video v) {
			if (lista.getLista().contains(v) == false) {
			lista.addVideo(v);
			adaptadorListaVideos.modificarListaVideos(lista);
			adaptadorUsuario.modificarUsuario(usuarioActual);
			}
		}
		
		//Método para eliminar un video a una lista de videos
			public void removeVideoLista(ListaVideos lista, Video v) {
				lista.removeVideo(v);
				adaptadorListaVideos.modificarListaVideos(lista);
				adaptadorUsuario.modificarUsuario(usuarioActual);
			}
		
		
		
		//Actualización de variables cuando reproducimos un video.
		public void reproducirVideo(Video video, boolean esReciente) {
			int old = video.getNumReproducciones();
			video.setNumReproducciones(old+1);
			if (esReciente) {
			actualizarRecientes(video);
			}
			actualizarTopTen();
			AdaptadorUsuario.getUnicaInstancia().modificarUsuario(usuarioActual);
			AdaptadorVideo.getUnicaInstancia().modificarVideo(video);
		}
		

		
		public void setFiltro(Filtro filtroPremium) {
			usuarioActual.setFiltroPremium(filtroPremium);
			AdaptadorUsuario.getUnicaInstancia().modificarUsuario(usuarioActual);
		}
		
		
		//Actualiza el top ten con los videos más reproducidos.
		public void actualizarTopTen() {
			List<Video> listaEntera = new ArrayList<Video>(repoVideos.getUnicaInstancia().getVideos());
			Collections.sort(listaEntera, new Comparator<Video>() {
				public int compare (Video v1,Video v2) {
					return new Integer(v2.getNumReproducciones()).compareTo(v1.getNumReproducciones());
				}
				
				});
			if(listaEntera.isEmpty()) {System.out.println("no se puede actualizar");}
			else {
			if (listaEntera.size() > 10) {
				if (top_ten.getLista().size() == 0) {
					for (int i = 0; i < 10 ; i++) {
							
						top_ten.addVideo(listaEntera.get(i));
					
					}
				} else {
					top_ten.getLista().removeAll(top_ten.getLista());
					for (int i = 0; i < 10 ; i++) {
						top_ten.addVideo(listaEntera.get(i));
					}
				}
			} else {
				if (top_ten.getLista().size() == 0) {
					for (int i = 0; i < listaEntera.size() ; i++) {
						top_ten.addVideo(listaEntera.get(i));
					
					}
				} else {
					top_ten.getLista().removeAll(top_ten.getLista());
					for (int i = 0; i < listaEntera.size() ; i++) {
						top_ten.addVideo(listaEntera.get(i));
					}
				}
				}
				
			}
		}
		
		
		//Devuelve la lista Top Ten.
		public ListaVideos getTopTen() {
			return top_ten;
		}

		public boolean topTenEmpty() {
			if(top_ten.getLista().isEmpty()) {return true;}
			return false;
		}
	
}
