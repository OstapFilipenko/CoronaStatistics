package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record_Model {
    private Date date;
    private int ill;
    private String country;
    private String province;

    final SimpleDateFormat format = new SimpleDateFormat("M/dd/YY");


    public Record_Model(String dateAsString, int ill, String country, String province) throws ParseException {
        this.date = format.parse(dateAsString);
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

    public Date getDateAsString() {
        return date;
    }

    public void setDateAsString(String dateAsString) throws ParseException {
        this.date = format.parse(dateAsString);
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
        return "Country: " + this.country + "\nDate: " + this.date.toString() + "\nIll People: " + this.ill + "\nProvince: " + this.province;
    }
}
