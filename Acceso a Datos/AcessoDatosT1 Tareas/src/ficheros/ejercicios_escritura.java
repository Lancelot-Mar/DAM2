package ficheros;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class ejercicios_escritura {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		System.out.println("Introduce el nombre del fichero 1");
		String fichero1= sc.nextLine();
		System.out.println("Introduce el nombre del fichero 2");
		String fichero2= sc.nextLine();
		System.out.println("Introduce el nombre de la ruta");
		String ruta= sc.nextLine();
				
		File directorio = new File(ruta);
	
		if(directorio.isDirectory() && directorio.exists()) {
			File fichero1prog=new File(directorio,fichero1);
			File fichero2prog=new File(directorio,fichero2);
			String nombreNuevo=fichero1+" "+fichero2;
			File ficheroNuevo=new File(directorio,nombreNuevo);
			if(fichero1prog.exists() && fichero1prog.isFile() && fichero2prog.exists() && fichero2prog.isFile()) {
				try {
					if(!ficheroNuevo.exists()) {
						ficheroNuevo.createNewFile();
					}
					FileReader lecturaf1 = new FileReader(fichero1prog);
					BufferedReader bufferlectura = new BufferedReader(lecturaf1);
					
					
					
					
				}catch(IOEception e) {
					e.printStackTrace();
				}
			}
		
		}
}
}
