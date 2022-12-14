
public class GuitarSpec {
	private Builder builder;
	private String model;
	private Type type;
	private Wood backWood;
	private Wood topWood;
	
	public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood) {
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
	}
	
	public GuitarSpec(String builder, String model, String type, String backWood, String topWood) {
		this.builder = Builder.fromString(builder);
		this.model = model;
		this.type = Type.fromString(type);
		this.backWood = Wood.fromString(backWood);
		this.topWood = Wood.fromString(topWood);
	}

	public Builder getBuilder() {
		return this.builder;
	}
	
	public String getModel() {
		return this.model;
	}

	public Type getType() {
		return this.type;
	}
	
	public Wood getBackWood() {
		return this.backWood;
	}
	
	public Wood getTopWood() {
		return this.topWood;
	}
	
	@Override
	public boolean equals(Object obj) {
		GuitarSpec spec = (GuitarSpec) obj;
		return spec.getBuilder() == this.builder && spec.getModel().equals(this.model) && spec.getType() == this.type 
				&& spec.getBackWood() == this.backWood && spec.getTopWood() == this.topWood;
	}
	
	public boolean softEquals(Object obj) {
		GuitarSpec spec = (GuitarSpec) obj;
		Builder specBuilder = spec.getBuilder();
		String specModel = spec.getModel();
		Type specType = spec.getType();
		
		return (spec.getBuilder() == this.builder) && 
				(spec.getModel().equals(this.model)) && 
				(spec.getType() == this.type) && 
				(spec.getBackWood() == this.backWood) && 
				(spec.getTopWood() == this.topWood);
	}
	
	@Override
	public String toString() {
		return this.builder + " " + this.model + " " + this.type + " " + this.backWood + " " + this.topWood;
	}
}
