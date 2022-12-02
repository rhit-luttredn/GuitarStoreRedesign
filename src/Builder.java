import java.util.Arrays;

public enum Builder{
	FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY;
	
	@Override
	public String toString() {
		switch(this) {
		case FENDER: return "Fender";
		case MARTIN: return "Martin";
		case GIBSON: return "Gibson";
		case COLLINGS: return "Collings";
		case OLSON: return "Olson";
		case RYAN: return "Ryan";
		case PRS: return "PRS";
		case ANY:
		default: return null;
		}
	}
	
	public static Builder fromString(String str) {
		switch(str.toLowerCase()) {
		case "fender": return FENDER;
		case "martin": return MARTIN;
		case "gibson": return GIBSON;
		case "collings": return COLLINGS;
		case "olson": return OLSON;
		case "ryan": return RYAN;
		case "prs": return PRS;
		case "":
		case "any": return ANY;
		default: return null;
		}
	}
	
	public static String printPossibleValues() {
		String possible = Arrays.asList(values()).subList(0, values().length-1).toString();
		return possible.substring(1, possible.length()-1);
	}
}
