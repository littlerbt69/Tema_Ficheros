package Boletin_7_1;

import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ejercicio5 {
    public static void main(String[] args) throws IOException {
        int opcion;
        do {
            menu();
            opcion = MiEntradaSalida.leerEnteroDeRango("Elije una opcion", 1, 5);
            switch (opcion) {
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    crearFichero();
                    break;
                case 3:
                    borrarFichero();
                    break;
                case 4:
                    mostrarFicheros3();
                    break;
                case 5:
                    break;
            }
        } while (opcion != 5);
    }

    public static void menu() {
        System.out.println("1. Crear un directorio");
        System.out.println("2. Crear un fichero de texto");
        System.out.println("3. Borrar fichero");
        System.out.println("4. Mostrar ficheros de una carpeta");
        System.out.println("5. Salir");
    }

    public static void crearDirectorio() {
        String nombre = MiEntradaSalida.solicitarCadenaMinus("Elija el nombre del directorio");
        File directorio = new File(".\\src\\Boletin_7_1\\" + nombre);

        if (directorio.exists()) {
            System.out.println("El directorio ya existe");
        } else {
            if (directorio.mkdir()) {
                System.out.println("El directorio se ha creado correctamente");
            } else {
                System.out.println("El directorio no ha podido ser creado");
            }
        }
    }

    public static void crearFichero() {
        String nombre = MiEntradaSalida.solicitarCadenaMinus("Elija el nombre del fichero");
        boolean append = true;
        File ficheroTexto = new File(".\\src\\Boletin_7_1\\" + nombre);

        if (ficheroTexto.exists()) {
            if (ficheroTexto.isFile()) {
                append = (MiEntradaSalida.leerCaracterSN("Quiere annadir el contenido al final del fichero?") == 'S');
            } else {
                System.out.println("El fichero no ha podido crearse, ya existe un directorio con ese nombre");
                return;
            }
        }
        String cadena = MiEntradaSalida.solicitarCadenaMinus("Escriba la cadena que estara en el fichero");
        try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroTexto, append))) {
            pw.println(cadena);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarFichero() {
        String nombre = MiEntradaSalida.solicitarCadenaMinus("Elija el nombre del fichero");
        File ficheroABorrar = new File(".\\src\\Boletin_7_1\\" + nombre);
        if (!ficheroABorrar.exists()) {
            System.out.println("El nombre del fichero no existe");
        } else {
            if (ficheroABorrar.delete()) {
                System.out.println("Se ha borrado el fichero " + nombre + " correctamente");
            } else {
                System.out.println("El fichero no ha podido ser borrado");
            }
        }
    }

    public static void mostrarFicheros() {
        String nombre = MiEntradaSalida.solicitarCadenaMinus("Elija el nombre del directorio");
        File directorio = new File(".\\src\\Boletin_7_1\\" + nombre);

        if (directorio.exists()) {
            if (directorio.isDirectory()) {
                File[] fichero = directorio.listFiles();
                if (fichero.length > 0) {
                    for (File fich : fichero) {
                        System.out.println("Nombre fichero: " + fich.getName());
                        long tamBytes = fich.length();
                        double tamKb = (double) tamBytes / 1024;
                        System.out.println("peso en KB: " + tamKb);
                    }
                } else {
                    System.out.println("El directorio esta vacio");
                }
            }
        } else {
            System.out.println("El nombre del directorio no existe");
        }
    }

    public static void mostrarFicheros2() {
        String nombre = MiEntradaSalida.solicitarCadenaMinus("Elija el nombre del directorio");
        File directorio = new File(".\\src\\Boletin_7_1\\" + nombre);

        if (directorio.exists()) {
            if (directorio.isDirectory()) {
                File[] ficheros = directorio.listFiles(File::isFile);
                if (ficheros.length > 0) {
                    for (File fich : ficheros) {
                        System.out.println("Nombre fichero: " + fich.getName());
                        long tamBytes = fich.length();
                        double tamKb = (double) tamBytes / 1024;
                        System.out.println("peso en KB: " + tamKb);
                    }
                } else {
                    System.out.println("El directorio esta vacio");
                }
            }
        } else {
            System.out.println("El nombre del directorio no existe");
        }
    }

    public static void mostrarFicheros3() throws IOException {
        String nombre = MiEntradaSalida.solicitarCadenaMinus("Elija el nombre del directorio");
        Path path = Paths.get(".\\src\\Boletin_7_1\\" + nombre);

        if (Files.exists(path) && Files.isDirectory(path)) {
            try (Stream<Path> f = Files.list(path)) {
                f.filter(Files::isRegularFile).forEach(System.out::println);
            }
        } else {
            System.out.println("El nombre del directorio no existe");
        }
    }
}
