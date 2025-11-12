package JavaNIO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void LeerFiles() {
		Path ruta = Paths.get("FicheroEjemplo.txt");
		
		try {
			
			//Lectura total del fichero
			
			String cont = Files.readString(ruta);
			System.out.println(cont);
			
			//--------------------------------------------------------------------------------//
			
			//Lectura por lineas del fichero
			
			List <String> ListaLineas = Files.readAllLines(ruta);
			
			for(String num:ListaLineas) {
				System.out.println(num);
			}
			
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	
	public static void EscrituraFiles() {
		Path ruta = Paths.get("FicheroEjemploEscrituraNIO.txt");
		
		try {
			
			String cont = "test primera esritura";
			
			//Se escribe en el fichero transformando el contenido en bites y le aplicamos el estandart de escritura con el charsets
			Files.write(ruta, cont.getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE,StandardOpenOption.WRITE); 

		}catch(IOException e) {
			e.getMessage();
		}	
	}
	
	
	
	public static void CopiarFiles() {
		Path rutaOrig = Paths.get("FicheroEjemploEscrituraNIO.txt");
		Path rutaDest = Paths.get("FicheroEjemploCopiaNIO.txt");

		try {
			
			Files.copy(rutaOrig, rutaDest, StandardCopyOption.REPLACE_EXISTING);
			
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	
	public static void ListarContenidos() {
		Path dir = Path.of(".");
		
		try {
			
			Stream <Path> flujo = Files.list(dir);
			flujo.forEach(archivo -> System.out.println(archivo.getFileName()));
			
			
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	
	public static void PropriedadesFichero() {
		Path ruta = Paths.get("FicheroEjemplo.txt");
		
		System.out.println("Fichero existe: "+Files.exists(ruta));
		System.out.println("Es directorio: "+Files.isDirectory(ruta));
	}
	
	
	
	public static void BorrarFiles() {
		Path ruta = Paths.get("FicheroEjemploEscrituraNIO.txt");
		
		try {
			
			Files.deleteIfExists(ruta);
			
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	public static void main(String[] args) {

		LeerFiles();
		EscrituraFiles();
		CopiarFiles();
		ListarContenidos();
		PropriedadesFichero();
	}
}
