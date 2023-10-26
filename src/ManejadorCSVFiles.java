import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.util.ArrayList;
import java.util.List;


//ANALISIS HTML, BUSCAMOS LOS ARCHIVOS CSV
public class ManejadorCSVFiles extends HTMLEditorKit.ParserCallback {
    private List<String> csvFiles;

    public ManejadorCSVFiles() {
        super();
        csvFiles = new ArrayList<>();
    }

    //METODO OVERRIDE PARA MANEJAR EL TEXTO DE LA PAGINA HTML Y ENCONTRAR ARCHIVOS CSV
    @Override
    public void handleText(char[] data, int pos) { //CHAR DATA, POSITION
        String text = new String(data);  //LINEA DE STRING
        if (text.trim().endsWith(".csv")) { //ENCONTRAR TEXTO QUE TERMINE EN .CSV
            //AGREGA LOS CSV AL List<String> csvFiles;
            csvFiles.add(text.trim());
            System.out.println("SE ENCONTRÓ EL CSV: " + text.trim());
        }
    }

    public List<String> getCsvFiles() {
        return csvFiles;
    }
}
