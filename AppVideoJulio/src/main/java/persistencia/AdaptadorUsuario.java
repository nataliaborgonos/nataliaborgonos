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
				Propiedad p9= new Propiedad("premium", String.valueOf(usuario.isPremium()));
				propiedades.add(p9);
				eUsuario.setPropiedades(propiedades);
				
				
				// registrar entidad usuario
				eUsuario = servPersistencia.registrarEntidad(eUsuario);
				// asignar identificador unico
				// Se aprovecha el que genera el servicio de persistencia
				usuario.setIdBD(eUsuario.getId()); 
		}
	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getIdBD());
		servPersistencia.borrarEntidad(eUsuario);
	}
	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public Usuario recuperarUsuario(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	
				
}
