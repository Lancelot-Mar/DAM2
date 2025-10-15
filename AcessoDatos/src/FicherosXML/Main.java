package FicherosXML;

import java.io.File;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main {

	public static void main(String[] args) {

		try {
			File ficheroXML = new File("persona.xml");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
			
			Document doc = docB.parse(ficheroXML);
			
			doc.getDocumentElement().normalize();
			
			NodeList Lista = doc.getElementsByTagName("persona");
			
			int cantidad = Lista.getLength();
			
			for(int i=0;i<cantidad;i++) {
				Node nodo = Lista.item(i);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element persona = (Element) nodo;
					
					String nombre = persona.getElementsByTagName("nombre").item(0).getTextContent();
					String edad = persona.getElementsByTagName("edad").item(0).getTextContent();
					String ciudad = persona.getElementsByTagName("ciudad").item(0).getTextContent();
					
					System.out.printf("La persona es: %s La edad es: %s La ciudad es: %s \n",nombre,edad,ciudad);
				}
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
