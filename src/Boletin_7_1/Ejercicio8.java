package Boletin_7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio8 {
    public static void main(String[] args) {
        Path path = Paths.get(".\\src\\Boletin_7_1\\Prueba1.txt");

        Pattern p = Pattern.compile("((?:\\p{L}{2,}\\s){3,})(([1-9][0-9]?)|0[1-9])");

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            if (reader.lines().map(p::matcher).allMatch(Matcher::matches)) {
                System.out.println("El fichero es valido.");
            } else {
                System.out.println("El fichero no es valido.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
