import java.awt.EventQueue;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
*/

public class Main {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					
					VentanaTraductor ventana = new VentanaTraductor();
					ventana.setSize(600,400);
					ventana.setVisible(true);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*

	public static void main(String[] args) throws IOException {

		URL direc = new URL("https://www.spanishdict.com/translate/alfombra");
		
		
		//String palabraTraducida;
		//String html = obtenerHTML(direc);
		
		//palabraTraducida = cortarHTML(html);
		
		//System.out.println(palabraTraducida);
		
		
		String web = "https://www.spanishdict.com/translate/gato";
		
		Document document = Jsoup.connect(web).get();
	
		Element palabra = document.select("div#quickdef1-es a.tCur1iYh").get(0);
		
		String resultado= palabra.html().toUpperCase();
		
		System.out.println(resultado);
		
	}

	private static String cortarHTML(String html) {

		int inicio, fin,fin2;
		
		inicio = html.indexOf("?langFrom=en\" class=\"tCur1iYh\">");//31
		
		
		//Opcion1 buscar desde la cadena inicial
		String trozo = html.substring(inicio);
		fin = trozo.indexOf("</a>");
		
		//Opcion2 buscar en la misma cadena desde el punto anteriormente calculado
		fin2 = html.indexOf("</a>", inicio);
		
		String resultado= html.substring(inicio+31,inicio+fin);
		String resultado2= html.substring(inicio+31,fin2);

		
		return resultado;
	}

	private static String obtenerHTML(URL direc) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(direc.openStream()));

		String inputline, codigo ="";
		
		while((inputline=in.readLine())!=null) {
			codigo+=inputline;
		}
		
		in.close();
		
		return codigo;
	}

	*/
}
