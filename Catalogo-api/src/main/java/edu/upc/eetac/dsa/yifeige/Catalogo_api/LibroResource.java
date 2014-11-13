package edu.upc.eetac.dsa.yifeige.Catalogo_api;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import edu.upc.eetac.dsa.yifeige.Catalogo_api.model.*;


@Path("/libros")
public class LibroResource 
{
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	
	private String Get_Libro_Query="select * from libros where titulo LIKE ?";
	@Path("/search")
	@GET
	@Produces(MediaType.Catalogo_API_LIBRO_COLLECTION)
	public libro_Collection getlibros(@QueryParam("titulo") String titulo)
	{
		libro_Collection libros =new libro_Collection();
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
			stmt = conn.prepareStatement(Get_Libro_Query);
			stmt.setString(1, '%'+titulo+'%');
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Libro libro=new Libro();
				libro.setLibroid(rs.getInt("libroid"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setLengua(rs.getString("lengua"));
				libro.setEdicion(rs.getString("edicion"));
				libro.setFecha_Edicion(rs.getString("Fecha_Edicion"));
				libro.setFecha_Impresion(rs.getString("Fecha_Impresion"));
				libro.setEditorial(rs.getString("Editorial"));
				libro.setLastModified(rs.getLong("last_modified"));
				libros.addLibro(libro);
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

		return libros;
	}
	
	
	
	
	
	
	
	private String Insert_Libro_Query="insert into libros (titulo,lengua,edicion,fecha_Edicion,fecha_Impresion,Editorial) value (?,?,?,?,?,?)";
	
	@POST
	@Consumes(MediaType.Catalogo_API_LIBRO)
	@Produces(MediaType.Catalogo_API_LIBRO)
	public Libro createlibro(Libro libro)
	{
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) 
		{
			throw new ServerErrorException("Could not connect to the database",Response.Status.SERVICE_UNAVAILABLE);
		}
		PreparedStatement stmt = null;
		try
		{
			stmt=conn.prepareStatement(Insert_Libro_Query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, libro.getTitulo());
			stmt.setString(2, libro.getLengua());
			stmt.setString(3, libro.getEdicion());
			stmt.setString(4, libro.getFecha_Edicion());
			stmt.setString(5, libro.getFecha_Impresion());
			stmt.setString(6, libro.getEditorial());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
			{
				int libroid=rs.getInt(1);
				libro=ObtenerLibroDB(Integer.toString(libroid));
			}
			else
			{
				
			}
		}
		catch(SQLException e)
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

		return libro;	
	}
	
	
	
	
	   
	
	   @GET
	   @Path("/{libroid}")
	   @Produces(MediaType.Catalogo_API_LIBRO)
	   public Response getlibro(@PathParam("libroid") String libroid,@Context Request request)
	   {
		   System.out.println("GetLibro");
		   CacheControl cc = new CacheControl();
		   Libro libro =ObtenerLibroDB(libroid);
		   EntityTag eTag = new EntityTag(Long.toString(libro.getLastModified()));
		   Response.ResponseBuilder rb=request.evaluatePreconditions(eTag);
		   if(rb!=null)
		   {
			   return rb.cacheControl(cc).tag(eTag).build();
		   }
		   rb = Response.ok(libro).cacheControl(cc).tag(eTag);
		   return rb.build();
		   
	   }
	   
	   
	   
	   
	   private String Get_Libro_By_Id_Query="select * from libros where libroid=?";
	   private String Get_autor_By_IdLibro_Query="select autores.nombre from libros,libro_autores,autores where libros.libroid=libro_autores.libroid and libro_autores.libroid=1 and autores.idautor=libro_autores.idautor";
	   
	   
	   
	   private Libro ObtenerautorDB(String libroid)
	   {
		   System.out.println("ObtenerAutorDB");
		   Libro libro = new Libro();
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) 
			{
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}

			PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(Get_autor_By_IdLibro_Query);
				//stmt.setInt(1, Integer.valueOf(libroid));
				
				ResultSet rs = stmt.executeQuery();
				System.out.println("result set: "+ rs.getString("nombre"));
				List<Author> autores= new ArrayList<Author>();
				if(rs.next())
				{
					
					Author autor= new Author();
					autor.setNombre(rs.getString("nombre"));
					autor.setIdautor(rs.getInt("idautor"));
					autores.add(autor);
					libro.setAutores(autores);
					System.out.println("Autor metido al libro");
				}
			}
			catch (SQLException e) 
			{
				throw new ServerErrorException(e.getMessage(),Response.Status.INTERNAL_SERVER_ERROR);
				
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					conn.close();
				} catch (SQLException e) {

				}
			}
			return libro;
		   
	   }
	   
	   
	   
	   
	   
		private Libro ObtenerLibroDB(String libroid)
		{
			System.out.println("ObtenerLibroDB");
			Libro libro = new Libro();
			libro=ObtenerautorDB(libroid);
			
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) 
			{
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}

			PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(Get_Libro_By_Id_Query);
				stmt.setInt(1, Integer.valueOf(libroid));
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
				{
					libro.setLibroid(rs.getInt("libroid"));
					libro.setTitulo(rs.getString("titulo"));
					libro.setLengua(rs.getString("lengua"));
					libro.setEdicion(rs.getString("edicion"));
					libro.setFecha_Edicion(rs.getString("Fecha_Edicion"));
					libro.setFecha_Impresion(rs.getString("Fecha_Impresion"));
					libro.setEditorial(rs.getString("Editorial"));
					libro.setLastModified(rs.getTimestamp("last_modified").getTime());
				}
				else 
				{
					throw new NotFoundException("There's no sting with libroid="+ libroid);
				}
				
			}
			catch (SQLException e) {
				throw new ServerErrorException(e.getMessage(),
						Response.Status.INTERNAL_SERVER_ERROR);
			} finally 
			{
				try {
					if (stmt != null)
						stmt.close();
					conn.close();
				} catch (SQLException e) 
				{

				}
			}
			return libro;
		  
		   }
	   
	
	
	

}
