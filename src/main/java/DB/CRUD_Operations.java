package DB;

import Models.Location_Model;

import javax.print.attribute.standard.PresentationDirection;
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


    public Location_Model selectOneLocation(String country, String province){
        try{
            PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Locations WHERE Country=? AND Province=?");
            ps.setString(1, country);
            ps.setString(2, province);
            ResultSet rs = ps.executeQuery();
            return new Location_Model(rs.getString("Country"), rs.getString("Province"), rs.getString("Lat"), rs.getString("Longtitude"));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Location_Model> selectLocations(){
        List<Location_Model> locations = new ArrayList<>();
        try{
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM Locations;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String lat = rs.getString("Lat");
                String longtitude = rs.getString("Longtitude");
                String country = rs.getString("Country");
                String province = rs.getString("Province");
                locations.add(new Location_Model(country, province, lat, longtitude));
            }
            return locations;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(List<Location_Model> locations){
        try{
            boolean stateOfDelete = deleteAllLocations();
            if(stateOfDelete){
                for (Location_Model l: locations) {
                    PreparedStatement prepStmt = null;
                    String query = "INSERT INTO Locations (Lat, Longtitude, Country, Province)  " +
                            "VALUES(?,?,?,?);";
                    prepStmt = dbConn.prepareStatement(query);
                    prepStmt.setString(1, l.getLat());
                    prepStmt.setString(2, l.getLon());
                    prepStmt.setString(3, l.getCountryName());
                    prepStmt.setString(4, l.getProvinceName());
                    prepStmt.executeUpdate();
                }
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllLocations(){
        try{
            String query = "DELETE FROM Locations;";
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLocation(Location_Model lc){
        try{
            PreparedStatement ps = dbConn.prepareStatement("DELETE FROM Locations WHERE Country=? AND PROVINCE=?;");
            ps.setString(1, lc.getCountryName());
            ps.setString(2, lc.getProvinceName());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateLocation(Location_Model lc, String country, String province, String lat, String longtitude){
        try{
            PreparedStatement ps = dbConn.prepareStatement("UPDATE Locations SET Country=?, Province=?, Lat=?, Longtitude=?" +
                    " WHERE Country=? AND Province=?");
            ps.setString(1, country);
            ps.setString(2, province);
            ps.setString(3,lat);
            ps.setString(4,longtitude);
            ps.setString(5,lc.getCountryName());
            ps.setString(6,lc.getProvinceName());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
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
