package data;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import application.Main;
import connect.connectToDatabase;

public class DataManager {
	
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<String> indicators = new ArrayList<String>();
	private ArrayList<String> result = new ArrayList<String>();
	private HashMap<String, Integer> countries = new HashMap<String, Integer>();
	private HashMap<Integer, Integer> years = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> byFiveYears = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> byTenYers = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> byTwentyYears = new HashMap<Integer, Integer>();
	private connectToDatabase connectDb;
	
	public DataManager() {
		connectDb = new connectToDatabase();
	}
	
	public void addCountry(String country)  {
		String query = "SELECT disasters.COUNTRIES.C_ID "
							+ " FROM disasters.COUNTRIES "
							+" WHERE disasters.COUNTRIES.C_NAME = "+ "'"+country+ "'"; 
		
		connectDb.sendQuery(query, result, "C_ID");
		int countryId = Integer.parseInt(result.get(0));
		result.remove(0);
		countries.put(country, countryId);
		
	}
	
	public void addYear(int year) {
		String query = "SELECT disasters.YEARS.YR_ID "
						+" FROM disasters.YEARS "
						+" WHERE disasters.YEARS.YEAR = "+ "'"+year+ "'";  
		
		connectDb.sendQuery(query, result, "YR_ID");
		int yearId = Integer.parseInt(result.get(0));
		result.remove(0);
		years.put(year, yearId);
	}
	
	public void addFiveOrTenOrTwentyYears(String years) {
		String interval[] = years.split("-");
		int start = Integer.parseInt(interval[0]);
		int end = Integer.parseInt(interval[1]);
		for (int year = start; year<= end; year++) {
			addYear(year);
		}
	}
	
	public void addIndicator(String indicator) {
		indicators.add(indicator);
	}
	
	public String createEntries() {
		for (int i = 0; i < indicators.size(); i++ ) {
			for (String keyCountry: countries.keySet()) {
				for (Integer keyYear: years.keySet() ) {
//					System.out.println(years.size()+" "+indicators.get(i)+" "+ keyCountry +" "+countries.get(keyCountry)+" "+keyYear+" "+years.get(keyYear) );
					String query = "SELECT disasters."+indicators.get(i)+".VALUE "
								+" FROM disasters."+indicators.get(i)
								+" WHERE disasters."+indicators.get(i)+".YR_ID = "+ years.get(keyYear)+" and "
								+" disasters."+indicators.get(i)+".C_ID = " + countries.get(keyCountry);
					
					connectDb.sendQuery(query, result, "VALUE");
					if(result.isEmpty()) {
						return keyCountry+","+indicators.get(i);
					}
					
					double value = Double.parseDouble(result.get(0));
//					TODO put noise
//					Random rand = new Random();
//					int randomNum = rand.nextInt(10) + 1;
//					if (randomNum  > 4) {
//						value = value + 0.1;
//					}
					
					result.remove(0);
					Entry entry = new Entry(indicators.get(i), keyCountry, keyYear, value);
					entries.add(entry);
				}
			}
		}
		return "";
	}

	
	public String createAvg(int byYears,  int endYearId) {
		for (int i = 0; i < indicators.size(); i++ ) {
			for (String keyCountry: countries.keySet()) {
				int y = 1970;
				for(int y5 = 0; y5 < endYearId; y5++) {
					String query = "SELECT avg(disasters."+indicators.get(i)+".VALUE) "
									+" FROM disasters."+indicators.get(i)
									+" INNER JOIN disasters.YEARS ON"+" disasters."+indicators.get(i)+".YR_ID = disasters.YEARS.YR_ID AND"
									+ " disasters.YEARS.YR5_ID="+y5+"   and disasters."+indicators.get(i)+".C_ID = "+countries.get(keyCountry) +";";
					connectDb.sendQuery(query, result, "");
					if(result.isEmpty()) {
						return keyCountry+","+indicators.get(i);
					}
					double value = Double.parseDouble(result.get(0));
					result.remove(0);
					y += byYears;
					Entry entry = new Entry(indicators.get(i), keyCountry, y, value);
					entries.add(entry);
					
				}
			}
		}
		return "";
	}
	
	public void clearAll() {
		countries.clear();
		years.clear();
		entries.clear();
		indicators.clear();
	}
	
	public ArrayList<String> getAllCountries(){
		String query = "SELECT * FROM disasters.COUNTRIES ";
			connectDb.sendQuery(query, result, "C_NAME");
			return result;
	}

	public ArrayList<String> getAllYears(){
		String query = "SELECT * FROM disasters.YEARS ";
			connectDb.sendQuery(query, result, "YEAR");
			return result;
	}
	
	public void clearResults() {
		result.clear();
	}
	
	public void printEntries() {
		for (int i = 0; i < entries.size(); i++) {
			
			System.out.println(entries.get(i).getIndicator()+" "+entries.get(i).getCountry()+" "+entries.get(i).getYear()+" "+ entries.get(i).getValue() );
		}
	}
	
	public void sortByYear() {
		Collections.sort(entries, Entry.byYear);
	}
	
	public void sortByCountry() {
		Collections.sort(entries, Entry.byCountry);
	}
	
	public int getEntriesSize() {
		return entries.size();
	}
	
	public String getEntryIndicator(int index) {
		return entries.get(index).getIndicator();
	}
	
	public String getEntryCountry(int index) {
		return entries.get(index).getCountry();
	}
	
	public int getEntryYear(int index) {
		return entries.get(index).getYear();
	}
	
	public double getEntryValue(int index) {
		return entries.get(index).getValue();
	}
}
