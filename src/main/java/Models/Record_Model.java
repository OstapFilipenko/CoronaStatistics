package Models;

public class Record_Model {
    private int rid;
    private String dateAsString;
    private int ill;
    private String foreignKey;

    public Record_Model(int rid, String dateAsString, String countryRegion, int ill, String provinceState) {
        this.rid = rid;
        this.dateAsString = dateAsString;
        this.ill = ill;
        this.foreignKey = countryRegion+provinceState;
    }

    public int getIll() {
        return ill;
    }

    public void setIll(int ill) {
        this.ill = ill;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getDateAsString() {
        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }


    @Override
    public String toString() {
        return "ID: " + this.rid + "\nForeignKey: " + this.foreignKey + "\nDate: " + this.dateAsString + "\nIll People: " + this.ill;
    }
}
