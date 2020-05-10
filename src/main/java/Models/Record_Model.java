package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Record_Model {
    private String date;
    private int ill;
    private String country;
    private String province;


    public Record_Model(String date, int ill, String country, String province){
        this.date = date;
        this.ill = ill;
        this.country = country;
        this.province = province;
    }

    public int getIll() {
        return ill;
    }

    public void setIll(int ill) {
        this.ill = ill;
    }

    public String getDate() {
        return date;
    }

    public void setDateAsString(String date)  {
        this.date = date;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Country: " + this.country + "\nDate: " + this.date + "\nIll People: " + this.ill + "\nProvince: " + this.province;
    }
}
