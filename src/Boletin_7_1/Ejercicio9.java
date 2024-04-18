package Boletin_7_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio9 {
    public static void main(String[] args) {
        Path path1 = Paths.get("./src/Boletin_7_1/Matriculas.txt");

        Path path2 = Paths.get("./src/Boletin_7_1/MatriculasCorrectas.txt");

        Pattern p = Pattern.compile("\\p{L}+\\s(\\d{4}-([A-Z&&[^AEIOU]]{3}))");

        try (BufferedWriter writter = Files.newBufferedWriter(path2, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE); Stream<String> lineas = Files.lines(path1);){
            lineas.map(p::matcher).filter(Matcher::matches).forEach(matcher -> {
                try {
                    writter.write(matcher.group(1));
                    writter.newLine();
                } catch (IOException e) {
                    System.out.println("Ha ocurrido un error.");
                }
            });
        } catch (IOException e) {
            System.out.println("El fichero no existe o no se puede abrir.");
        }
    }
}
