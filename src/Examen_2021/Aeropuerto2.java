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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aeropuerto2 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Path registro = Paths.get("./src/Examen_2021/Registros");

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/Examen_2021/aeropuerto.xml");
            Element raiz = doc.getDocumentElement();

            NodeList vuelo = doc.getElementsByTagName("vuelo");

            Pattern p = Pattern.compile("(\\d)(\\d{2})(\\d{2})(\\d{3})");

            for (int i = 0; i < vuelo.getLength(); i++) {
                Element vuelos = (Element) vuelo.item(i);
                String id = vuelos.getElementsByTagName("id").item(0).getTextContent();

                Matcher m = p.matcher(id);

                if (m.matches()) {
                    String continente = m.group(1);
                    String pais = m.group(2);
                    String aerolinea = m.group(3);
                    String codigoVuelo = m.group(4);

                    String nuevoId = aerolinea + codigoVuelo + pais + continente;

                    vuelos.getElementsByTagName("id").item(0).setTextContent(nuevoId);

                    vuelos.removeChild(vuelos.getElementsByTagName("compania").item(0));
                }
            }

            File f = new File("./src/Examen_2021/Registros2");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // 3º Establecemos algunas opciones de salida, como por ejemplo, la codificación
            // de salida.

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // 4º Creamos el StreamResult, que intermediará entre el transformador y el
            // archivo de destino.

            StreamResult result = new StreamResult(f);

            // 5º Creamos el DOMSource, que intermediará entre el transformador y el árbol
            // DOM.

            DOMSource source = new DOMSource(doc);

            // 6º Realizamos la transformación.

            transformer.transform(source, result);

        } catch (IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
