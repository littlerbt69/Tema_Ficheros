package Boletin_7_3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse("./src/Boletin_7_3/web1.html");

            Element raiz = documento.getDocumentElement();

            Element titulo = (Element) raiz.getElementsByTagName("title").item(0);
            System.out.println("Titulo de la pagina: " + titulo.getTextContent());

            NodeList div = documento.getElementsByTagName("div");
            System.out.println("La cantidad de divisores que hay en la pagina son: " + div.getLength());

            NodeList divValores =  documento.getElementsByTagName("div");
            int contadorDivValores = 0;

            for (int i = 0; i < divValores.getLength(); i++) {
                Element element = (Element) divValores.item(i);

                if (!element.getAttribute("id").isBlank()) {
                    contadorDivValores++;
                }
            }

            System.out.println("El numero de div con contenido es de: " + contadorDivValores);

            NodeList valoresImagenes =  documento.getElementsByTagName("img");

            for (int i = 0; i < valoresImagenes.getLength(); i++) {
                Element element = (Element) valoresImagenes.item(i);

                if (!element.getAttribute("alt").isBlank()) {
                    System.out.println("El texto es el siguiente: " + element.getAttribute("alt"));
                }
            }

            NodeList todosDivisores =  documento.getElementsByTagName("div");

            for (int i = 0; i < todosDivisores.getLength(); i++) {
                Element element = (Element) todosDivisores.item(i);

                if (!element.getAttribute("class").equals("noticia")) {
                    String titular = element.getElementsByTagName("h2").item(0).getTextContent();
                    System.out.println("Estos son los titulares de la noticia: " + titular);
                    String textoAlt = ((Element) element.getElementsByTagName("img").item(0)).getAttribute("alt");
                    System.out.println("Este es el texto alternativo de la noticia: " + textoAlt);
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Error de lectura o archivo no encontrado.");
        }
    }
}
