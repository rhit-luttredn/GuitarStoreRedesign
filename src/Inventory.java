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

	public Inventory(Reader file) throws Exception {
		this.guitars = new LinkedList<>();
		
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
			
			this.guitars.add(guitar);
		}
		System.out.println(this.guitars);
	}

	public void addGuitar(String serial, Double price, GuitarSpec spec) {
		this.guitars.add(new Guitar(serial, price, spec));
	}

	public void removeGuitar(String serialNumber) {

	}

	public List<Guitar> searchGuitar(GuitarSpec spec) {
		return null;
	}
}
