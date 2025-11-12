package ficheros;
import java.io.*;



public class test {

	public static void main(String[] args) {

		//Ponemos la ruta entre medio de los parentesis con la siguiente sintaxis:
		//File fichero = new File(Ruta del fichero);
		
		File fichero = new File("FicheroEjemplo.txt");
		
		//Verificamos que el fichero exista
		//if(fichero.exists()) {
		
		if(!fichero.exists()) {
			try {				
				fichero.createNewFile();	
			}catch(IOException io){
				io.printStackTrace();
			}
		}
		
		else{
			
			//ESCRITOR
			
			try {				
				
				//Esto nos permite no sobrescribir las lineas del archivo, el true final nos permita añadir el contenido manteniendo el contenido anterior
				BufferedWriter pw = new BufferedWriter(new FileWriter(fichero,true)); 
				
				/*
				FileWriter escritura = new FileWriter(fichero);
				PrintWriter pw = new PrintWriter(escritura);
				*/
				
				for(int i=0; i<11; i++) {
					pw.write("Linea: "+i);//Esta parte permite escribir dentro del archivo
					//pw.newLine; //Sigue la logica del Buffer
				}
				
				pw.close();
				
			}catch(IOException io){
				io.printStackTrace();
			}
				
			//Ejecutamos lo siguiente para ver los datos del archivo
			System.out.println("Nombre Fichero: "+fichero.getName());//Nombre del Archivo
			System.out.println("Ruta Relativa: "+fichero.getPath());//Ruta Relativa
			System.out.println("Ruta Absoluta: "+fichero.getAbsolutePath());//Ruta Absoluta
			System.out.println("Tamaño: "+fichero.length());//Tamaño del fichero
				
			System.out.println();//--------------------------------------------------------------------//
				
			//Vemos los datos de Lectura,Escritura y Ejecucion del Fichero
			System.out.println("Permiso de Lectura: "+fichero.canRead());//Permiso de Lectura
			System.out.println("Permiso de Escritura: "+fichero.canWrite());//Permiso de Escritura
			System.out.println("Permiso de Ejecucion: "+fichero.canExecute());//Permiso de Ejecucion
	
			System.out.println();//--------------------------------------------------------------------//
				
			//LECTOR
				 
			try { //Hay que agregar un try catch porque las hacciones de lectura y escritura crean excepciones
				FileReader lector = new FileReader(fichero); //Creamos el lector para poder leer el fichero
				BufferedReader buffer = new BufferedReader(lector); // lleno el buffer de los caracteres y ¿leo lineas?
				String linea; //Defino las lineas del fichero para que reconozca los saltos de linea de este ultimo
					
				while((linea=buffer.readLine())!=null) { //Condicion que vaya leyendo las lineas hasta que el buffer sea igual a null
					System.out.println(linea);
				}
				
				lector.close();
				
			}catch(IOException e) {
				e.getMessage(); //Imprime el mensaje de error
				
			}finally {
				System.out.println("Adios");
			}
			
		}
		
		/*	
		}else {
			System.out.println("No existe el fichero");
			
			try {
				System.out.println("Se ha creado el fichero");

				//Creamos un nuevo fichero en caso de que no exista
				fichero.createNewFile();
				
			}catch(IOException io){ //Esto permite sacar los errores guardados en la pila
				io.printStackTrace(); // Aquí se imprime el error si el archivo no se encuentra o no se puede leer
			}
		}
		*/
		
	}

}
