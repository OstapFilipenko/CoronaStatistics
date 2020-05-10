package Models;

public class Location_Model {
    private String countryName;
    private String provinceName;
    private String lat;
    private String lon;

    public Location_Model(String countryName, String provinceName, String lat, String lon) {
        this.countryName = countryName;
        this.provinceName = provinceName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


    @Override
    public String toString() {
        return "Country: " + this.countryName + "\nProvince: " + this.provinceName + "\nLat: " + this.lat + "\nLong: " + this.lon;
    }
}
