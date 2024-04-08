package Boletin_7_1;

import java.io.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        File archivoLeer = new File(".\\src\\Boletin_7_1\\FicheroEj1.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoLeer))){
            int contador = 0;
            while (br.readLine() != null) {
                contador++;
            }

            System.out.println("El fichero seleccionado tiene un total de: " + contador + " lineas.");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
