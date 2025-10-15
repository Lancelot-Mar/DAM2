package FicherosXML2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main {

	public static void main(String[] args) {

		try {
			File ficheroXML = new File("frutas.xml");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
			
			Document doc = docB.parse(ficheroXML);
			
			doc.getDocumentElement().normalize();
			
			NodeList Lista = doc.getElementsByTagName("fruta");
			
			int cantidad = Lista.getLength();
			
			for(int i=0;i<cantidad;i++) {
				Node nodo = Lista.item(i);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element frutas = (Element) nodo;
					
					String nombre = frutas.getElementsByTagName("nombre").item(0).getTextContent();
					String tipo = frutas.getElementsByTagName("tipo").item(0).getTextContent();
					String color = frutas.getElementsByTagName("color").item(0).getTextContent();
					String origen = frutas.getElementsByTagName("origen").item(0).getTextContent();
					String precio = frutas.getElementsByTagName("precio").item(0).getTextContent();
					String temporada = frutas.getElementsByTagName("temporada").item(0).getTextContent();
					

					NodeList listaNutrientes = doc.getElementsByTagName("nutrientes"); //sacamos la lista de nutrientes
					Node nod2 = listaNutrientes.item(i);
					
					if(nod2.getNodeType() == Node.ELEMENT_NODE) {
						Element nutrientes = (Element) nod2;
										
						int cantidadNutrientes = listaNutrientes.getLength();
						
						List <String> nutrientesArr = new ArrayList<>();
						
						//for para recorrer el apartado de nutrientes del xml y agregarlos  a el array
						for (int j = 0; j < cantidadNutrientes / 4; j++) { 
							String nutriente = nutrientes.getElementsByTagName("nutriente").item(j).getTextContent();
							nutrientesArr.add(nutriente);
						}
						
						System.out.printf("%s, %s, %s, %s, %s, %s, %s  \n",nombre,tipo,color,origen,precio,temporada,nutrientesArr);			
					}					
				}
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
