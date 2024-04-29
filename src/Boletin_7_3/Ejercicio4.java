package Boletin_7_3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio4 {
    public static void main(String[] args) {
        File fichero = new File("./src/Boletin_7_3/quijote.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String linea;
            String patronRazon = "\\braz[óo]n\\b";
            Pattern patron = Pattern.compile(patronRazon, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

            int contador = 0;

            while ((linea = br.readLine()) != null) {
                Matcher m = patron.matcher(linea);
                while (m.find()) {
                    contador++;
                }
            }

            System.out.println("Se ha encontrado la palabra RAZON: " + contador + " veces.");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
