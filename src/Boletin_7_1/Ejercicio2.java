package Boletin_7_1;

import java.io.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        File archivoLeer = new File(".\\src\\Boletin_7_1\\FicheroEj1.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoLeer))){
            String linea;
            String acumulador = "";
            while ((linea = br.readLine()) != null) {
                acumulador += linea;
            }

            System.out.println("El contenido del fichero es el siguiente: " + acumulador);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
