package application;
	

import java.util.ArrayList;
import java.util.HashMap;

import chart2.Bar;
import chart2.Line;
import chart2.Scatter;
import data.DataManager;
import data.Entry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

public class Main extends Application implements EventHandler<ActionEvent>{
	
	private static DataManager dm;
	private Button button; 
	private Button button2;
	private MenuButton countriesMenuItem;
	private MenuButton disastersMenuItem;
	private MenuButton yearsMenuItem;
	private MenuButton chartsMenuItem;
	private MenuButton fiveYearsMenuItem;
	private MenuButton tenYearsMenuItem;
	private MenuButton twentyYearsMenuItem;
	private CheckBox cbAvg5;
	private CheckBox cbAvg10;
	private CheckBox cbAvg20;
	private Text t;
	private HashMap<String, String> indicators = new HashMap<String, String>();
	
	private ObservableList<CheckMenuItem> list = FXCollections.observableArrayList();
	private ArrayList<CheckMenuItem> selected = new ArrayList<CheckMenuItem>();
	
	private Stage primaryStage;
	private Stage secondStage; 
	
	private boolean yearsFlag;
	private boolean fiveYearsFlag;
	private boolean tenYearsFlag;
	private boolean twentyYearsFlag;
	private boolean indicatorFlag = false;
	private boolean avg5Flag = false;
	private boolean avg10Flag = false;
	private boolean avg20Flag = false;
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		
		try {
			cbAvg5= createCheckBox("AVG  5", 420, 300);
			cbAvg10= createCheckBox("AVG 10", 420, 320);
			cbAvg20= createCheckBox("AVG 20", 420, 340);
			
			button = createButton("OK", 770, 400, 100, 40);
			button.setOnAction(this);
			
			button2 = createButton("Default", 880, 400, 100, 40 );
			button2.setOnAction(this);
			
			createCountriesCheckMenuItemList();
			countriesMenuItem = createMenuButton("Select countries", 264, 150, 150, 20);
			
			createDisastersCheckMenuItemList();
			disastersMenuItem = createMenuButton("Select disasters", 100, 150, 150, 20);
			
			createYearsCheckMenuItemList();
			yearsMenuItem = createMenuButton("Select years", 420, 150, 150, 20);
			
			createChartsMenuItemList();
			chartsMenuItem = createMenuButton("Select chart", 580, 150, 150, 20);
			
			createFiveYearsMenuItemList();
			fiveYearsMenuItem = createMenuButton("Select five years in a row", 420, 190, 150, 20);
			
			createTenYearsMenuItemList();
			tenYearsMenuItem = createMenuButton("Select ten years in a row", 420, 230, 150, 20);
			
			createTwentyYearsMenuItemList();
			twentyYearsMenuItem = createMenuButton("Select twenty years in a row", 420, 270, 150, 20);
			
			secondStage = new Stage();
			
			t = new Text(300, 50, "Disasters of the world");
			t.setFont(new Font(30));
			
			Pane root = new Pane();
			root.getChildren().addAll(cbAvg20,cbAvg10,cbAvg5,twentyYearsMenuItem, tenYearsMenuItem,fiveYearsMenuItem,yearsMenuItem,
										disastersMenuItem, countriesMenuItem, chartsMenuItem, button, button2, t);
			
			root.setId("pane");
			
			Scene scene = new Scene(root,1000,500);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		dm = new DataManager();
		launch(args);
	}
	
	public Button createButton (String text, int layoutX, int layoutY,
									int PrefSizeW, int PrefSizeH) {
		Button b = new Button();
		b.setText(text);
		b.setPrefSize(PrefSizeW, PrefSizeH);
		b.setLayoutX(layoutX);
		b.setLayoutY(layoutY);
		return b;
	}
	
	public MenuButton createMenuButton(String text, int layoutX, int layoutY,
										int PrefSizeW, int PrefSizeH) {
		
		MenuButton menuButton = new MenuButton();
		menuButton.setText(text);
		menuButton.setLayoutX(layoutX);
		menuButton.setLayoutY(layoutY);
		menuButton.setPrefSize(PrefSizeW, PrefSizeH);
		menuButton.getItems().addAll(list);
		list.clear();
		return menuButton;
	}

	public CheckBox createCheckBox(String text, int layoutX, int layoutY) {
		CheckBox cb = new CheckBox(text);
		cb.setLayoutX(layoutX);
		cb.setLayoutY(layoutY);
		return cb;
		
	}
	public void createCountriesCheckMenuItemList() {
		ArrayList<String> result = dm.getAllCountries();
		for (String str : result) {
			list.add(new CheckMenuItem(str));
		}
		dm.clearResults();
	}
	
