package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParserSql {
	public static void readFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			int currentTable=-1;
			
			Table.tables.clear();
			
			while ((line = reader.readLine()) != null) { //read every line, one by one
				switch(StatementType(line)) {
				case "CREATE TABLE":
					currentTable=tableFound(line); //when create table found
					break;
				case "END OF TABLE": //when line start with ")" it means the table end
					currentTable=-1;
					break;
				default :
					if (currentTable>=0&&!line.trim().equals("")) { //else, if we are in a table, it means we found an attribute
						attributFound(currentTable,line);
					}
				}
			}
			reader.close();
		}catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static String StatementType(String sqlLine) { //check the start of every line
		if(sqlLine.startsWith("CREATE TABLE")) {
			return "CREATE TABLE";
		} else if (sqlLine.startsWith(")")) {
			return "END OF TABLE";
		}		
		return "DEFAULT";
	}
	
	private static int tableFound(String sqlLine) { //when a new table is found, create a new table
		int tableIndex;
		int start = sqlLine.indexOf("CREATE TABLE");
		int end = sqlLine.indexOf("(");
		
		if (start > -1 && end > -1 && start < end) {
			Table.newTable(sqlLine.substring(12,end).trim()); //extract the name and save it with the new table
			tableIndex=Table.tables.size()-1;
		} else {
			return -1;
		}
		
		return tableIndex; //return the index of the new table
	}
	
	private static void attributFound(int tableIndex,String sqlLine) {// when a new attribute is found, put it on right table
		String nom="";
		String sqlType="";
		String[] words;
		int start;
		int end;
		
		if(sqlLine.trim().startsWith("PRIMARY KEY")) { //if it's a primary key, modify the correct attribute
			start=sqlLine.indexOf("(");
			end=sqlLine.indexOf(")");
			
			nom=sqlLine.substring(start+1,end);
			for (int i=0; i<Table.tables.get(tableIndex).getAttrib().size();i++) {
				if(Table.tables.get(tableIndex).getAttrib().get(i).getName().equalsIgnoreCase(nom)) { //find the correct attribute by listing them all and checking their name
					Table.tables.get(tableIndex).getAttrib().get(i).setSqlType("PRIMARY KEY"); //replace the sqlType by Primary Key
				}
			}
			
		} else if (sqlLine.trim().startsWith("FOREIGN KEY")) { //Same as PK for FK
			start=sqlLine.indexOf("(");
			end=sqlLine.indexOf(")");
			
			nom=sqlLine.substring(start+1,end);
			for (int i=0; i<Table.tables.get(tableIndex).getAttrib().size();i++) { //Same loop than PK
				if(Table.tables.get(tableIndex).getAttrib().get(i).getName().equalsIgnoreCase(nom)) {
					Table.tables.get(tableIndex).getAttrib().get(i).setSqlType("FOREIGN KEY");
				}
			}
		} else if (sqlLine.trim().startsWith(",CONSTRAINT")) {//if it start with constraint, need to find if it's a primary or foreign key
			if(sqlLine.contains("PRIMARY KEY")) {
				start=sqlLine.indexOf("(");
				end=sqlLine.indexOf(")");
				
				nom=sqlLine.substring(start+1,end);
				for (int i=0; i<Table.tables.get(tableIndex).getAttrib().size();i++) {
					if(Table.tables.get(tableIndex).getAttrib().get(i).getName().equalsIgnoreCase(nom)) { //find the correct attribute by listing them all and checking their name
						Table.tables.get(tableIndex).getAttrib().get(i).setSqlType("PRIMARY KEY"); //replace the sqlType by Primary Key
					}
				}
			} else if (sqlLine.contains("FOREIGN KEY")) {
				start=sqlLine.indexOf("(");
				end=sqlLine.indexOf(")");
				
				nom=sqlLine.substring(start+1,end);
				for (int i=0; i<Table.tables.get(tableIndex).getAttrib().size();i++) { //Same loop than PK
					if(Table.tables.get(tableIndex).getAttrib().get(i).getName().equalsIgnoreCase(nom)) {
						Table.tables.get(tableIndex).getAttrib().get(i).setSqlType("FOREIGN KEY");
					}
				}
			}
		} else { //else, it's a normal attribute
			words=sqlLine.trim().replaceAll(" +", " ").split(" "); //delete extra spaces then split words separated by single space
			nom=words[0]; // extract the name
			sqlType=words[1].replace(",",""); //extract the Type without its ','
			if(nom!=""&&sqlType!="") {
				Table.tables.get(tableIndex).addAttrib(nom, sqlType); //if it's not empty (shouldn't be), create new attribute
			} else {
				System.out.println("error in attribut value");
			}
		}
	}
}
