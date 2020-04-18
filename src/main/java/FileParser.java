import Models.Location_Model;
import Models.Record_Model;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    // List of all coutries and their locations
    private List<Location_Model> loations = new ArrayList<>();

    // List of all records
    private List<Record_Model> record_models = new ArrayList<>();

    public List<Location_Model> getLoations() {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/CSV-Files/time_series_covid19_confirmed_global.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withHeader("Province/State", "Country/Region", "Lat", "Long")
            .withIgnoreHeaderCase()
            .withTrim());

            for (CSVRecord csvRecord: csvParser) {
                String provinceState = csvRecord.get("Province/State");
                String countryRegion = csvRecord.get("Country/Region");
                String lat = csvRecord.get("Lat");
                String longtitude = csvRecord.get("Long");

                loations.add(new Location_Model(countryRegion, provinceState, lat, longtitude));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.loations;
    }

    public List<Record_Model> getRecord_models() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/CSV-Files/time_series_covid19_confirmed_global.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

            for (CSVRecord csvRecord: csvParser) {
                for (String d: getAllDatesBetween("1/22/2020")) {
                    String date = d;
                    String countryRegion = csvRecord.get("Country/Region");
                    String provinceState = csvRecord.get("Province/State");
                    int ill = Integer.parseInt(csvRecord.get(d));

                    record_models.add(new Record_Model(date, countryRegion, ill, provinceState));
                }
            }
            }catch (Exception e){
            e.printStackTrace();
        }
        return record_models;
    }

    public static List<String> getAllDatesBetween(String startDate) {
        List<String> allDatesString = new ArrayList<>();

        String[] dateSplited = startDate.split("/");

        LocalDate st = LocalDate.of(Integer.parseInt(dateSplited[2]), Integer.parseInt(dateSplited[0]), Integer.parseInt(dateSplited[1]));
        LocalDate end = LocalDate.now().minusDays(1);
        while (!st.isAfter(end)) {
            String dateFalseFormated = st.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            String[] dataFalseSplited = dateFalseFormated.split("\\.");
            String month = dataFalseSplited[1];
            if(month.substring(0,1).equals("0")) {
                month = month.replace("0", "");
            }
            String day = dataFalseSplited[0];
            if(day.substring(0,1).equals("0")){
                day = day.replace("0", "");
            }
            dataFalseSplited[1] = day;
            dataFalseSplited[0] = month;
            String correctDate = String.join("/", dataFalseSplited);
            allDatesString.add(correctDate);
            st = st.plusDays(1);
        }
        return allDatesString;
    }
}
