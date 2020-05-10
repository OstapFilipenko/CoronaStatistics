import DB.CRUD_Operations;
import DB.DBConnection;
import Models.Location_Model;

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
        System.out.println("Size of locations List: " + fl.getLoations().size());
        System.out.println("____________");
        //System.out.println("Days: " + FileParser.getAllDatesBetween("1/22/2020"));
        //System.out.println("____________");
        //System.out.println("Size of records List: " + fl.getRecord_models().size());
        //System.out.println("_____________________");
        //System.out.println("first Location: " + fl.getLoations().get(1));

        PropertiesLoader propertiesLoader = new PropertiesLoader("./src/main/resources/config.properties");
        System.out.println("URL: " + propertiesLoader.getUrl());
        System.out.println("USER: " + propertiesLoader.getUser());
        System.out.println("Pass: " + propertiesLoader.getPass());
        DBConnection dbConnection = new DBConnection(propertiesLoader.getUrl(), propertiesLoader.getUser(), propertiesLoader.getPass());
        System.out.println("Conn: " + dbConnection.getConnectio());


        CRUD_Operations crud_operations = new CRUD_Operations(dbConnection.getConnectio());
        System.out.println("_______________________________");
        System.out.println("The state of Statement: " + crud_operations.insert(fl.getLoations()));
        List<Location_Model> selectedList = crud_operations.selectLocations();
        System.out.println("Size of selected List: " + selectedList.size());
        crud_operations.closeConn();

    }
}
