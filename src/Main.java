import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static final String INVENTORY_FILE = "inventory.csv";
	public static final String USER_FILE = "users.csv";
	
	private HashMap<String, User> users; // username, user
	private Inventory inventory;
	public Scanner sc;
	
	public Main() {
		this.sc = new Scanner(System.in);
		
		this.users = new HashMap<>();
		this.populateUsers();
		
		// TODO: ask user for file to use
		this.handleCreateInventory();
	}

	public static void main(String[] args) {
		Main main = new Main();
	}
	
	private void populateUsers() {
		// TODO: Ask user for file to use?
		
		
		// TODO: read file of users and populate users list
	}
	
	private void handleCommand() {
		// Main loop for handling commands
	}
	
	private void handleLogin(String username, String password) {
		
	}
	
	private void handleCreateInventory() {
		// TODO: Ask user for file to use
		try {
			File file = new File(INVENTORY_FILE);
			this.inventory = new Inventory(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error parsing csv");
		}
	}
	
	private void handleSearchGuitar(GuitarSpec spec) {
		
	}
	
	private void handleAddGuitar(GuitarSpec spec) {
		
	}
	
	private void handleRemoveGuitar(String serialNumber) {
		
	}

}
