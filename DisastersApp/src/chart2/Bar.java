package chart2;

import java.util.ArrayList;
import data.DataManager;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Series;

public class Bar extends Chart{
	
	public Bar(DataManager dm) {
		super();
		this.dm = dm;
		title = "Bar Chart Sample";
	}
	
	@Override
	public void barChartWork() {
		// TODO Auto-generated method stub
		chart = new BarChart<String,Number>(xAxis,yAxis);
		chart.setTitle("Country Summary");	
	}

	@Override
	public Scene doWorkGetScene(ArrayList<Series> seriesList, int widht, int height) {
		// TODO Auto-generated method stub
		Scene scene  = new Scene(chart, widht, height);
		for (int i = 0; i < seriesList.size(); i++) {
        	 ((BarChart<String, Number>) chart).getData().add(seriesList.get(i));
        }
        return scene;
	}
	
	@Override
	public void lineChartWork() {
		// TODO Auto-generated method stub
	}
	@Override
	public void scatterChartWork() {
		// TODO Auto-generated method stub
	}
}
