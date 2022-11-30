import java.io.File;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

public class Inventory {
	private HashMap<String, Guitar> guitars; // Serial Number, Guitar

	public Inventory(Reader file) throws Exception {
		// TODO: Parse file and populate guitars
		CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT);
		for (CSVRecord csvRecord : parser) {
			// serialNumber, price, builder, model, type, backWood, topWood
			String serialNumber = csvRecord.get(0);
			double price = Double.parseDouble(csvRecord.get(1));
			String builder = csvRecord.get(2);
			String model = csvRecord.get(3);
			String type = csvRecord.get(4);
			String backWood = csvRecord.get(5);
			String topWood = csvRecord.get(6);
		}
	}

	public void addGuitar(GuitarSpec spec) {

	}

	public void removeGuitar(String serialNumber) {

	}

	public List<Guitar> searchGuitar(GuitarSpec spec) {
		return null;
	}
}
