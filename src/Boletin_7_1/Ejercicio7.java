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
            opcion = MiEntradaSalida.leerEnteroDeRango("Escribe la letra inicial de lo que quieres buscar", 1, 4);
            switch (opcion) {
                case 1:
                    listarPorComienzo();
                    break;
                case 2:
                    listarPorExtension();
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } while (opcion != 5);
    }

    public static void menu() {
        System.out.println("1. Listar por comienzo");
        System.out.println("2. Listar por extension");
        System.out.println("3. Buscar por archivos");
        System.out.println("4. Buscar recursivo");
        System.out.println("5. Salir");
    }

    public static void listarPorComienzo() {
        String palabraFiltrar = MiEntradaSalida.solicitarCadenaMinus("Elige la palabra por la que filtrar el fichero");
        File[] files = directorio.listFiles((dir, name) -> name.startsWith(palabraFiltrar));

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName() + " -> (Directorio)");
            } else {
                System.out.println(file.getName() + " - " + file.length() / 1024 + " KB");
            }
        }
    }

    public static void listarPorExtension() {
        String palabraFiltrar = MiEntradaSalida.solicitarCadenaMinus("Escribe la extension del fichero que deseas buscar");

        Path directorio = Paths.get("/Users/Alumno/Desktop");

        try (Stream<Path> ficheros = Files.list(directorio)){
            ficheros.filter(Files :: isRegularFile);
            ficheros.filter(a -> directorio.endsWith("." + palabraFiltrar)).forEach(a -> {
                try {
                    System.out.println(a.getFileName().toString() + " - " + Files.size(a) / 1024 + " KB");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            System.out.println("Ocurrio un error al acceder el fichero");
        }
    }
}
