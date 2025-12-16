package application;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Table {
	private static int nbrOfLine = 0;
	public static int getNbrOfLine() {
		return nbrOfLine;
	}
	public static void setNbrOfLine(int nbrOfLine) {
		Table.nbrOfLine = nbrOfLine;
	}

	public static ArrayList<Table> tables = new ArrayList<Table>();
	public static void newTable(String name) {
		Table table = new Table();
		table.setName(name);
		tables.add(table);
	}
	public static ObservableList<String> getAllNames(){
		ObservableList<String> list = FXCollections.observableArrayList();
		for(int i=0; i<tables.size() ; i++) {
			list.add(tables.get(i).getName());
		}
		return list;
	}
	
	private int pk=-1;
	
	private String name = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private ArrayList<Attribut> attrib = new ArrayList<Attribut>();
	public ArrayList<Attribut> getAttrib() {
		return attrib;
	}
	public void setAttrib(ArrayList<Attribut> attrib) {
		this.attrib = attrib;
	}
	public void addAttrib(String nom,String sqlType) {
		Attribut att = new Attribut(nom,sqlType);
		attrib.add(att);
	}
	public void setAttribType() {
		System.out.println("Not implemented yet");
	}
	
	public String setValue(String type) {
        String value = "";
        Random rand = new Random();
        switch (type.trim().toLowerCase()) {
            case "pk":
                pk++;
                value = String.valueOf(pk);
                break;
            case "fk":
                int randomForeignKey = rand.nextInt(Table.getNbrOfLine()) + 1;
                value = String.valueOf(randomForeignKey);
                break;
            case "last name":
                try {
                    List<String> lastNames = readDataFromFile("lastNames.txt");
                    int randomIndex = rand.nextInt(lastNames.size());
                    value = lastNames.get(randomIndex);
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the last names file.");
                    e.printStackTrace();
                }
                break;
            case "first name":
                try {
                    List<String> firstNames = readDataFromFile("firstNames.txt");
                    int randomIndex = rand.nextInt(firstNames.size());
                    value = firstNames.get(randomIndex);
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the first names file.");
                    e.printStackTrace();
                }
                break;
            case "past date":
                value = generateRandomPastDate();
                break;
            case "upcom date":
                value = generateRandomUpcomingDate();
                break;
            case "1digit":
                value = String.valueOf(rand.nextInt(9) + 1);
                break;
            case "2digit":
                value = String.valueOf(rand.nextInt(99) + 1);
                break;
            case "2digit2":
                value = String.format("%.2f", rand.nextDouble() * 100);
                break;
            case "2digit3":
                value = String.format("%.3f", rand.nextDouble() * 100);
                break;
            case "2.2digit":
                value = String.format("%.2f", rand.nextDouble() * 1000);
                break;
            case "2.3digit":
                value = String.format("%.3f", rand.nextDouble() * 1000);
                break;
            case "address":
                try {
                    List<String> addresses = readDataFromFile("addresses.txt");
                    int randomAddressIndex = rand.nextInt(addresses.size());
                    String address = addresses.get(randomAddressIndex);
                    int randomInt = rand.nextInt(999) + 1;
                    value = address + " " + randomInt;
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the addresses file.");
                    e.printStackTrace();
                }
                break;
            case "city":
                try {
                    List<String> cities = readDataFromFile("cities.txt");
                    int randomCityIndex = rand.nextInt(cities.size());
                    value = cities.get(randomCityIndex);
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the cities file.");
                    e.printStackTrace();
                }
                break;
            case "phone number":
                try {
                    List<String> phoneNumbers = readDataFromFile("phonesNR.txt");
                    int randomPhoneNumberIndex = rand.nextInt(phoneNumbers.size());
                    value = phoneNumbers.get(randomPhoneNumberIndex);
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the phone numbers file.");
                    e.printStackTrace();
                }
                break;
            case "brand":
            	try {
            		List<String> brands = readDataFromFile("brands.txt");
            		int randomBrandIndex = rand.nextInt(brands.size());
            		value = brands.get(randomBrandIndex);
            	} catch (IOException e) {
            		System.out.println("An error occurred while reading the brand file.");
                    e.printStackTrace();
            	}
                break;
            case "model":
            	try {
            		List<String> models = readDataFromFile("models.txt");
            		int randomModelIndex = rand.nextInt(models.size());
            		value = models.get(randomModelIndex);
            	} catch (IOException e) {
            		System.out.println("An error occurred while reading the model file.");
                    e.printStackTrace();
            	}	
                break;
            case "random int":
            	int randomInt = rand.nextInt(Integer.MAX_VALUE);
                value = String.valueOf(randomInt);
                break;
            default:
                value = "";
                break;
        }
        return value;
    }

    private List<String> readDataFromFile(String fileName) throws IOException {
        List<String> data = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String entry = scanner.nextLine();
            data.add(entry);
        }
        scanner.close();
        return data;
    }
    
    private String generateRandomPastDate() {
    	/*
        //Random rand = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date pastDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        return sdf.format(pastDate);
        */
    	long today = new Date().getTime();
    	long aDay = TimeUnit.DAYS.toMillis(1);
    	long pastYear = new Date(today - aDay*365).getTime();
    	long random = ThreadLocalRandom.current().nextLong(pastYear,today);
    	Date date = new Date(random);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return sdf.format(date);
    }

    private String generateRandomUpcomingDate() {
        /*
    	//Random rand = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date upcomingDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        return sdf.format(upcomingDate);
        */
        long today = new Date().getTime();
    	long aDay = TimeUnit.DAYS.toMillis(1);
    	long nextYear = new Date(today + aDay*365).getTime();
    	long random = ThreadLocalRandom.current().nextLong(today,nextYear);
    	Date date = new Date(random);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return sdf.format(date);
    }

}
