package edu.upc.eetac.dsa.yifeige.Catalogo_api.model;
import java.util.ArrayList;
import java.util.List;
public class User_Collection 
{
	private List<User> Users;
	


	public User_Collection()
	{
		super();
		Users=new ArrayList<>();
	}
	
	public List<User> getUsers() {
		return Users;
	}

	public void setUsers(List<User> users) {
		Users = users;
	}
	
	public void addUser(User User)
	{
		Users.add(User);
	}
	
	

}
