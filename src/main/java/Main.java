import DB.CRUD_Locations;
import DB.CRUD_Records;
import DB.DBConnection;
import Models.Location_Model;
import Models.Record_Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        boolean state = FileDownload.download("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
                "./src/main/resources/CSV-Files");
        System.out.println("State of downloading the csv file: " + state);


        System.out.println("____________");
        FileParser fl = new FileParser();
        List<Location_Model> locations = fl.getLoations();
        List<Record_Model> records = fl.getRecord_models();
        System.out.println("Size of locations List: " + locations.size());
        System.out.println("____________");
        System.out.println("Days: " + FileParser.getAllDatesBetween("1/22/2020"));
        System.out.println("____________");
        System.out.println("Size of records List: " + records.size());
        System.out.println("First record: " + records.get(10).toString());

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
    }
}