	public void createYearsCheckMenuItemList() {
		ArrayList<String> result = dm.getAllYears();
		for (String str : result) {
			list.add(new CheckMenuItem(str));
		}
		dm.clearResults();
	}
	
	public void createDisastersCheckMenuItemList() {
		
		list.add(new CheckMenuItem("air accident killed"));
		indicators.put("air accident killed", "AIR_ACCIDENT_KILLED");
		list.add(new CheckMenuItem("air accident affected"));
		indicators.put("air accident affected", "AIR_ACCIDENT_AFFECTED");
		list.add(new CheckMenuItem("drought killed"));
		indicators.put("drought killed", "DROUGHT_KILLED");
		list.add(new CheckMenuItem("drought affected"));
		indicators.put("drought affected", "DROUGHT_AFFECTED");
		list.add(new CheckMenuItem("earthquake killed"));
		indicators.put("earthquake killed", "EARTHQUAKE_KILLED");
		list.add(new CheckMenuItem("earthquake affected"));
		indicators.put("earthquake affected", "EARTHQUAKE_AFFECTED");
		list.add(new CheckMenuItem("flood killed"));
		indicators.put("flood killed", "FLOOD_KILLED");
		list.add(new CheckMenuItem("flood affected"));
		indicators.put("flood affected", "FLOOD_AFFECTED");
		list.add(new CheckMenuItem("storm killed"));
		indicators.put("storm killed", "STORM_KILLED");
		list.add(new CheckMenuItem("storm affected"));
		indicators.put("storm affected", "STORM_AFFECTED");
	}
	
	public void createFiveYearsMenuItemList() {
		list.add(new CheckMenuItem("1970-1974"));
		list.add(new CheckMenuItem("1975-1979"));
		list.add(new CheckMenuItem("1980-1984"));
		list.add(new CheckMenuItem("1984-1989"));
		list.add(new CheckMenuItem("1990-1994"));
		list.add(new CheckMenuItem("1995-1999"));
		list.add(new CheckMenuItem("2000-2004"));
		list.add(new CheckMenuItem("2005-2008"));
	}
	
	public void createTenYearsMenuItemList() {
		list.add(new CheckMenuItem("1970-1979"));
		list.add(new CheckMenuItem("1980-1989"));
		list.add(new CheckMenuItem("1990-1999"));
		list.add(new CheckMenuItem("2000-2008"));
	}
	
	public void createTwentyYearsMenuItemList() {
		list.add(new CheckMenuItem("1970-1989"));
		list.add(new CheckMenuItem("1990-2008"));
	}
	
	public void createChartsMenuItemList() {
		list.add(new CheckMenuItem("Bar Chart"));
		list.add(new CheckMenuItem("Line Chart"));
		list.add(new CheckMenuItem("Scatter Chart"));
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if ( event.getSource() == button) {
			System.out.println("ok button");
			yearsFlag = false;
			fiveYearsFlag = false;
			tenYearsFlag = false;
			twentyYearsFlag = false;
			
			for(MenuItem item: disastersMenuItem.getItems()) {
				CheckMenuItem checkMenuItem = (CheckMenuItem) item;
				if(checkMenuItem.isSelected()) {
					selected.add(checkMenuItem);
					indicatorFlag = true;
					dm.addIndicator(indicators.get(item.getText()));
				}
			}
			if (indicatorFlag == false ) {
				errorIndicatorCategory();
				dm.clearAll();
				return;
			}
			
			for(MenuItem item : countriesMenuItem.getItems()) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if(checkMenuItem.isSelected()) {
                	selected.add(checkMenuItem);
                	dm.addCountry(item.getText());
                }
            }
			
			for(MenuItem item : yearsMenuItem.getItems()) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if(checkMenuItem.isSelected()) {
                	selected.add(checkMenuItem); 
                	yearsFlag = true;
     				if ( (fiveYearsFlag==false & tenYearsFlag==false & twentyYearsFlag==false) ) {
     					dm.addYear(Integer.parseInt(item.getText()));
     				}
     				else {
     					errorYearsCategory();
     				}
                }
                
               
            }
			
