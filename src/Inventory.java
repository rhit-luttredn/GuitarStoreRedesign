import java.io.File;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

public class Inventory {
	private List<Guitar> guitars; // Serial Number, Guitar
	private HashMap<Builder, List<Guitar>> builderHash;
	private HashMap<String, List<Guitar>> modelHash;
	private HashMap<Type, List<Guitar>> typeHash;
	private HashMap<Wood, List<Guitar>> backwoodHash;
	private HashMap<Wood, List<Guitar>> topwoodHash;

	public Inventory(Reader file) throws Exception {
		this.guitars = new LinkedList<>();
		this.builderHash = new HashMap<>();
		this.modelHash = new HashMap<>();
		this.typeHash = new HashMap<>();
		this.backwoodHash = new HashMap<>();
		this.topwoodHash = new HashMap<>();

		// TODO: Parse file and populate guitars
		CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT.withFirstRecordAsHeader());
		for (CSVRecord csvRecord : parser) {
			// serialNumber, price, builder, model, type, backWood, topWood
			String serialNumber = csvRecord.get(0);
			double price = Double.parseDouble(csvRecord.get(1).substring(1));
			Builder builder = Builder.fromString(csvRecord.get(2));
			String model = csvRecord.get(3);
			Type type = Type.fromString(csvRecord.get(4));
			Wood backWood = Wood.fromString(csvRecord.get(5));
			Wood topWood = Wood.fromString(csvRecord.get(6));

			GuitarSpec spec = new GuitarSpec(builder, model, type, backWood, topWood);
			Guitar guitar = new Guitar(serialNumber, price, spec);

			this.addGuitar(serialNumber, price, spec);
		}
		System.out.println(this.guitars);
	}

	public void addGuitar(String serial, Double price, GuitarSpec spec) {
		Guitar guitar = new Guitar(serial, price, spec);
		this.guitars.add(guitar);

		// builderHash
		Builder builder = spec.getBuilder();
		if (!builderHash.containsKey(builder))
			builderHash.put(builder, new LinkedList<Guitar>());
		builderHash.get(builder).add(guitar);

		// modelHash
		String model = spec.getModel().toLowerCase();
		if (!modelHash.containsKey(model))
			modelHash.put(model, new LinkedList<Guitar>());
		modelHash.get(model).add(guitar);

		// typeHash
		Type type = spec.getType();
		if (!typeHash.containsKey(type))
			typeHash.put(type, new LinkedList<Guitar>());
		typeHash.get(type).add(guitar);

		// backwoodHash
		Wood backwood = spec.getBackWood();
		if (!backwoodHash.containsKey(backwood))
			backwoodHash.put(backwood, new LinkedList<Guitar>());
		backwoodHash.get(backwood).add(guitar);

		// topwoodHash
		Wood topwood = spec.getTopWood();
		if (!topwoodHash.containsKey(topwood))
			topwoodHash.put(topwood, new LinkedList<Guitar>());
		topwoodHash.get(topwood).add(guitar);
	}

	public void removeGuitar(String serialNumber) {

	}

	public static List<Guitar> union(List<Guitar> list1, List<Guitar> list2) {
		if (list1.isEmpty())
			return list2;
		if (list2.isEmpty())
			return list1;

		LinkedList<Guitar> guitars = new LinkedList<>();
		for (Guitar g1 : list1) {
			for (Guitar g2 : list2) {
				if (g1 == g2) {
					guitars.add(g1);
					break;
				}
			}
		}
		return guitars;
	}

	public List<Guitar> searchGuitar(GuitarSpec spec) {
		LinkedList<Guitar> guitars = new LinkedList<>();

		Builder builder = spec.getBuilder();
		if (builder != Builder.ANY)
			guitars.addAll(this.builderHash.get(builder));

		String model = spec.getModel().toLowerCase();
		if (!model.equals(""))
			guitars = (LinkedList<Guitar>) union(guitars, this.modelHash.get(model));

		Type type = spec.getType();
		if (type != Type.ANY)
			guitars = (LinkedList<Guitar>) union(guitars, this.typeHash.get(type));

		Wood backwood = spec.getBackWood();
		if (backwood != Wood.ANY)
			guitars = (LinkedList<Guitar>) union(guitars, this.backwoodHash.get(backwood));

		Wood topwood = spec.getTopWood();
		if (topwood != Wood.ANY)
			guitars = (LinkedList<Guitar>) union(guitars, this.topwoodHash.get(topwood));

		return guitars;
	}

	public List<Guitar> searchGuitarSingleAttribute(GuitarSpec spec) {
		return null;
	}
}
