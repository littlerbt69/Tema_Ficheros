package Boletin_7_3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
            Document doc = db.parse("./src/Boletin_7_3/web1.html");
            Element raiz = doc.getDocumentElement();
            Element titulo = (Element) raiz.getElementsByTagName("title").item(0);

            System.out.println("El titulo de la pagina se llama : " + titulo.getTextContent()+ "\n");

            System.out.printf("En la pagina hay %d divisores \n",raiz.getElementsByTagName("div").getLength());

            NodeList divValores = doc.getElementsByTagName("div");
            int contDivVal = 0;
            for (int i = 0; i < divValores.getLength(); i++){
                Element elemento = (Element) divValores.item(i);
                if (!elemento.getAttribute("id").isBlank()){
                    contDivVal++;
                }
            }
            System.out.printf("En la página hay %d divisores con valor \n",contDivVal);

            NodeList imgValor = doc.getElementsByTagName("img");
            for (int i = 0; i < imgValor.getLength(); i++) {
                Element elemento = (Element) imgValor.item(i);
                if (!elemento.getAttribute("alt").isBlank()) {
                    System.out.println("Texto de la imagen : " + elemento.getAttribute("alt")+ "\n");
                }
            }
            NodeList todosLosDivisores = doc.getElementsByTagName("div");
            for (int i = 0; i < todosLosDivisores.getLength(); i++) {
                Element elemento = (Element) todosLosDivisores.item(i);
                if (elemento.getAttribute("class").matches("\bnoticia\b")) {
                    String titular = elemento.getElementsByTagName("h2").item(0).getTextContent();
                    System.out.println("Titular: " + titular + "\n");
                    String textoAlter = ((Element) elemento.getElementsByTagName("img").item(0)).getAttribute("alt");
                    System.out.printf("Texto de imagen alternativo : " + textoAlter + "\n");
                }
            }
            NodeList menuPrincipal = doc.getElementsByTagName("div");
            for (int i = 0; i < menuPrincipal.getLength(); i++){
                Element elemento = (Element) menuPrincipal.item(i);
                if (elemento.getAttribute("id").equals("menu-principal")){
                    NodeList acciones = elemento.getElementsByTagName("li");
                    System.out.println("Menu: ");
                    for (int j = 0; j < acciones.getLength();j++){
                        System.out.println(acciones.item(j).getTextContent());
                    }
                }
            }
            NodeList todasNoticias = doc.getElementsByTagName("div");
            for (int i = 0; i < todosLosDivisores.getLength(); i++) {
                Element elemento = (Element) todasNoticias.item(i);
                if (elemento.getAttribute("class").equals("noticia")) {
                    String titular = elemento.getElementsByTagName("h2").item(0).getTextContent();
                    System.out.println("Titular: " + titular);
                    String textoDesc = elemento.getElementsByTagName("p").item(0).getTextContent();
                    System.out.println("Descipcion: " + textoDesc);
                }
            }
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("El archivo no se ha podido leer o no existe.");
        }
    }
}
