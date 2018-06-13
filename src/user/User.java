package user;

public class User {
	public String name;
	public String email;
	private int user_id;
	
	public User(int user_id, String name, String email) {
		super();
		this.name = name;
		this.email = email;
		this.user_id = user_id;
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", user_id=" + user_id + "]";
	}

	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
