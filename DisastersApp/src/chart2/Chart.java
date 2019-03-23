package chart2;

import java.util.ArrayList;

import data.DataManager;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public abstract class Chart {
	
	private static final int  SCENE_WIDTH = 1000;
	private static final int  SCENE_HEIGHT = 800;
	
	protected String title="";
	protected DataManager dm;
	
	protected javafx.scene.chart.Chart chart;
	
	protected final CategoryAxis xAxis = new CategoryAxis();
	protected final NumberAxis yAxis = new NumberAxis(); 
    
    public  abstract void lineChartWork();
    
    public  abstract void barChartWork();
    
    public  abstract void scatterChartWork();
    public  abstract Scene doWorkGetScene(ArrayList<XYChart.Series> seriesList, int widht, int height);
    
     public void show(Stage stage) {
    	
    	stage.setTitle(title);
		 
        xAxis.setLabel("Years");                
        yAxis.setLabel("Persons");
        
       
//        TODO bar method , line Method
        lineChartWork();
        barChartWork();
        scatterChartWork();
        
        ArrayList<XYChart.Series> seriesList = new ArrayList<XYChart.Series>();
        
        String indicator = dm.getEntryIndicator(0);
        String country = dm.getEntryCountry(0);
              
        XYChart.Series series = new XYChart.Series();
        series.setName(dm.getEntryCountry(0) + "-" + dm.getEntryIndicator(0));
        seriesList.add(series);
        
        for(int i = 0; i < dm.getEntriesSize(); i++ ) {
        	if (! indicator.equals(dm.getEntryIndicator(i))) {
        		indicator = dm.getEntryIndicator(i);
        		country = dm.getEntryCountry(i);
        		series = new XYChart.Series();
    			series.setName(dm.getEntryCountry(i)+ "-" + dm.getEntryIndicator(i));
    			series.getData().add(new XYChart.Data(Integer.toString(dm.getEntryYear(i)), dm.getEntryValue(i)));
   			 	seriesList.add(series);		
        	}
        	else {
        		if ( ! country.equals(dm.getEntryCountry(i))){
        			country = dm.getEntryCountry(i);
        			series = new XYChart.Series();
        			series.setName(dm.getEntryCountry(i)+ "-" + dm.getEntryIndicator(i));
        			series.getData().add(new XYChart.Data(Integer.toString(dm.getEntryYear(i)), dm.getEntryValue(i)));
       			 	seriesList.add(series);
        		}
        		else {
        			int size = seriesList.size();
        			seriesList.get(size-1).getData().add(new XYChart.Data(Integer.toString(dm.getEntryYear(i)), dm.getEntryValue(i)));
        		}
        	}
        	
        } 
        
        Scene scene  = doWorkGetScene(seriesList, SCENE_WIDTH, SCENE_HEIGHT );
//        for (int i=0; i<seriesList.size(); i++) {
//        	System.out.println("name "+seriesList.get(i).getName());
//        }
        stage.setScene(scene);
        stage.show();
    }
     
    
	
}
