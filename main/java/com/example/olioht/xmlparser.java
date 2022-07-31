package com.example.olioht;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class xmlparser {

    public List<String> readXML() {
        List<String> newList = new ArrayList<>();
        // a list of strings that will contains the data we want
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/Schedule/";
            // connecting to the website and getting access to the xml document with movies
            Document doc = builder.parse(url);
            ((Document) doc).getDocumentElement().normalize();
            // Nodelist will include elements inside "show" tag
            NodeList nlist = doc.getDocumentElement().getElementsByTagName("Show");

            for(int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                //System.out.println("Element in this:"+node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // uusiList receives the information corresponding these tags in string form
                    newList.add(element.getElementsByTagName("EventID").item(0).getTextContent());
                    newList.add(element.getElementsByTagName("ID").item(0).getTextContent());
                    newList.add(element.getElementsByTagName("Title").item(0).getTextContent());
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Tiedot ulos");
        }

        return newList;
    }
}
