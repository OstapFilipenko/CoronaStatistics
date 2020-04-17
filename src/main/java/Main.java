public class Main {
    public static void main(String[] args){
        boolean state = FileDownload.download("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
                "./src/main/resources/CSV-Files");
        System.out.println("State of downloading the csv file: " + state);

        System.out.println("____________");
        FileParser fl = new FileParser();
        System.out.println("Size of locations List: " + fl.getLoations().size());
    }
}
