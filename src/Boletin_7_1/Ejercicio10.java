package Boletin_7_1;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio10 {
    public static void main(String[] args) {
        Path origen = Paths.get(".\\src\\Boletin_7_1\\Ejercicio10.txt");
        Path destino = Paths.get(".\\src\\Boletin_7_1\\");

        Pattern patron = Pattern.compile("^F\\s(\\p{L}{3,}\\.\\p{L}{3})");

        try (Stream<String> lineas = Files.lines(origen)){
            lineas.map(patron::matcher).filter(Matcher::matches).forEach(matcher -> {
                try {
                    Files.createFile(destino.resolve(matcher.group(1)));
                } catch (FileAlreadyExistsException e) {
                    System.out.println("Ya existe el fichero" + matcher.group(1));
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
