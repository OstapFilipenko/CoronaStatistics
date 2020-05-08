package DB;

import Models.Location_Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CRUD_Operations {
/*
    Before Writing into the table, first check which elements should be posted in the table, (dont write object twice into table)
    How to do that:
        Location Table:
            loop through the list<Location> try to select that from table if everything is ok then do nothing, otherwise in the catch block INSERT INTO with values of that object
        Records:
            the same principle like Location, but create select where number = object number, date = object date, country = object country

    !!!!!ATTENTION!!!!!
    First try to Insert Dates with described principle above, then can be read, deleted, adn updated.
 */


    private Connection dbConn;

    public CRUD_Operations(Connection conn){
        this.dbConn = conn;
    }

    public List<Location_Model> locationToAdd(List<Location_Model> all){

        return null;
    }


    public ResultSet selectAll(){
        try{
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM Location;";
            return stmt.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(){
        try{
            PreparedStatement prepStmt = null;
            String query = "INSERT INTO Location (LocationID, Lat, Longtitude, Country, Province)  " +
                    "VALUES(?,?,?,?,?);";
            prepStmt = dbConn.prepareStatement(query);
            prepStmt.setInt(1,4);
            prepStmt.setString(2, "234.32423");
            prepStmt.setString(3, "432.34234");
            prepStmt.setString(4, "Germany");
            prepStmt.setString(5, "");
            prepStmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeConn(){
        try{
            dbConn.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
