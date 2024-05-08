package Boletin_7_1;

import java.io.*;

public class Ejercicio3 {
    public static void main(String[] args) {
        //Con Java IO
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(new FileWriter(".\\src\\Boletin_7_1\\salidaEj3.txt",true))){
            String linea;
            while (!(linea = br.readLine()).equalsIgnoreCase("fin")){
                pw.println(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
