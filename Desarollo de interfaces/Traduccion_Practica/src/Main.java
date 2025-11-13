import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

	public static void main(String[] args) throws IOException {

		URL direc = new URL("https://www.spanishdict.com/translate/alfombra"
				+ "");
		
		String palabraTraducida;
		String html = obtenerHTML(direc);
		
		palabraTraducida = cortarHTML(html);
		
		System.out.println(html);
		
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

}
