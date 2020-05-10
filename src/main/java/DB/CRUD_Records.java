package DB;

import Models.Location_Model;
import Models.Record_Model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CRUD_Records {
    private Connection dbConn;

    public CRUD_Records(Connection conn) {
        this.dbConn = conn;
    }

    public boolean deleteAll() {
        try {
            String query = "DELETE FROM Records;";
            PreparedStatement stmt = dbConn.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Record_Model record) {
        try {
            PreparedStatement ps = dbConn.prepareStatement("DELETE FROM Records WHERE DataV=? AND Country=? AND Province=?;");
            ps.setDate(1, stringToDate(record.getDate()));
            ps.setString(2, record.getCountry());
            ps.setString(3, record.getProvince());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertAll(List<Record_Model> records) {
        try {
            boolean stateOfDelete = deleteAll();
            if (stateOfDelete) {
                for (Record_Model rc : records) {
                    String query = "INSERT INTO Records (DateV, Infections, Country, Province)  " +
                            "VALUES(?,?,?,?);";
                    PreparedStatement prepStmt = dbConn.prepareStatement(query);
                    prepStmt.setDate(1, stringToDate(rc.getDate()));
                    prepStmt.setInt(2, rc.getIll());
                    prepStmt.setString(3, rc.getCountry());
                    prepStmt.setString(4, rc.getProvince());
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

    public boolean insert(Record_Model record) {
        try {
            PreparedStatement prepStmt = null;
            String query = "INSERT INTO Records (DateV, Infections, Country, Province)  " +
                    "VALUES(?,?,?,?);";
            prepStmt = dbConn.prepareStatement(query);
            prepStmt.setDate(1, stringToDate(record.getDate()));
            prepStmt.setInt(2, record.getIll());
            prepStmt.setString(3, record.getCountry());
            prepStmt.setString(4, record.getProvince());
            prepStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Record_Model record, String date, int ill, String coutry, String province) {
        try {
            PreparedStatement ps = dbConn.prepareStatement("UPDATE Records SET DateV=?, Infections=?, Country=?, Province=?" +
                    " WHERE DateV=? AND Coutry=? AND Province=?");
            ps.setDate(1, stringToDate(date));
            ps.setInt(2, ill);
            ps.setString(3, coutry);
            ps.setString(4, province);
            ps.setDate(5, stringToDate(record.getDate()));
            ps.setString(6, record.getCountry());
            ps.setString(7, record.getProvince());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Record_Model> selectAll() {
        List<Record_Model> records = new ArrayList<>();
        try {
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM Records;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String date = dateToString(rs.getDate("DateV"));
                int ill = rs.getInt("Infections");
                String country = rs.getString("Country");
                String province = rs.getString("Province");
                records.add(new Record_Model(date, ill, country, province));
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Record_Model select(String date, String country, String province) {
        try {
            PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Records WHERE Country=? AND Province=? AND DateV=?");
            ps.setString(1, country);
            ps.setString(2, province);
            ps.setDate(3, stringToDate(date));
            ResultSet rs = ps.executeQuery();
            return new Record_Model(dateToString(rs.getDate("DateV")), rs.getInt("Infections"), rs.getString("Country"),
                    rs.getString("Province"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Date stringToDate(String date) throws ParseException {
        String[] splited = date.split("/");
        String moth = (splited[0].length()==1) ? "0" + splited[0] : splited[0];
        String day = (splited[1].length()==1)  ? "0" + splited[1] :splited[1];
        String year = "20" + splited[2];
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date1 = sdf1.parse(day + "-" + moth + "-" + year);
        return new java.sql.Date(date1.getTime());
    }

    public String dateToString(Date date){
        String[] splited = date.toString().split("-");
        String year = splited[0].substring(2);
        String month = (splited[1].substring(0,1).equals("0")) ? splited[1].substring(splited[1].length()-1) : splited[1];
        String day = splited[2];
        return month+"/"+day+"/"+year;
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

