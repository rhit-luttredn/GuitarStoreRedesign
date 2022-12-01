import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner; 

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {
	public static final String INVENTORY_FILE = "inventory.csv";
	public static final String USER_FILE = "users.csv";

	private HashMap<String, User> users; // username, user
	private Inventory inventory;
	public Scanner sc;

	public static void main(String[] args) {
		Main main = new Main();
	}
	
	public Main() {
		this.sc = new Scanner(System.in);

		this.users = new HashMap<>();
		this.populateUsers();

		// TODO: ask user for file to use
		this.handleCreateInventory();
		this.handleLogin();
	}

	private void populateUsers() {
		// TODO: Ask user for file to use?

		// TODO: read file of users and populate users list
		try {
			FileReader file = new FileReader(new File(USER_FILE));
			CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT.withFirstRecordAsHeader());
			for (CSVRecord csvRecord : parser) {
				// User, Password, isEmployee
				String username = csvRecord.get(0);
				String password = csvRecord.get(1);
				boolean isEmployee = csvRecord.get(2).equals("1");
				
				if (isEmployee) {
					this.users.put(username, new Employee(username, password));
				} else {
					this.users.put(username, new User(username, password));
				}
			}
			System.out.println(users);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error parsing csv");
		}
	}

	private void handleCommand() {
		// Main loop for handling commands
		System.out.println("TODO: await commands");
	}
	
	private void handleLogin() {
		try {
            System.out.println("Please enter your username.");
            String user = sc.nextLine();
            //TODO: check if username in system
            //if no, inform user, prompt again
            //if yes, continue
            System.out.println("Please enter your password.");
            String pass = sc.nextLine();
            //TODO: check if password matches
            //if no, ask again
            //if yes, continue
            this.handleCommand();
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println("System.in was closed; exiting");
        }
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
