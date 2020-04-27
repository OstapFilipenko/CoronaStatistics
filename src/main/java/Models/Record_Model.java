package Models;

public class Record_Model {
    private String dateAsString;
    private int ill;
    private int locationID;


    public Record_Model(String dateAsString, int ill, int locationID) {
        this.dateAsString = dateAsString;
        this.ill = ill;
        this.locationID = locationID;
    }

    public int getIll() {
        return ill;
    }

    public void setIll(int ill) {
        this.ill = ill;
    }

    public String getDateAsString() {
        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }


    @Override
    public String toString() {
        return "ForeignKey: " + this.locationID + "\nDate: " + this.dateAsString + "\nIll People: " + this.ill;
    }
}
