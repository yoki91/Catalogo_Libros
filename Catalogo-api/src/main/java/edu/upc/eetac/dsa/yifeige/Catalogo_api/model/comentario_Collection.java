package edu.upc.eetac.dsa.yifeige.Catalogo_api.model;
import java.util.ArrayList;
import java.util.List;
public class comentario_Collection 
{
	private List<Comentario> comentarios;
	
	

	public comentario_Collection()
	{
		super();
		comentarios=new ArrayList<>();
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public void addcomentario(Comentario comentario)
	{
		comentarios.add(comentario);
	}
	
	
	
	
	

}
