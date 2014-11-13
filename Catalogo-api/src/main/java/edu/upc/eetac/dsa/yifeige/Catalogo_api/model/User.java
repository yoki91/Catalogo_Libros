package edu.upc.eetac.dsa.yifeige.Catalogo_api.model;

public class User 
{
	private String username;
	private String password;
	private String name;
	private String email;
	private String rol;
	private boolean loginSuccessful;
	private boolean administrador;


	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public boolean isLoginSuccessful() 
	{
		return loginSuccessful;
	}
	public void setLoginSuccessful(boolean loginSuccessful) 
	{
		this.loginSuccessful = loginSuccessful;
	}
	
	public boolean isAdministrador() 
	{
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

}


