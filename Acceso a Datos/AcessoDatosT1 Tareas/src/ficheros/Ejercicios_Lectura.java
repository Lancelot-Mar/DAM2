package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicios_Lectura {

	public static void ej1() {
        File fichero = new File("FicheroEjemplo.txt");

        try {
            FileReader lector = new FileReader(fichero);
            int caracter;

            while ((caracter = lector.read()) != -1) {
                if ((char)caracter != ' ') {
                    System.out.print((char)caracter);
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void ej2() {
		
		File fichero = new File("FicheroEjemplo.txt");

		try { 
			BufferedReader buffer = new BufferedReader(new FileReader(fichero));
			String linea; 
			int contador = 0;
			int contadorVocales = 0;
				
			while((linea=buffer.readLine())!=null) { 
				for(int i=0;i<linea.length();i++) {
					if(linea.charAt(i)=='a' || linea.charAt(i)=='e' || linea.charAt(i)=='i' || linea.charAt(i)=='o' || linea.charAt(i)=='u') {
						contadorVocales++;
					}
				}
				for(int i=0;i<linea.length();i++) {
					if(linea.charAt(i)!=' ') {
						contador++;
				}
				}
			}
			
			System.out.println("Letras totales: "+(contador-1));
			System.out.println("Vocale: "+contadorVocales);
			
			buffer.close();
			
		}catch(IOException e) {
            System.out.println(e.getMessage());
		}
		
	}
	
	public static void ej2Lectura() {
		
		String text = "Texto de ejemplo test test";
		Pattern patron = Pattern.compile("[aeiouAEIOU]");
		
		Matcher match = patron.matcher(text);
		
		while(match.find()) {
			System.out.println(match.group()); 
		}
	}
	
	public static void ej3() {
        File fichero = new File("Restaurants.csv");

        try {
			FileReader lector = new FileReader(fichero); 

			int caracter;

			

            lector.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void ej4() {
		
		ArrayList <String> palabra = new ArrayList<>();
		ArrayList <Integer> veces = new ArrayList<>();
		
        try(BufferedReader lector = new BufferedReader(new FileReader("C:\\Users\\DAM\\Downloads\\frutas.txt"))) {

        	String linea;
        	
            while ((linea=lector.readLine())!=null) {
            	String fruta = linea.trim().toLowerCase();
            	
            	int indice = palabra.indexOf(fruta);
            	
            	if(indice== -1) {
            		palabra.add(fruta);
            		veces.add(1);
            	}else {
            		int cantidad=veces.get(indice)+1;
            		veces.set(indice, cantidad);
            	}
            	
            }

            lector.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void main(String[] args) {


		ej2();
		System.out.println();
		ej2Lectura();
		
	}

}
