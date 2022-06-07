package dominio;


import java.util.ArrayList;
import java.util.List;

public class Video {
	
	private String url;
	private String titulo;
	private int numReproducciones;
	private List<Etiqueta> listaEtiquetas;
	
	
	public Video(String url, String titulo) {
		this.url = url;
		this.titulo = titulo;
		this.numReproducciones = 0;
		this.listaEtiquetas = new ArrayList<Etiqueta>();
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
	public List<Etiqueta> getEtiquetas(){
		return listaEtiquetas;
	}
	public String getUrl() {
		return url;
	}
}
