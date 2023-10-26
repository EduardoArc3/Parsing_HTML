import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;



//IMPLEMENTACIÓN DE DESCARGA PARA LOS ARCHIVOS CSV (HILOS SEPARADOS)
public class CSVFileThread extends Thread {
    private String csvUrl;

    //TOMAMOS EL URL COMO ARGUMENTO EN EL CONSTRUCTOR
    public CSVFileThread(String csvUrl  ) {
        this.csvUrl = csvUrl;
    }
        //METODO RUN QUE INICIA EL THREAD
        //DESCARGAR EL CSV
    public void run() {
        URL url;
        try {
            url = new URL(csvUrl);
        } catch (MalformedURLException e) {
            return;
        }

        //CONTADOR DE LAS LINEAS, INICIANDO EN 0
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line; //variable LINEA
            //LEER LINEA POR LINEA EL ARCHIVO
            while ((line = reader.readLine()) != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("ERROR EN LA LECTURA DEL URL: " + csvUrl + ". COMPLICADO:" + e.getMessage());
            return;
        }

        System.out.println("SE DESCARGO EL ARCHIVO CSV, BUEN TRABAJO " + csvUrl + " - Número de líneas: " + lineCount);
    }
}