			for(MenuItem item : fiveYearsMenuItem.getItems()) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if(checkMenuItem.isSelected()) {
                	selected.add(checkMenuItem); 
                	fiveYearsFlag = true;
      				if ( (yearsFlag==false & tenYearsFlag==false & twentyYearsFlag==false) ) {
      					dm.addFiveOrTenOrTwentyYears(item.getText());
      				}
      				else {
      					errorYearsCategory();
      				}
                }
            }
			
			for(MenuItem item : tenYearsMenuItem.getItems()) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if(checkMenuItem.isSelected()) {
                	selected.add(checkMenuItem); 
                	tenYearsFlag = true;
      				if ( (yearsFlag==false & fiveYearsFlag==false & twentyYearsFlag==false) ) {
      					dm.addFiveOrTenOrTwentyYears(item.getText());
      				}
      				else {
      					errorYearsCategory();
      				}
                }
            }
			
			for(MenuItem item : twentyYearsMenuItem.getItems()) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if(checkMenuItem.isSelected()) {
                	selected.add(checkMenuItem); 
                	twentyYearsFlag = true;
      				if ( (yearsFlag==false & fiveYearsFlag==false & tenYearsFlag==false) ) {
      					dm.addFiveOrTenOrTwentyYears(item.getText());
      				}
      				else {
      					errorYearsCategory();
      				}
                }
            }
			String str = "";
			if (cbAvg5.isSelected()) {
//				avg by 5 years
				str = dm.createAvg(5,8);
				avg5Flag = true;
			}
			else if (cbAvg10.isSelected()) {
//				avg by 10 years
				str = dm.createAvg(10,4);
				avg10Flag = true;
			}
			else if (cbAvg20.isSelected()) {
//				avg by 20 years
				str = dm.createAvg(20,2);
				avg20Flag = true;
			}else {
				avg5Flag = false;
				avg10Flag = false;
				avg20Flag = false;
				str = dm.createEntries();
			}
			
			String s[]= {"",""};
			if (! str.equals("")) {
				String s2[] = str.split(",");
				s=s2;
			}
		
			if(! str.equals("")) {
				dm.clearAll();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("There are no data for the following country in:  " +s[1]);
				alert.setContentText(s[0]);

				alert.showAndWait();
				System.out.println(str);
			}
			else {
				if (dm.getEntriesSize() > 1) {
					
					dm.sortByCountry();
					
//					den 8umamai giati sortarame ana xronia
//					if (avg5Flag || avg10Flag || avg20Flag) {
//						dm.sortByCountry();
//					}
//					else {
//						dm.sortByCountry();
//						dm.sortByYear();
//					}
				}
				for(MenuItem item : chartsMenuItem.getItems()) {
					CheckMenuItem checkMenuItem = (CheckMenuItem) item;
					if(checkMenuItem.isSelected()) {
						selected.add(checkMenuItem);
						if(item.getText().equals("Bar Chart")) {
	                    	if (isYearsCategoryOk()) {
	                    		Bar barChart2 = new Bar(dm);
	                    		barChart2.show(secondStage);
	                    	}
	                    	
	                    }
	                    else if(item.getText().equals("Line Chart")) {
	                    	if (isYearsCategoryOk()) {
	                    		Line lineChart1 = new Line(dm);
	                    		lineChart1.show(secondStage);
	                    		dm.printEntries();
	                    	}
	                    	
	                    }
	                    else if(item.getText().equals("Scatter Chart")) {
	                    	if (isYearsCategoryOk()) {
	                    		Scatter scatterChart1 = new Scatter(dm);
	                    		scatterChart1.show(secondStage);
	                    	}
	                    }
	                }
				}
				dm.clearAll();
			}
		}
		if( event.getSource() == button2) {
			
			for(int y=0;y<selected.size();y++) {
				selected.get(y).setSelected(false);
			}

		}
	}
	
	public void errorIndicatorCategory() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Υou have not chosen disaster ");

		alert.showAndWait();
	}
	public void errorYearsCategory() {
		String str="";
		if (yearsFlag) {
			str = str+ "- one year -";
		}
		if(fiveYearsFlag) {
			str = str+ "- five years -";
		}
		if(tenYearsFlag) {
			str = str+ "- ten years -";
		}
		if(tenYearsFlag) {
			str = str+ "- twenty years -";
		}
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Υou have chosen above one category ");
		alert.setContentText(str);

		alert.showAndWait();
	}
	
	public boolean isYearsCategoryOk() {
		if (yearsFlag) {
			if(fiveYearsFlag==false & tenYearsFlag==false & twentyYearsFlag==false) {
				return true;
			}
		}
		if (fiveYearsFlag) {
			if(yearsFlag==false & tenYearsFlag==false & twentyYearsFlag==false) {
				return true;
			}
		}
		if (tenYearsFlag) {
			if(yearsFlag==false & fiveYearsFlag==false & twentyYearsFlag==false) {
				return true;
			}
		}
		if (twentyYearsFlag) {
			if(yearsFlag==false & fiveYearsFlag==false & tenYearsFlag==false) {
				return true;
			}
		}
		if (avg5Flag == true & avg10Flag == false & avg20Flag == false) {
			return true;
		}
		if (avg5Flag == false & avg10Flag == true & avg20Flag == false) {
			return true;
		}
		if (avg5Flag == false & avg10Flag == false & avg20Flag == true) {
			return true;
		}
		return false;
	}
	
	
}
