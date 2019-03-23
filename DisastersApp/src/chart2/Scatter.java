package chart2;

import java.util.ArrayList;

import data.DataManager;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class Scatter extends Chart {

	
	public Scatter(DataManager dm) {
		super();
		this.dm = dm;
		title = "Scatter Chart Sample";
	}
	
	@Override
	public void lineChartWork() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void barChartWork() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scatterChartWork() {
		// TODO Auto-generated method stub
//		final ScatterChart<String,Number> sc = new
//	            ScatterChart<String,Number>(xAxis,yAxis);
//		 sc.setTitle("Country Summary");
		chart =  new ScatterChart<String,Number>(xAxis,yAxis);
		chart.setTitle("Country Summary");
	}
	

	@Override
	public Scene doWorkGetScene(ArrayList<XYChart.Series> seriesList, int widht, int height ) {
		// TODO Auto-generated method stub
		for (int i = 0; i < seriesList.size(); i++) {
        	 ((ScatterChart<String, Number>) chart).getData().add(seriesList.get(i));
        }
        
		Scene scene  = new Scene(chart, widht, height);
        return scene;
	}

	

	

}
