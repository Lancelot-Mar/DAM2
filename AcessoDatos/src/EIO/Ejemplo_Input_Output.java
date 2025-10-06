package EIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejemplo_Input_Output {
	
	
	public static void Escritura(ArrayList <Persona> listaPersona) {
		
		String fichero = "personas.dat";
		File ficheroEscritura = new File(fichero);
		
		if(!ficheroEscritura.exists()) {
			try {
				ficheroEscritura.createNewFile();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}else {
			if(ficheroEscritura.isFile()) {
				
				//Aqui procedemos a escribir, abriendo el archivo a escritura 
				
				try {
					
					FileOutputStream fos = new FileOutputStream(ficheroEscritura);	
				
					//Transformamos el fichero en un objeto en bytes
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					oos.writeObject(listaPersona);
				
					oos.close();
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}else {
				System.out.println("Fichero es directorio");
			}
		}	
	}
	
	
	public static void Lectura() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"));
			ArrayList <Persona> personas = (ArrayList <Persona>) ois.readObject();
			
			for(Persona p:personas) {
				System.out.println(p);
			}
			
			ois.close();
			
		}catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void EscrituraDatos() {
		
		File ficheroDatos = new File("datos.bin");
		
		try {
			if(!ficheroDatos.exists()) {
				ficheroDatos.createNewFile();
			}
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroDatos)); 
			
			dos.writeInt(2);
			dos.writeDouble(1.2);
			dos.writeUTF("Test String 1234567.43");
			
			dos.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void LecturaDatos() {
				
		try {
	
			DataInputStream dis = new DataInputStream(new FileInputStream("datos.bin"));
			
			int entero = dis.readInt();
			double numero = dis.readDouble();
			String frase = dis.readUTF();
			
			System.out.println("Entero: "+entero+", Double: "+numero+", StirngUTF: "+frase);
			
			dis.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
	
		ArrayList <Persona> listaPersona = new ArrayList<>();
		
		listaPersona.add(new Persona("Luis",22));
		listaPersona.add(new Persona("Juan",20));
		listaPersona.add(new Persona("Larua",32));
		
		Escritura(listaPersona);
		
		Lectura();
		
		EscrituraDatos();
		
		LecturaDatos();
	}
}
