
public enum Builder {
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
		switch(str) {
		case "Fender": return FENDER;
		case "Martin": return MARTIN;
		case "Gibson": return GIBSON;
		case "Collings": return COLLINGS;
		case "Olson": return OLSON;
		case "Ryan": return RYAN;
		case "PRS": return PRS;
		case "ANY": return ANY;
		default: return null;
		}
	}
}
