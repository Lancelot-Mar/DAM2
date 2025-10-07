import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class generadorProceso {

	public void ejecutar(String ruta) {
		
		
		List <String> nombreArgumentos = new ArrayList<>();
		
		nombreArgumentos.add("cmd");
		nombreArgumentos.add("start");
		nombreArgumentos.add("/C");
		nombreArgumentos.add(ruta);				
				
		try{
			ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);

			Process proceso = pb.start();

			BufferedReader buffer = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String linea; 
				
			while((linea=buffer.readLine())!=null) {
				System.out.println(linea);
			}
			
		}catch(IOException e){
			e.printStackTrace();	
		}
	}
}
	

