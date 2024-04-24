package Boletin_7_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio11_2 {
    public static void main(String[] args) {
        Path fichero = Paths.get(".\\src\\Boletin_7_1\\Fichero11.txt");
        String destino = ".\\src\\Ejercicio11";
        Pattern patron = Pattern.compile("(\\p{Lu}{2,})\\s(\\p{Lu}{2,})\\s(\\p{Lu}{2,})\\s([^\\\\/:\"*?<>|\n]{2,})");

        try (Stream<String> lineas = Files.lines(fichero)) {
            lineas.map(patron::matcher)
                    .filter(Matcher::matches)
                    .forEach(matcher -> {
                        String nombreCompleto = matcher.group(2) + matcher.group(3) + matcher.group(1);
                        try {
                            Files.createDirectories(Path.of(destino, matcher.group(4), nombreCompleto));
                        } catch (IOException e) {
                            System.out.println(e);
                        }

                    });

        } catch (IOException e) {
            System.out.println("El fichero no existe o no se ha encontrado.");
        }
    }
}
