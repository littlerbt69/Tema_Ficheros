package Boletin_7_1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ejercicio7 {

    static File directorio = new File("/Users/Alumno/Desktop");

    public static void main(String[] args) {
        int opcion;
        do {
            menu();
            opcion = MiEntradaSalida.leerEnteroDeRango("Elije una opcion", 1, 6);
            switch (opcion) {
                case 1:
                    listarDirectorio(directorio);
                    break;
                case 2:
                    listarArchBuscarFicherosQueComienzePorLetras(directorio);
                    break;
                case 3:
                    listarPorExtension();
                    break;
                case 4:
                    buscarArchivoEnDirectorio(directorio);
                    break;
                case 5:
                    String nombreArchivo = MiEntradaSalida.solicitarCadenaMinus("Escriba el nombre del archivo que quieres buscar");
                    buscarArchivoRecurEnDirectorio(directorio, nombreArchivo);
                    break;
                case 6:
                    System.out.println("Saliendo del programa, gracias");
                    break;
            }
        } while (opcion != 6);
    }

    public static void menu() {
        System.out.println("1. Listar Directorio");
        System.out.println("2. Listar directorio y buscar ficheros que comiencen por una palabra");
        System.out.println("3. Listar archivos con cierta extensión de un directorio");
        System.out.println("4. Buscar un archivo en un directorio");
        System.out.println("5. Buscar recursivamente un archivo en un directorio");
        System.out.println("6. Salir");
    }

    public static void listarDirectorio(File directorio) {

        File[] archivos = directorio.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("El directorio esta vacio");
        } else {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println(archivo.getName() + "directorio");
                } else {
                    System.out.println(archivo.getName() + " - " + archivo.length() / 1024 + " Kb");
                }
            }
        }
    }

    public static void listarArchBuscarFicherosQueComienzePorLetras(File directorio) {

        String cadenaFiltrar = MiEntradaSalida.solicitarCadenaMinus("Escribe las letras por las que empiece tu fichero");
        File[] files = directorio.listFiles(((dir, name) -> name.startsWith(cadenaFiltrar)));
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName() + " [Directorio] ");
            } else {
                System.out.println(file.getName() + "-" + file.length() / 1024 + " Kb");
            }
        }
    }

    public static void listarPorExtension() {

        String cadenaFiltrar = MiEntradaSalida.solicitarCadenaMinus("Escribe la extension para buscar el fichero");
        Path directorioNuevo = Paths.get(".\\src\\Boletin_7_1\\pruebaej5");
        try (Stream<Path> ficheros = Files.list(directorioNuevo)) {
            ficheros.filter(Files::isRegularFile)
                    .filter(a -> a.toString().endsWith("." + cadenaFiltrar))
                    .forEach(a -> {
                        try {
                            System.out.println(a.getFileName().toString() + "-" + Files.size(a) / 1024 + "Kb");

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            System.out.println("Ocurrio un error al cerrar el fichero");
        }
    }

    public static void buscarArchivoEnDirectorio(File directorio) {

        String nombreArchivo = MiEntradaSalida.solicitarCadenaMinus("Escriba el nombre del archivo");
        File[] files = directorio.listFiles(((dir, name) -> name.equals(nombreArchivo)));
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory() && file.exists()) {
                    System.out.println(file.getAbsolutePath() + " " + (file.length() / 1024 + " Kb "));
                }
            }
        }else {
            System.out.println("El archivo esta vacio");
        }
    }

    public static void buscarArchivoRecurEnDirectorio(File directorio, String nombreArchivo) {

        File[] files = directorio.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    buscarArchivoRecurEnDirectorio(file, nombreArchivo);
                } else if (file.getName().equals(nombreArchivo)) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("El directorio esta vacio o no existe");
        }
    }
}
