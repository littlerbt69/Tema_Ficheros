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
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Aeropuerto3 {
    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/Examen_2021/aeropuerto.xml");
            NodeList compañias = doc.getElementsByTagName("compania");
            Document doc2 = db.newDocument();
            doc2.appendChild(doc2.createElement("compañias"));

            Set <String> nombresCompañias = new HashSet<>();

            for (int i = 0; i < compañias.getLength(); i++){
                String compañia = compañias.item(i).getTextContent();
                nombresCompañias.add(compañia);
            }
            nombresCompañias.stream().forEach(compañia -> {
                Element nuevaCompañia = doc2.createElement("compañia");
                nuevaCompañia.setAttribute("nombre",compañia);
                doc2.getDocumentElement().appendChild(nuevaCompañia);
            });

            File f = new File("./src/Examen_2021/compañias.xml");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StreamResult result = new StreamResult(f);
            DOMSource source = new DOMSource(doc2);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
