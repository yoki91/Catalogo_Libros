package edu.upc.eetac.dsa.yifeige.Catalogo_api.model;

import java.util.List;

public class Libro {
	private int libroid;
	private String titulo;
	private String lengua;
	private String edicion;
	private String fechaEdicion;
	private String fechaImpresion;
	private String editorial;
	private long lastModified;
	private List<Author> autores;
	private List<Comentario> comentarios;

	public int getLibroid() {
		return libroid;
	}

	public void setLibroid(int libroid) {
		this.libroid = libroid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLengua() {
		return lengua;
	}

	public void setLengua(String lengua) {
		this.lengua = lengua;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getFecha_Edicion() {
		return fechaEdicion;
	}

	public void setFecha_Edicion(String fecha_Edicion) {
		fechaEdicion = fecha_Edicion;
	}

	public String getFecha_Impresion() {
		return fechaImpresion;
	}

	public void setFecha_Impresion(String fecha_Impresion) {
		fechaImpresion = fecha_Impresion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public List<Author> getAutores() {
		return autores;
	}

	public void setAutores(List<Author> autores) {
		this.autores = autores;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
