package application;

import java.util.ArrayList;
import java.util.Scanner;

public class Identification {
	public static Scanner scan = new Scanner(System.in);
	private static String str;
	private static char c_Rep;
	public static void askUser(Table table) {
		ArrayList<Attribut> list = table.getAttrib();
		for(int i = 0;i<list.size();i++) {
			switch(list.get(i).getSqlType().toUpperCase()) {
			case "PRIMARY KEY" :
				list.get(i).setType("pk");
				break;
			case "FOREIGN KEY" :
				list.get(i).setType("fk");
				break;
			case "VARCHAR" :
			case "VARCHAR(50)":
				do {
					System.out.println("---");
					System.out.println("Column : "+list.get(i).getName());
					System.out.println("Value type : VARCHAR");
					System.out.println("---");
					System.out.println("1/ Last name");
					System.out.println("2/ First name");
					System.out.println("3/ Address");
					System.out.println("4/ City");
					System.out.println("5/ Phone number");
					System.out.println("6/ Random int");
					System.out.println("7/ Brand");
					System.out.println("8/ Model");
					System.out.println("---");
					str=scan.nextLine();
					c_Rep=str.charAt(0);
					switch(c_Rep){
					case '1' :
						list.get(i).setType("lastName");
						break;
					case '2' :
						list.get(i).setType("firstName");
						break;
					case '3' :
						list.get(i).setType("address");
						break;
					case '4' :
						list.get(i).setType("city");
						break;
					case '5' :
						list.get(i).setType("phone");
						break;
					case '6' :
						list.get(i).setType("int");
						break;
					case '7' :
						list.get(i).setType("brand");
						break;
					case '8' :
						list.get(i).setType("model");
						break;
					default : 
							System.out.println("Enter correct character");
							c_Rep='x';
					}
				}while (c_Rep=='x');
				break;
			case "DATE" :
				do {
					System.out.println("---");
					System.out.println("Column : "+list.get(i).getName());
					System.out.println("Value type : DATE");
					System.out.println("---");
					System.out.println("1/ Past date");
					System.out.println("2/ Upcoming date");
					System.out.println("---");
					str=scan.nextLine();
					c_Rep=str.charAt(0);
					switch(c_Rep){
					case '1' :
						list.get(i).setType("past date");
						break;
					case '2' :
						list.get(i).setType("upcom date");
						break;
					default : 
							System.out.println("Enter correct character");
							c_Rep='x';
					}
				}while (c_Rep=='x');
				break;
			case "INT" :
				do {
					System.out.println("---");
					System.out.println("Column : "+list.get(i).getName());
					System.out.println("Value type : INT");
					System.out.println("---");
					System.out.println("1/ One digit");
					System.out.println("2/ Two digits");
					System.out.println("---");
					str=scan.nextLine();
					c_Rep=str.charAt(0);
					switch(c_Rep){
					case '1' :
						list.get(i).setType("1digit");
						break;
					case '2' :
						list.get(i).setType("2digit");
						break;
					default : 
							System.out.println("Enter correct character");
							c_Rep='x';
					}
				}while (c_Rep=='x');
				break;
			case "FLOAT" :
				do {
					System.out.println("---");
					System.out.println("Column : "+list.get(i).getName());
					System.out.println("Value type : FLOAT");
					System.out.println("---");
					System.out.println("1/ Two digits, then two after the comma");
					System.out.println("2/ Two digits, then three after the comma");
					System.out.println("---");
					str=scan.nextLine();
					c_Rep=str.charAt(0);
					switch(c_Rep){
					case '1' :
						list.get(i).setType("2digit2");
						break;
					case '2' :
						list.get(i).setType("2digit3");
						break;
					default : 
							System.out.println("Enter correct character");
							c_Rep='x';
					}
				}while (c_Rep=='x');
				break;
			case "DOUBLE" :
				do {
					System.out.println("---");
					System.out.println("Column : "+list.get(i).getName());
					System.out.println("Value type : FLOAT");
					System.out.println("---");
					System.out.println("1/ Two digits, then two after the comma");
					System.out.println("2/ Two digits, then three after the comma");
					System.out.println("---");
					str=scan.nextLine();
					c_Rep=str.charAt(0);
					switch(c_Rep){
					case '1' :
						list.get(i).setType("2.2digit");
						break;
					case '2' :
						list.get(i).setType("2.3digit");
						break;
					default : 
							System.out.println("Enter correct character");
							c_Rep='x';
					}
				}while (c_Rep=='x');
				break;
				default :
					list.get(i).setType("undefined");
					System.out.println("Data type not supported");
			}
		}
		table.setAttrib(list);
	}
}
