package Utils.utilidades;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class LeerArchivosGherkin {
    public static void main(String[] args) {
        String rutaArchivos = System.getProperty("user.dir")+ File.separator+ "/src/test/resources/cucumber/example";
        File directorio = new File(rutaArchivos);
        File[] archivosGherkin = (File[]) FileUtils.listFiles(directorio, new String[] { "feature" }, true).toArray(new File[0]);

        try {
            String rutaSalida = System.getProperty("user.dir")+ File.separator+ "ConsolidadoGherkins.feature";
            File archivoSalida = new File(rutaSalida);
            FileWriter escritor = new FileWriter(archivoSalida);
            for (File archivo : archivosGherkin) {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea = lector.readLine();
                while (linea != null) {
                    if (linea.contains("@TestCaseKey")) {
                        escritor.write(linea + "\n");
                    }
                    linea = lector.readLine();
                }
                lector.close();
            }
            escritor.close();
            System.out.println("Se ha creado el archivo ConsolidadoGherkins.feature con las líneas que contienen '@'.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
