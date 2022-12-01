
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
		case "Any": return ANY;
		default: return null;
		}
	}
}
