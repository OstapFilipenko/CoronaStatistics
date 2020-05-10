import JavaFx_Elements.ChartScene;
import Models.Location_Model;
import Models.Record_Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application{
    static FileParser fl = new FileParser();
    static List<Location_Model> locations = fl.getLoations();
    static List<Record_Model> records = fl.getRecord_models();

    public static void main(String[] args){
        Thread t2 = new Thread(){
            @Override
            public void run() {
                db_proceses();
            }
        };
        t2.start();

        launch(args);

    }

    public static void db_proceses(){
        boolean state = FileDownload.download("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
                "./src/main/resources/CSV-Files");
        System.out.println("State of downloading the csv file: " + state);


        System.out.println("____________");


        System.out.println("Size of locations List: " + locations.size());
        System.out.println("____________");
        System.out.println("Days: " + FileParser.getAllDatesBetween("1/22/2020"));
        System.out.println("____________");
        System.out.println("Size of records List: " + records.size());
        System.out.println("First record: " + records.get(10).toString());



        /*
        PropertiesLoader propertiesLoader = new PropertiesLoader("./src/main/resources/config.properties");
        System.out.println("URL: " + propertiesLoader.getUrl());
        System.out.println("USER: " + propertiesLoader.getUser());
        System.out.println("Pass: " + propertiesLoader.getPass());
        DBConnection dbConnection = new DBConnection(propertiesLoader.getUrl(), propertiesLoader.getUser(), propertiesLoader.getPass());
        System.out.println("Conn: " + dbConnection.getConnectio());


        CRUD_Locations crud_locations = new CRUD_Locations(dbConnection.getConnectio());
        System.out.println("_______________________________");
        System.out.println("The state of Statement(Location): " + crud_locations.insertAll(locations));
        List<Location_Model> selectedListLocs = crud_locations.selectAll();
        System.out.println("Size of selected List(Location): " + selectedListLocs.size());
        crud_locations.closeConn();

        CRUD_Records crud_records = new CRUD_Records(dbConnection.getConnectio());
        System.out.println("_______________________________");
        System.out.println("The state of Statement(Records): " + crud_records.insertAll(records));
        List<Record_Model> selectedListRecs = crud_records.selectAll();
        System.out.println("Size of selected List(Records): " + selectedListRecs.size());
        crud_records.closeConn();
         */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Corona Statistics");

        VBox vBox = new VBox();
        ChoiceBox<String> cb = new ChoiceBox<>();

        for (Location_Model l: locations) {
            cb.getItems().addAll(
                    l.getCountryName() + " " + l.getProvinceName()
            );
        }
        cb.setValue("Austria ");

        Button next = new Button("Next");
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ChartScene cs = new ChartScene(locations, records, cb.getValue());
                primaryStage.setScene(cs.diaScreen());
            }
        });
        vBox.getChildren().addAll(cb, next);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
