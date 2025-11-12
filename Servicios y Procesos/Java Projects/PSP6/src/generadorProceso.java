import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class generadorProceso {

	public void ejecutarYdestruir(String ruta ) {
			
		List <String> nombreArgumentos = new ArrayList<>();
		if(ruta==null || ruta.isEmpty()){
			System.out.println("Falta el Nombre del Comando");
			System.exit(1);
		}
		
		nombreArgumentos.add(ruta);
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(nombreArgumentos);
		
		//Hace que el proceso herede del a E/S standart del proceso padre
		//Asi podemos ver el resultado del comando
		pb.inheritIO();
			
		
		Process proceso = null;
		
		try{
			proceso = pb.start();
			System.out.println("Proceso Lanzado");
			//Proceso padre espera a la finalizacion del proceso
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try {
			proceso.waitFor();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		try {
			System.out.println(proceso.exitValue());
		}catch(IllegalThreadStateException e) {
			System.out.println(e);
		}
		
		if(proceso != null) {
			proceso.destroy();
			//mensaje de destruir proceso hijo
		}
	}
	}