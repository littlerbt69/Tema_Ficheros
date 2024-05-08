package Examen_2021;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

public class Aeropuerto {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Path registro = Paths.get("./src/Examen_2021/Registros");

        try (BufferedWriter writer = Files.newBufferedWriter(registro, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)) {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/Examen_2021/aeropuerto.xml");
            Element raiz = doc.getDocumentElement();

            NodeList vuelo = doc.getElementsByTagName("vuelo");

            for (int i = 0; i < vuelo.getLength(); i++) {
                Element vuelos = (Element) vuelo.item(i);
                String id = vuelos.getElementsByTagName("id").item(0).getTextContent();
                String codigo = vuelos.getElementsByTagName("codigo").item(0).getTextContent();
                String compañia = vuelos.getElementsByTagName("compan       ia").item(0).getTextContent();
                String salida = vuelos.getElementsByTagName("hora_salida").item(0).getTextContent();
                String destino = vuelos.getElementsByTagName("destino").item(0).getTextContent();

                String linea = id + ":" + codigo + ":" + compañia + ":" + salida + ":" + destino;

                writer.write(linea);
                writer.newLine();

            }

            Pattern p = Pattern.compile("");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
