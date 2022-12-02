import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner; 

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Main {
	public static final String INVENTORY_FILE = "inventory.csv";
	public static final String USER_FILE = "users.csv";

	private HashMap<String, User> users; // username, user
	private Inventory inventory;
	public Scanner sc;
	public User active;

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
		System.out.print("GuitarShell>");
		String command = sc.nextLine();
		switch (command) {
			default: 
				System.out.println("Invalid command.");
				this.handleCommand();
				
			case "search": 
				this.handleSearchGuitar();
				
			case "add": 
				this.handleAddGuitar();
				
			case "remove": 
				this.handleRemoveGuitar();
		}
	}
	
	private void handleLogin() {
		try {
            System.out.println("Username:");
            String user = sc.nextLine();
 
            if(!users.containsKey(user)) {
            	System.out.println("User not found, please try again.");
            	this.handleLogin();
            }
            
            System.out.println("Password:");
            String pass = sc.nextLine();
            
            if(!users.get(user).verifyPassword(pass)) {
            	System.out.println("Invalid password, please try again.");
            	this.handleLogin();
            }
            	System.out.println("Welcome! Please enter a command:");
            	this.active = users.get(user);
            	this.handleCommand();
            	
        } catch(IllegalStateException | NoSuchElementException e) {
            System.out.println("Exiting");
        }
	}

	private void handleCreateInventory() {
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

	private void handleSearchGuitar() {
		
	}

	private void handleAddGuitar() {
		if(this.active instanceof Employee) {
			System.out.println("Enter the serial number:");
			String serial = sc.nextLine();
			System.out.println("Enter the serial price (dollar amount, e.g 12.99):");
			String price = sc.nextLine();
			System.out.println("Enter the manufacturer:");
			String builder = sc.nextLine();
			System.out.println("Enter the model:");
			String model = sc.nextLine();
			System.out.println("Enter the type:");
			String type = sc.nextLine();
			System.out.println("Enter the backwood:");
			String backwood = sc.nextLine();
			System.out.println("Enter the topwood:");
			String topwood = sc.nextLine();
			
			GuitarSpec spec = new GuitarSpec(builder, model, type, backwood, topwood);
			this.inventory.addGuitar(serial, Double.parseDouble(price), spec);
			
			try (CSVPrinter printer = new CSVPrinter(new FileWriter(INVENTORY_FILE, true), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			     printer.printRecord(serial, price, builder, model, type, backwood, topwood);
			 } catch (IOException ex) {
			     ex.printStackTrace();
			 }
			
		} else {
			System.out.println("You do not have permission to use this command.");
			this.handleCommand();
		}
	}

	private void handleRemoveGuitar() {

	}

}
