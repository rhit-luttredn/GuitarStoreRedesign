import java.util.List;

public class User {
	private String username;
	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean verifyPassword(String password) {
		return this.password.equals(password);
	}
	
	public List<Guitar> searchGuitar(GuitarSpec spec) {
		
		return null;
	}
	
	@Override
	public String toString() {
		return this.username + ": " + this.password;
	}
}
