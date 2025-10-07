import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class generadorProceso {

	public void ejecutar(String ruta) {
		
		
		
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
		
		try{
			Process proceso = pb.start();
			proceso.waitFor();
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("test");
			System.out.println("-----------------------------------------------------------------------------------------");
			
		}catch(IOException e){
			System.out.println("Error durante la ejecucion del commando");
			e.printStackTrace();
			System.exit(2);
			
		}catch(InterruptedException e){
			System.err.println("Proceso Interumpido");
			System.exit(3);
		}
		
	}
	
}
