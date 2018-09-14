package factory;

public enum CustomerType {
	NORMAL("normal"),
	PRIME("prime");
	
	private final String type;
	
	CustomerType(String type){
		this.type = type;
	}
	
	public String getValue() {
		return type;
	}
}
