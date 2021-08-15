/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlpersistencia;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel Moro
 */
public class Persistencia {

    public static void crearXML() {

        try {

            // Se crea la instancia y estructura necesaria para el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, "concesionario", null);
            documento.setXmlVersion("1.0");

            Element coches = documento.createElement("coches");

            // AÑADIMOS UN PRIMER COCHE
            Element coche = documento.createElement("coche");

            Element matricula = documento.createElement("matricula");
            Text textMatricula = documento.createTextNode("1112345");
            matricula.appendChild(textMatricula);
            coche.appendChild(matricula);

            Element marca = documento.createElement("marca");
            Text textMarca = documento.createTextNode("AUDI");
            marca.appendChild(textMarca);
            coche.appendChild(marca);

            Element precio = documento.createElement("precio");
            Text textPrecio = documento.createTextNode("30001");
            precio.appendChild(textPrecio);
            coche.appendChild(precio);

            coches.appendChild(coche);

            // lo insertamos
            documento.getDocumentElement().appendChild(coches);

            /*
            // AÑADIMOS UN SEGUNDO COCHE
            textMatricula = documento.createTextNode("22222222");
            matricula.appendChild(textMatricula);
            coche.appendChild(matricula);

            textMarca = documento.createTextNode("SEAT");
            marca.appendChild(textMarca);
            coche.appendChild(marca);

            textPrecio = documento.createTextNode("14000");
            precio.appendChild(textPrecio);
            coche.appendChild(precio);

            coches.appendChild(coche);
            
            // lo insertamos
            documento.getDocumentElement().appendChild(coches);
             */
            // creamos el fichero
            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("fichero.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void leerXML() {

        try {
            // Se crea la instancia y estructura necesaria para el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File("fichero.xml"));

            NodeList listaCoches = documento.getElementsByTagName("coche");

            for (int i = 0; i < listaCoches.getLength(); i++) {
                Node nodo = listaCoches.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    NodeList hijos = e.getChildNodes();
                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node hijo = hijos.item(j);
                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("Propiedad: " + hijo.getNodeName() + ", Valor: " + hijo.getTextContent());
                        }
                    }
                    System.out.println("");
                }
            }
        } catch (SAXException | IOException  | ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
            }
        }
    }
