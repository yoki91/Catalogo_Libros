package edu.upc.eetac.dsa.yifeige.Catalogo_api;
import java.sql.*;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.digest.DigestUtils;

import edu.upc.eetac.dsa.yifeige.Catalogo_api.model.User;;





@Path("/users")
public class UserReource 
{
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private final static String GET_USER_BY_USERNAME_QUERY = "select * from users where username=?";
	private final static String INSERT_USER_INTO_USERS = "insert into users values(?, MD5(?), ?, ?)";
	private final static String INSERT_USER_INTO_USER_ROLES = "insert into user_roles values (?, 'registered')";
	private final static String Get_Rol_By_Username_Query="select * from user_roles where username=?";
	
	
	
	
	
	
	@Path("/login")
	@POST
	@Produces(MediaType.Catalogo_API_USER)
	@Consumes(MediaType.Catalogo_API_USER)
	public User login(User user) 
	{
		if (user.getUsername() == null || user.getPassword() == null)
			throw new BadRequestException("username and password cannot be null.");

		String pwdDigest = DigestUtils.md5Hex(user.getPassword());
		String storedPwd = getUserFromDatabase(user.getUsername(), true).getPassword();
		user.setLoginSuccessful(pwdDigest.equals(storedPwd));
		user.setPassword(null);
		return user;
	}

	private User getUserFromDatabase(String username, boolean password) 
	{
		User user = new User();
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
			stmt = conn.prepareStatement(GET_USER_BY_USERNAME_QUERY);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) 
			{
				user.setUsername(rs.getString("username"));
				if (password)
				user.setPassword(rs.getString("userpass"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
			} else
				throw new NotFoundException(username + " not found.");
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
			} catch (SQLException e) 
			{
				
			}
		}

		return user;
	}
	
	
	
	
	@Path("/validate")
	@POST
	@Produces(MediaType.Catalogo_API_USER)
	@Consumes(MediaType.Catalogo_API_USER)
	public User verificarRol(User user)
	{
		if(user.getUsername()==null)throw new BadRequestException("El nombre del usuario para verificar el rol no puede ser vacio");
		
		String usuario=user.getUsername();
		String usuarioadmin=ObtenerRolDesdeDB(usuario,true).getRol();
		user.setAdministrador(usuarioadmin.equals("administrador"));
		return user;		
	}
	
	
	private User ObtenerRolDesdeDB(String username,boolean rol)
	{
		User user = new User();
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
			stmt = conn.prepareStatement(Get_Rol_By_Username_Query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			
			if (rs.next()) 
			{
				user.setUsername(rs.getString("username"));
				if (rol)
				user.setRol(rs.getString("rolename"));
			} else
				throw new NotFoundException(username + " not found.");
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
			} catch (SQLException e) 
			{
				
			}
		}

		return user;
	}
		
		
		
}
