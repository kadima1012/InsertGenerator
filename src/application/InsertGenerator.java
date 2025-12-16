package application;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InsertGenerator 
{
	public static void sqlGenerator() {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		try {
		  File file = new File("insert.sql");
		  file.createNewFile();
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		
		try {
		  FileWriter writer = new FileWriter("insert.sql");
		  
		  for (int i=0;i<Table.tables.size();i++) {
			  for(int line=0;line<Table.getNbrOfLine();line++) {
				  columns.clear();
				  values.clear();
				  for (int j=0;j<Table.tables.get(i).getAttrib().size();j++) {
					  columns.add(Table.tables.get(i).getAttrib().get(j).getName());
					  values.add(Table.tables.get(i).setValue(Table.tables.get(i).getAttrib().get(j).getType()));
				  }
				  writer.write(generateInsert(Table.tables.get(i).getName(),columns.toArray(new String[0]),values.toArray(new String[0])));
				  writer.write(System.lineSeparator());
			  }
			}

		  writer.close();
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	
	private static String generateInsert(String tableName, String[] columns, String[] values) 
	{
		
		StringBuilder sql = new StringBuilder(); // Create a StringBuilder object to build the SQL query.		
		
		sql.append("INSERT INTO ").append(tableName).append(" ("); // Add the "INSERT INTO" part of the query with the table name.
				
		for(int i = 0; i < columns.length; i++) // Browse the columns and add them to the list of columns in the query.
		{
			sql.append(columns[i]);			
			
			if(i < columns.length - 1) // If it's not the last column, add a comma to separate the columns.
			{
				sql.append(", ");
			}
		}
				
		sql.append(") VALUES ("); // Add the "VALUES" part to the query.
				
		for(int i = 0; i < values.length; i++) // Browse the values and add them to the list of values in the query.
		{
			sql.append("'").append(values[i]).append("'"); // Surround each value with single quotes for string values.
							
			if(i < values.length - 1)// If it's not the last value, add a comma to separate them.
			{
				sql.append(", ");
			}
		
		}
				
		sql.append(");"); // Add a semicolon at the end of the SQL query.
				
		return sql.toString();	// Return the SQL query as a string.	
		
	}
}

