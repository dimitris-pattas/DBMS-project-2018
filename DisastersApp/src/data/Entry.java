package data;

import java.util.Comparator;

public class Entry {
	
	private String indicator = "";
	private String country = "";
	private int year;
	private double value;
	
	public Entry(String indicator, String country, int year, double value) {
		this.indicator = indicator;
		this.country = country;
		this.year = year;
		this.value = value;
	}
	
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getIndicator() {
		return indicator;
	}
	
	public String getCountry() {
		return country;
	}
	
	public int getYear() {
		return year;
	}
	
	public double getValue() {
		return value;
	}
	
	
	public static Comparator<Entry> byYear = new Comparator<Entry>() {

		public int compare(Entry e1, Entry e2) {
	
		   int year1 = e1.getYear();
		   int year2 = e2.getYear();
	
		   /*For ascending order*/
		    return Integer.compare(year1, year2);
	
		   /*For descending order*/
		   //rollno2-rollno1;
   }};
   
   public static Comparator<Entry> byCountry = new Comparator<Entry>() {

		public int compare(Entry e1, Entry e2) {
	
	
		   /*For ascending order*/
		   return e1.getCountry().compareTo(e2.getCountry());
	
		   /*For descending order*/
		   //rollno2-rollno1;
  }};
}
