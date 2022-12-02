import java.util.Arrays;

public enum Type {
	ACOUSTIC, ELECTRIC, ANY;
	
	@Override
	public String toString() {
		switch(this) {
		case ACOUSTIC: return "Acoustic";
		case ELECTRIC: return "Electric";
		case ANY:
		default: return null;
		}
	}
	
	public static Type fromString(String str) {
		switch(str) {
		case "Acoustic": return ACOUSTIC;
		case "Electric": return ELECTRIC;
		case "":
		case "Any": return ANY;
		default: return null;
		}
	}
	
	public static String printPossibleValues() {
		String possible = Arrays.asList(values()).subList(0, values().length-1).toString();
		return possible.substring(1, possible.length()-1);
	}
}
