package edu.upc.eetac.dsa.yifeige.Catalogo_api.model;
import java.util.ArrayList;
import java.util.List;

public class libro_Collection 
{
	private List<Libro> libros;
	
	public libro_Collection()
	{
		super();
		libros=new ArrayList<>();
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	public void addLibro(Libro libro)
	{
		libros.add(libro);
	}

}
