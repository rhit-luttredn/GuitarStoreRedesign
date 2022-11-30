
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
}
