import java.io.IOException;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Operaciones {

	public static String traducir(String palabraTraducir) {

		String REGEX_TRADUCIR = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
		
		if(palabraTraducir.matches(REGEX_TRADUCIR)) {
			String web = "https://www.spanishdict.com/translate/" + palabraTraducir;
			
			Document document = null;
			Element palabra = null;
			String resultado = null;
			
			try {
				document = Jsoup.connect(web).get();
				palabra = document.select("div#quickdef1-es a.tCur1iYh").get(0);
				resultado= palabra.html().toUpperCase();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return resultado;											
		}else {
			String resultado = "error";
			return resultado;
		}
		
	}

}
