package persistencia;

import java.util.List;

import dominio.Etiqueta;

public interface IAdaptadorEtiqueta {
	public void registrarEtiqueta(Etiqueta etiq);
	public void borrarEtiqueta(Etiqueta etiq);
	public void modificarEtiqueta(Etiqueta etiq);
	public Etiqueta recuperarEtiqueta(int codigo);
	public List<Etiqueta> recuperarTodasEtiquetas();
}
