import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//CREE UNA CLASE PARA INCIAR TODO EL PROCESO, INCLUYENDO EL ANALISIS Y LA DESCARGA DE LOS ARCHIVOS.CSV
public class MainApplication {

    public static void main(String[] args) {
        //PATH URL DE LA PAGINA WEB
        String url = "https://people.sc.fsu.edu/~jburkardt/data/csv/csv.html";

        URL webPage = null;
        try {
            webPage = new URL(url);
        } catch (MalformedURLException ex) {
            System.err.println("EL URL ESTA MAL, PA");
            return;
        }

        //LEER LOS DATOS (HTMLReader)
        BufferedReader lectorhtml = null;
        try {
            lectorhtml = new BufferedReader(
                    new InputStreamReader(webPage.openStream()));
        } catch (IOException ex) {
            System.err.println("ERROR EN LA CONEXIÓN");//DEPURAR
            return;
        }
        //OBJETOS INICIALIZADOS, ANALIZADOR HTML Y MANEJADOR DEL CSV
        HTMLParser p = new HTMLParser();
        HTMLEditorKit.Parser procesamiento = p.getParser();
        ManejadorCSVFiles contadorHTML = new ManejadorCSVFiles();
        try {
            procesamiento.parse(lectorhtml, contadorHTML, true);
        } catch (IOException e) {
            System.err.println("ERROR EN EL DOCUMENTO HTML");//DEPURAR
            return;
        }

        //FORSITO DE LOS ARCHIVOS CSV, HILOS DOWNLOADER
        for (String csvFile : contadorHTML.getCsvFiles()) {
            try {
                URL csvUrl = new URL(webPage, csvFile);
                CSVFileThread csvFileHILO= new CSVFileThread(csvUrl.toString());
                csvFileHILO.start();
            } catch (MalformedURLException e) {
                System.err.println("ERROR" + csvFile); //DEPURAR
            }
        }
    }
}
