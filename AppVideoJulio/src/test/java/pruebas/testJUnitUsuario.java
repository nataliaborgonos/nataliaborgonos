package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.FiltroMisListas;
import dominio.ListaVideos;
import dominio.Usuario;

public class testJUnitUsuario {

	Usuario usu = new Usuario("Natalia", "Borgoños Garcia", "natalia.borgonosg@um.es", "31/10/2000","nataliaborgonos" , "1234");
	ListaVideos lista = new ListaVideos("ListaTest");
	
	@Test
	public void test() {
		assertNotNull(usu.getRecientes());
	}

	
	@Test
	public void testGetListaVideos() {
		usu.addListaVideos(lista);
		assertTrue(usu.getListaVideos().size() == 1);
	}

	@Test
	public void testGetRecientes() {
		assertTrue(usu.getRecientes().size() == 0);
	}

	@Test
	public void testSetFiltroPremium() {
		usu.setFiltroPremium(new FiltroMisListas());
		assertSame("MisListas", usu.getFiltroPremium().getNombreFiltro());
	}

}
