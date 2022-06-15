package dominio;


import java.util.ArrayList;
import java.util.List;

public class Video {
	private int idBD;
	private String url;
	private String titulo;
	private int numReproducciones;
	private List<Etiqueta> listaEtiquetas;
	
	
	public Video(String url, String titulo) {
		this.idBD=0;
		this.url = url;
		this.titulo = titulo;
		this.numReproducciones = 0;
		this.listaEtiquetas = new ArrayList<Etiqueta>();
	}
	
	public int getIdBD() {
		return idBD;
	}
	
	public void setIdBD(int idBD) {
		this.idBD=idBD;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setListaEtiquetas(List<Etiqueta> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}
	
	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones=numReproducciones;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
	public List<Etiqueta> getEtiquetas(){
		return listaEtiquetas;
	}
	
	public void addEtiqueta(Etiqueta etiq) {
		this.listaEtiquetas.add(etiq);
	}

	public void removeEtiqueta(Etiqueta etiq) {
		this.listaEtiquetas.remove(etiq);
	}
	
	public String getEtiquetasString() {
		String etiquetas = "";
		for (Etiqueta etiq : listaEtiquetas) {
			etiquetas+= etiq.getNombreEtiq() + " ";
		}
		return etiquetas.trim();
	}
	
	public String getUrl() {
		return url;
	}
}
