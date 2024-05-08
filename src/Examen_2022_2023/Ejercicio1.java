package Examen_2022_2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ejercicio1 {
    public static void main(String[] args) {

        String directorioOrigen = MiEntradaSalida.solicitarCadena("Introduzca la carpeta origen");
        Path origen = Paths.get(directorioOrigen);
        String directorioDestino = MiEntradaSalida.solicitarCadena("Introduzca la carpeta destino");
        Path destino = Paths.get(directorioDestino);

        if (!Files.isDirectory(origen)) {
            System.out.println("La ruta origen no es un directorio válido");
            return;
        } else {
            if (!Files.isReadable(origen)) {
                System.out.println("El directorio origen no tiene permiso de lectura");
                return;
            }

        }
        if (!Files.isDirectory(destino)) {
            System.out.println("La ruta destino no es un directorio válido");
            return;
        } else {
            if (!Files.isWritable(destino)) {
                System.out.println("El directorio destino no tiene permiso de escritura");
            }
        }
        try (Stream<Path> st = Files.list(origen)) {
            st.filter(path -> {
                try {
                    //Si el archivo tiene mas de 1024 bytes, es fichero regular, termina por .txt y empieza por copiar
                    if (Files.size(path) > 1024 && Files.isRegularFile(path) && path.getFileName().endsWith(".txt")) {
                        try (BufferedReader br = Files.newBufferedReader(path)) {
                            //Si el archivo empieza por copiar devuelve true
                            if (br.readLine().toLowerCase().startsWith("copiar")) {
                                return true;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el fichero");
                }
                return false;
                //Por cada arhcivo que cumpla la condicion se copia a otro archivo nuevo
            }).forEach(path -> {
                try {
                    Files.copy(path, destino.resolve(path.getFileName().toString()));
                } catch (IOException e) {
                    System.out.println("Error al copiar el fichero: " + path.getFileName());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
