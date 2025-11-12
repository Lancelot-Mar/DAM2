import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class generadorProceso {

	public void ejecutar(String ruta,String[] args) {
		
		
		
		List <String> nombreArgumentos = new ArrayList<>();
		if(ruta==null || ruta.isEmpty()){
			System.out.println("Falta el Nombre del Comando");
			System.exit(1);
		}
		
		nombreArgumentos.add(ruta);
				
		
		for(int i=0;i<args.length;i++) {
			
			if(args[i]==null){
				System.out.println("Falta el Parametro del comando");
				System.exit(1);
			}
			
			nombreArgumentos.add(args[i]);
		}
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(nombreArgumentos);
		
		//Hace que el proceso herede del a E/S standart del proceso padre
		//Asi podemos ver el resultado del comando
		pb.inheritIO();
		
		try{
			Process proceso = pb.start();
			int codigoRetorno = proceso.waitFor();
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("El Comando devuelve: "+codigoRetorno);
			System.out.println("-----------------------------------------------------------------------------------------");
			
			if(codigoRetorno==0) {
				 System.out.println("Ejecucion Correcta");
			}else {
				System.out.println("Error en la Ejecucion");
			}
			
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
