package chart2;

import java.util.ArrayList;

import data.DataManager;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class Line extends Chart{
	
	public Line(DataManager dm) {
		super();
		this.dm = dm;
		title = "Line Chart Sample";
	}
	
		@Override
		public void lineChartWork() {
			// TODO Auto-generated method stub
	      
			chart = new LineChart<String,Number>(xAxis,yAxis);
			chart.setTitle("Country Summary");
		}

		@Override
		public void barChartWork() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void scatterChartWork() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Scene doWorkGetScene(ArrayList<Series> seriesList, int widht, int height) {
			// TODO Auto-generated method stub
			
			Scene scene  = new Scene(chart, widht, height);
			for (int i = 0; i < seriesList.size(); i++) {
	        	 ((LineChart<String, Number>) chart).getData().add(seriesList.get(i));
	        }
	        return scene;
		}
	
}
