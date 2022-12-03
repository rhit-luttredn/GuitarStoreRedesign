import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
		while (true) {
			System.out.print("GuitarShell>");
			switch (sc.nextLine()) {
			case "search":
				this.handleSearchGuitar();
				break;
			case "add":
				this.handleAddGuitar();
				break;
			case "remove":
				this.handleRemoveGuitar();
				break;
			case "logout":
				System.out.println("TODO: implement logout");
				break;
			case "exit":
				System.out.println("TODO: implement exit");
				return;
			case "help":
				System.out.println("Available commands:");
				System.out.println("  `search` to search for a guitar in the inventory.");
				if (this.active instanceof Employee) {
					System.out.println("  `add` to add a guitar to the inventory.");
					System.out.println("  `remove` to remove a guitar from the inventory by serial number.");
				}
				System.out.println("  `logout` to logout of the current account");
				System.out.println("  `exit` to exit the application.");
				System.out.println("  `help` to see this.");
				break;
			default:
				System.out.println("Invalid command. Try help for more options");
				this.handleCommand();
				break;
			}
		}
	}

	private void handleLogin() {
		try {
			System.out.println("Username:");
			String user = sc.nextLine();

			if (!users.containsKey(user)) {
				System.out.println("User not found, please try again.");
				this.handleLogin();
			}

			System.out.println("Password:");
			String pass = sc.nextLine();

			if (!users.get(user).verifyPassword(pass)) {
				System.out.println("Invalid password, please try again.");
				this.handleLogin();
			}
			System.out.println("Welcome! Please enter a command:");
			this.active = users.get(user);
			this.handleCommand();

		} catch (IllegalStateException | NoSuchElementException e) {
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
	
	private Object validateEnumEntry(Class c, String promptMsg, String errorMsg, boolean loopTillValid, boolean allowAnys) {
		Object val = null;
		do {
			System.out.println(promptMsg);
			System.out.print("> ");
			String response = sc.nextLine().trim();
			if (c.equals(Builder.class))
				val = Builder.fromString(response);
			else if (c.equals(Type.class))
				val = Type.fromString(response);
			else if (c.equals(Wood.class))
				val = Wood.fromString(response);
			if (!allowAnys && val.toString().equals(""))
				val = null;
			if (val == null)
				System.out.println(errorMsg);
		} while(loopTillValid && val == null);
		return val;
	}

	private void handleSearchGuitar() {
		// Builder
		String prompt = "Please enter the builder.\n Available builders: " + Builder.printPossibleValues() + 
				" or leave empty for any builder";
		Builder builder = (Builder) validateEnumEntry(Builder.class, prompt, "Invalid builder.", true, true);
		
		// Model
		System.out.println("Please enter the model or leave empty for any model:");
		System.out.print("> ");
		String model = sc.nextLine();
		
		// Type
		prompt = "Please enter the type.\nAvailable types: " + Type.printPossibleValues() + " or leave empty for any type";
		Type type = (Type) validateEnumEntry(Type.class, prompt, "Invalid type.", true, true);
		
		// Backwood
		prompt = "Please enter the backwood.\nAvailable woods: " + Wood.printPossibleValues() + " or leave empty for any wood";
		Wood backwood = (Wood) validateEnumEntry(Wood.class, prompt, "Invalid wood.", true, true);
		
		// Topwood
		prompt = "Please enter the topwood.\nAvailable woods: " + Wood.printPossibleValues() + " or leave empty for any wood"; 
		Wood topwood = (Wood) validateEnumEntry(Wood.class, prompt, "Invalid wood.", true, true);

		GuitarSpec spec = new GuitarSpec(builder, model, type, backwood, topwood);
		List<Guitar> guitars = this.inventory.searchGuitar(spec);
		
		System.out.println("Found " + guitars.size() + " guitars");
		for (Guitar guitar : guitars) {
			System.out.println(guitar);
		}
	}

	private void handleAddGuitar() {
		if (this.active instanceof Employee) {
			// Serial Number
			System.out.println("Enter the serial number:");
			String serial = sc.nextLine();
			
			// Price
			System.out.println("Enter the serial price (dollar amount, e.g 12.99):");
			String price = sc.nextLine();
			
			// Builder
			String prompt = "Please enter the builder.\n Available builders: " + Builder.printPossibleValues();
			Builder builder = (Builder) validateEnumEntry(Builder.class, prompt, "Invalid builder.", true, false);
			
			// Model
			System.out.println("Please enter the model:");
			String model = sc.nextLine();
			
			// Type
			prompt = "Please enter the type.\nAvailable types: " + Type.printPossibleValues();
			Type type = (Type) validateEnumEntry(Type.class, prompt, "Invalid type.", true, false);
			
			// Backwood
			prompt = "Please enter the backwood.\nAvailable woods: " + Wood.printPossibleValues();
			Wood backwood = (Wood) validateEnumEntry(Wood.class, prompt, "Invalid wood.", true, false);
			
			// Topwood
			prompt = "Please enter the topwood.\nAvailable woods: " + Wood.printPossibleValues();
			Wood topwood = (Wood) validateEnumEntry(Wood.class, prompt, "Invalid wood.", true, false);

			GuitarSpec spec = new GuitarSpec(builder, model, type, backwood, topwood);
			this.inventory.addGuitar(serial, Double.parseDouble(price), spec);

			try (CSVPrinter printer = new CSVPrinter(new FileWriter(INVENTORY_FILE, true),
					CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
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
