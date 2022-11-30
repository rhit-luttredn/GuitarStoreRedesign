import java.util.List;

public class User {
	private String username;
	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean verifyPassword(String username, String password) {
		
		return false;
	}
	
	public List<Guitar> searchGuitar(GuitarSpec spec) {
		
		return null;
	}
}
