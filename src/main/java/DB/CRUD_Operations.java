package DB;

import Models.Location_Model;

import java.sql.*;
import java.util.ArrayList;
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

    public List<Location_Model> locationToAdd(List<Location_Model> all, ResultSet rs) throws SQLException {
        List<Location_Model> inBoth = new ArrayList<>();
        for (Location_Model l: all){
            while (rs.next()){
                if(l.getCountryName().equals(rs.getString("Country")) && l.getProvinceName().equals(rs.getString("Province"))){
                    inBoth.add(l);
                }
            }
        }

        all.removeAll(inBoth);

        return all;
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

    public boolean insert(List<Location_Model> locations){
        try{
            for (Location_Model l: locations) {
                PreparedStatement prepStmt = null;
                String query = "INSERT INTO Location (LocationID, Lat, Longtitude, Country, Province)  " +
                        "VALUES(?,?,?,?,?);";
                prepStmt = dbConn.prepareStatement(query);
                prepStmt.setInt(1,l.getLocationID());
                prepStmt.setString(2, l.getLat());
                prepStmt.setString(3, l.getLon());
                prepStmt.setString(4, l.getCountryName());
                prepStmt.setString(5, l.getProvinceName());
                prepStmt.executeUpdate();
            }

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
