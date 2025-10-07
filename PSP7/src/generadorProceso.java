import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class generadorProceso {

	public void ejecutar(String ruta,String[] args) {
		
		List <String> nombreArgumentos = new ArrayList<>();
		
		nombreArgumentos.add(ruta);
				
		
		for(int i=0;i<args.length;i++) {
			nombreArgumentos.add(args[i]);
		}
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(nombreArgumentos);

		try{
			pb.start();
			
			
		}catch(IOException e){
			e.printStackTrace();
	}	
}
}