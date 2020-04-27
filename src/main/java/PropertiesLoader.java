import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private String url;
    private String user;
    private String pass;

    public PropertiesLoader(String path){
        loader(path);
    }

    public void loader(String path){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
            this.url = prop.getProperty("url");
            this.pass = prop.getProperty("pass");
            this.user = prop.getProperty("user");

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
