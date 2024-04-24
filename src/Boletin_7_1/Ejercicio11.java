package Boletin_7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio11 {
    public static void main(String[] args) {
        Path fichero = Paths.get(".\\src\\Boletin_7_1\\Fichero11.txt");
        Pattern patron = Pattern.compile("(\\p{Lu}{2,})\\s(\\p{Lu}{2,})\\s(\\p{Lu}{2,})\\s([^\\\\/:\"*?|<>]{2,})");

        try (BufferedReader reader = Files.newBufferedReader(fichero)){
            String linea;
            while ((linea = reader.readLine()) != null) {
                Matcher matcher = patron.matcher(linea);
                if (matcher.matches()) {
                    String nombre = matcher.group(1);
                    String ap1 = matcher.group(2);
                    String ap2 = matcher.group(3);
                    String curso = matcher.group(4);

                    String nombreDirectorioAlumno = ap1 + ap2 + nombre;

                    Path directorioCurso = Paths.get(curso);
                    if (!Files.exists(directorioCurso)) {
                        Files.createDirectory(directorioCurso);
                    }

                    Path directorioAlumno = directorioCurso.resolve(nombreDirectorioAlumno);
                    Files.createDirectory(directorioAlumno);

                    System.out.println("Directorio creado para: " + nombreDirectorioAlumno);
                } else {
                    System.out.println("Error al crear el directorio.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
