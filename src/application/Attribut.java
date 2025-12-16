package application;


public class Attribut {
	private String name ="";
	private String sqlType = "";
	private String type="";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Attribut(String name,String sqlType) {
		setName(name);
		setSqlType(sqlType);
	}
}
/*
List of possible type :
	pk INT (increment 0 to line)
	fk INT (random from line)
	past date DATE (random from last year ?)
	upcom date DATE (random from next year ?)
	1digit INT (Random 1-9)
	2digit INT (Random 1-99)
	2digit2 FLOAT
	2digit3 FLOAT
	2.2digit DOUBLE
	2.3digit DOUBLE
	name VARCHAR (from name list)
	address VARCHAR (address from list ?)
	city VARCHAR (from city list)
	phone VARCHAR (phone nbr from list ?)
	int VARCHAR (Random int to string)
	undefined N/A (keep empty, data type not supported)
*/