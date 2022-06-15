package persistencia;

import java.util.List;

import dominio.Etiqueta;
import dominio.RepositorioVideos;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorEtiqueta implements IAdaptadorEtiqueta {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorEtiqueta unicaInstancia = null;
	private RepositorioVideos repoVideos = RepositorioVideos.getUnicaInstancia();
	
	public static AdaptadorEtiqueta getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorEtiqueta();
		else
			return unicaInstancia;
	}
	
	private AdaptadorEtiqueta() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}
	@Override
	public void registrarEtiqueta(Etiqueta etiq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarEtiqueta(Etiqueta etiq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEtiqueta(Etiqueta etiq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Etiqueta recuperarEtiqueta(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etiqueta> recuperarTodasEtiquetas() {
		// TODO Auto-generated method stub
		return null;
	}

}
