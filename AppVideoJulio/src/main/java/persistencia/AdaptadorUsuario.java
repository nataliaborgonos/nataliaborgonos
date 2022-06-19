package persistencia;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import persistencia.*;
import tds.driver.ServicioPersistencia;

import tds.driver.FactoriaServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import dominio.*;

public class AdaptadorUsuario implements IAdaptadorUsuario{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuario unicaInstancia = null;
	
	public static AdaptadorUsuario getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuario();
		else
			return unicaInstancia;
	}
	private AdaptadorUsuario() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}
	
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		boolean existe = true;
		
		// Si la entidad está registrada no la registra de nuevo
				try {
					eUsuario = servPersistencia.recuperarEntidad(usuario.getIdBD());
				} catch (NullPointerException e) {
					existe = false;
				}
				if (existe) return;
				
				//listas videos
				AdaptadorListaVideos adaptadorlistas = AdaptadorListaVideos.getUnicaInstancia();
				for (ListaVideos lista : usuario.getListaVideos()) {
					adaptadorlistas.registrarListaVideos(lista);
				}
				
				eUsuario = new Entidad();
				eUsuario.setNombre("usuario");
				
				ArrayList<Propiedad> propiedades= new ArrayList<Propiedad>();
				Propiedad p1= new Propiedad("nombre",usuario.getNombre());
				propiedades.add(p1);
				Propiedad p2= new Propiedad("apellidos", usuario.getApellidos());
				propiedades.add(p2);
			    Propiedad p3= new Propiedad("email", usuario.getEmail());
			    propiedades.add(p3);
			    Propiedad p4= new Propiedad("login", usuario.getLogin());
			    propiedades.add(p4);
			    Propiedad p5= new Propiedad("password", usuario.getPassword());
			    propiedades.add(p5);
			    Propiedad p6= new Propiedad("fechanacim", usuario.getFechaNac());
			    propiedades.add(p6);
				Propiedad p7= new Propiedad("premium", String.valueOf(usuario.isPremium()));
				propiedades.add(p7);
				Propiedad p8 = new Propiedad("recientes", usuario.getRecientes().toString());
				propiedades.add(p8);
				eUsuario.setPropiedades(propiedades);
				
				// registrar entidad usuario
				eUsuario = servPersistencia.registrarEntidad(eUsuario);
				// asignar identificador unico, aprovecha el que genera el servicio de persistencia
				usuario.setIdBD(eUsuario.getId()); 
		}
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getIdBD());
		servPersistencia.borrarEntidad(eUsuario);
	}
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getIdBD());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos", usuario.getApellidos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "login");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "login", usuario.getLogin());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechanacim");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechanacim", usuario.getFechaNac());
		String listas = this.getListaVideosString(usuario.getListaVideos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listalistavideos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "listalistavideos", listas);
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "recientes");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "recientes", usuario.getRecientes().toString());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", String.valueOf(usuario.isPremium()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "filtro");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "filtro", usuario.getFiltroPremium().getNombreFiltro());
	}
	public Usuario recuperarUsuario(int codigo) {
		// TODO Auto-generated method stub
		Entidad eUsuario;
		String nombre;
		String apellidos;
		String email;
		String login;
		String password;
		String fechaNacim;
		boolean premium;
		String nombreFiltro;
		String recientes;
		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		login = servPersistencia.recuperarPropiedadEntidad(eUsuario, "login");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		fechaNacim = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechanacim");
		premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		nombreFiltro = servPersistencia.recuperarPropiedadEntidad(eUsuario, "filtro");
		recientes = servPersistencia.recuperarPropiedadEntidad(eUsuario, "recientes");
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacim);
		usuario.setIdBD(codigo);
		usuario.setListaVideos(this.getListasVideosFormatoLista(servPersistencia.recuperarPropiedadEntidad(eUsuario, "listalistavideos")));
		//AdaptadorListaVideos adaptadorListas = AdaptadorListaVideos.getUnicaInstancia();
		usuario.setPremium(premium);
		if (nombreFiltro.equals("NoFiltro")) {
			usuario.setFiltroPremium(new NoFiltro());
		} else if (nombreFiltro.equals("Mis Listas")) {
			usuario.setFiltroPremium(new FiltroMisListas());
		} else if (nombreFiltro.equals("Adultos")) {
			usuario.setFiltroPremium(new FiltroAdultos());
		}
		return usuario;
	}
	
	public List<Usuario> recuperarTodosUsuarios() {
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}
	
	public String getListaVideosString(List<ListaVideos> listaListasVideos) {
		String listas = "";
		for (ListaVideos lista : listaListasVideos) {
			listas+= lista.getIdBD() + " ";
		}
		return listas.trim(); 
	}
	
	public List<ListaVideos> getListasVideosFormatoLista(String listas) {
		List<ListaVideos> listaListaVideos = new ArrayList<ListaVideos>();
		StringTokenizer strTok = new StringTokenizer(listas, " ");
		AdaptadorListaVideos adaptadorListas = AdaptadorListaVideos.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			ListaVideos l = adaptadorListas.recuperarListaVideos(Integer.valueOf((String) strTok.nextElement()));
			listaListaVideos.add(l);
		}
		return listaListaVideos;
	}

}
