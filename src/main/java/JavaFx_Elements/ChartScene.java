package JavaFx_Elements;

import Models.Location_Model;
import Models.Record_Model;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class ChartScene {
    private List<Location_Model> locations = new ArrayList<>();
    private List<Record_Model> records = new ArrayList<>();
    private String country;
    public ChartScene(List<Location_Model> locations, List<Record_Model> records, String county){
        this.locations = locations;
        this.records = records;
        this.country = county;
    }

    public Scene diaScreen(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle(this.country + " CoVID-19");
        xAxis.setLabel("Date");
        yAxis.setLabel("Infections");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(country);
        for (Record_Model rc: this.records) {
            if((rc.getCountry() + " " + rc.getProvince()).equals(this.country)){
                series1.getData().add(new XYChart.Data(rc.getDate(), rc.getIll()));
            }
        }
        bc.getData().addAll(series1);
        Scene scene = new Scene(bc);
        return scene;
    }

}
