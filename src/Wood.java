
public enum Wood {
	INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO, ADIRONDACK, ALDER, SITKA, ANY;
	
	@Override
	public String toString() {
		switch(this) {
		case INDIAN_ROSEWOOD: return "Indian Rosewood";
		case BRAZILIAN_ROSEWOOD: return "Brazilian Rosewood";
		case MAHOGANY: return "Mahogany";
		case MAPLE: return "Maple";
		case COCOBOLO: return "Cocobolo";
		case ADIRONDACK: return "Adirondack";
		case ALDER: return "Alder";
		case SITKA: return "Sitka";
		case ANY:
		default: return null;
		}
	}
	
	public static Wood fromString(String str) {
		switch(str) {
		case "Indian Rosewood": return INDIAN_ROSEWOOD;
		case "Brazilian Rosewood": return BRAZILIAN_ROSEWOOD;
		case "Mahogany": return MAHOGANY;
		case "Maple": return MAPLE;
		case "Cocobolo": return COCOBOLO;
		case "Adirondack": return ADIRONDACK;
		case "Alder": return ALDER;
		case "Sitka": return SITKA;
		case "Any": return ANY;
		default: return null;
		}
	}
}
