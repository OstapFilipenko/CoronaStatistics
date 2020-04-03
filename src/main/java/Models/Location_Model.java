package Models;

public class Location_Model {
    private String countryName;
    private String provinceName;
    private double lat;
    private double lon;
    private String lid;

    public Location_Model(String countryName, String provinceName, double lat, double lon) {
        this.countryName = countryName;
        this.provinceName = provinceName;
        this.lat = lat;
        this.lon = lon;
        this.lid = this.countryName+this.provinceName;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
