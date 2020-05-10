package DB;

import Models.Location_Model;

import javax.print.attribute.standard.PresentationDirection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD_Locations {
    private Connection dbConn;

    public CRUD_Locations(Connection conn) {
        this.dbConn = conn;
    }

    public Location_Model select(String country, String province) {
        try {
            PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Locations WHERE Country=? AND Province=?");
            ps.setString(1, country);
            ps.setString(2, province);
            ResultSet rs = ps.executeQuery();
            return new Location_Model(rs.getString("Country"), rs.getString("Province"), rs.getString("Lat"),
                    rs.getString("Longtitude"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Location_Model> selectAll() {
        List<Location_Model> locations = new ArrayList<>();
        try {
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM Locations;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String lat = rs.getString("Lat");
                String longtitude = rs.getString("Longtitude");
                String country = rs.getString("Country");
                String province = rs.getString("Province");
                locations.add(new Location_Model(country, province, lat, longtitude));
            }
            return locations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertAll(List<Location_Model> locations) {
        try {
            boolean stateOfDelete = deleteAll();
            if (stateOfDelete) {
                for (Location_Model l : locations) {
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
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(Location_Model location) {
        try {
            PreparedStatement prepStmt = null;
            String query = "INSERT INTO Locations (Lat, Longtitude, Country, Province)  " +
                    "VALUES(?,?,?,?);";
            prepStmt = dbConn.prepareStatement(query);
            prepStmt.setString(1, location.getLat());
            prepStmt.setString(2, location.getLon());
            prepStmt.setString(3, location.getCountryName());
            prepStmt.setString(4, location.getProvinceName());
            prepStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            String query = "DELETE FROM Records; DELETE FROM Locations;";
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Location_Model lc) {
        try {
            PreparedStatement ps = dbConn.prepareStatement("DELETE FROM Locations WHERE Country=? AND PROVINCE=?;");
            ps.setString(1, lc.getCountryName());
            ps.setString(2, lc.getProvinceName());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(Location_Model lc, String country, String province, String lat, String longtitude) {
        try {
            PreparedStatement ps = dbConn.prepareStatement("UPDATE Locations SET Country=?, Province=?, Lat=?, Longtitude=?" +
                    " WHERE Country=? AND Province=?");
            ps.setString(1, country);
            ps.setString(2, province);
            ps.setString(3, lat);
            ps.setString(4, longtitude);
            ps.setString(5, lc.getCountryName());
            ps.setString(6, lc.getProvinceName());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean closeConn() {
        try {
            dbConn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
