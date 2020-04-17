package Models;

public class Record_Model {
    private int rid;
    private String dateAsString;
    private String foreignKey;

    public Record_Model(int rid, String dateAsString, String foreignKey) {
        this.rid = rid;
        this.dateAsString = dateAsString;
        this.foreignKey = foreignKey;
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
}
