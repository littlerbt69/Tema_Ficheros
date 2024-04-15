package Boletin_7_1;

import java.io.File;
import java.util.Scanner;

public class Ejercicio6 {
    Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        File directorio = new File("/Users/Alumno/Desktop");

        if (directorio.exists() && directorio.isDirectory()) {
            listarArchivosDirectorio(directorio);
        } else {
            System.out.println("El directorio no existe o no es valido.");
        }

    }

    public static void listarArchivosDirectorio(File directorio) {
        File[] archivos = directorio.listFiles();

        if (archivos == null || archivos.length == 0) {
            System.out.println("El directorio esta vacio.");
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                System.out.println(archivo.getName() + " -> (Directorio)");
            } else {
                System.out.println(archivo.getName() + " - " + archivo.length() / 1024 + " KB");
            }
        }
    }
}
