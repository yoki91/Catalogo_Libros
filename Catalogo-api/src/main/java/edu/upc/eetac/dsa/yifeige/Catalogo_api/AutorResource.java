package edu.upc.eetac.dsa.yifeige.Catalogo_api;
import java.sql.*;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import edu.upc.eetac.dsa.yifeige.Catalogo_api.model.*;

@Path("/autores")
public class AutorResource 
{
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String INSERT_AUTHOR_QUERY="insert into autores (nombre) value (?)";
	
	
	
		
    @POST
    @Consumes(MediaType.Catalogo_API_AUTHOR)
    @Produces(MediaType.Catalogo_API_AUTHOR)
	public Author createAuthor(Author author)
	{
    	Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try
		{
			stmt=conn.prepareStatement(INSERT_AUTHOR_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, author.getNombre());
			
			System.out.print(author.getNombre());
			System.out.print(INSERT_AUTHOR_QUERY);
			stmt.executeUpdate();
			ResultSet rs =stmt.getGeneratedKeys();
			if(rs.next())
			{
				int authorid=rs.getInt(1);
				author=ObtenerAuthorDesdeDB(authorid);
			}
			else
			{
				//Something has failed...
			}
		}
		catch(SQLException e)
		{
			throw new ServerErrorException(e.getMessage(),Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		 finally {
				try {
					if (stmt != null)
						stmt.close();
					conn.close();
				} catch (SQLException e) {

				}
			}

			return author;
		
		
		
	}
    
    
    private String Get_Author_By_Name="select * from autores where idautor=?";
    
    
    
    private Author ObtenerAuthorDesdeDB(int authorid)
    {
    	Author author =new Author();
    	Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try
		{
			stmt=conn.prepareStatement(Get_Author_By_Name);
			stmt.setInt(1, authorid);			
			ResultSet rs =stmt.executeQuery();
			if(rs.next())
			{
				author.setIdautor(Integer.parseInt(rs.getString("idautor")));
				author.setNombre(rs.getString("nombre"));
			}
			else 
			{
				throw new NotFoundException("There's no Author with this id"+ authorid);
			}
		}
		catch (SQLException e) 
		{
				throw new ServerErrorException(e.getMessage(),Response.Status.INTERNAL_SERVER_ERROR);
			} 
		finally 
			{
				try 
				{
					if (stmt != null)
						stmt.close();
					conn.close();
				} 
				catch (SQLException e) 
				{

				}
			}

			
    	return author;
    	
    }
    
    
    
    private String DELETE_Author_Query="delete from autores where idautor=?";
    private String DELETE_Libro_QUery="delete from libros where libroid=?";
    
    @DELETE
    @Path("/{idautor}")
    public void deleteAutor(@PathParam("idautor") String idautor)
    {
    	
    	
    	Connection conn = null;
		try 
		{
			conn = ds.getConnection();
		} 
		catch (SQLException e) 
		{
			throw new ServerErrorException("Could not connect to the database",Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try
		{
			/*Libro libro = new Libro();
	    	libro=ObtenerLibroidDesdeDB(Integer.valueOf(idautor));
	    	stmt=conn.prepareStatement(DELETE_Libro_QUery);
	    	stmt.setInt(1,libro.getLibroid());
	    	int rows1=stmt.executeUpdate();*/
	    	
			stmt=conn.prepareStatement(DELETE_Author_Query);
			stmt.setInt(1, Integer.valueOf(idautor));
			System.out.print(DELETE_Author_Query);
			System.out.print(Integer.valueOf(idautor));
			int rows=stmt.executeUpdate();
			if(rows==0)
			{
				throw new NotFoundException("No existe author con id="+ idautor);
			}
			
		}
		catch (SQLException e) 
		{
			throw new ServerErrorException(e.getMessage(),Response.Status.INTERNAL_SERVER_ERROR);
		} finally 
		{
			try 
			{
				if (stmt != null)
					stmt.close();
				    conn.close();
			} 
			catch (SQLException e) 
			{
				
			}
		}
		
    	
    }
    
    
    /*private String Get_libroid_Query="select libroid from libro_autores where idautor=?";
    
    private Libro ObtenerLibroidDesdeDB(int idautor)
    {
    	Connection conn = null;
		try 
		{
			conn = ds.getConnection();
		} 
		catch (SQLException e) 
		{
			throw new ServerErrorException("Could not connect to the database",Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try
		{
			stmt=conn.prepareStatement(Get_libroid_Query);
			stmt.setInt(1, idautor);
			ResultSet rs = stmt.executeQuery();
			if(rs.getFetchSize()==0)
			{
				while(rs.next())
				{
					Libro libro=new Libro();
					libro.setLibroid(rs.getInt("libroid"));
					return libro;
					
				}
			}
			else
			{
				throw new NotFoundException("no existe el libro con id"+idautor);
			}
			
			
		}
		catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {

			}
		}
		return null;
    	
    }*/
    
    
	

}
