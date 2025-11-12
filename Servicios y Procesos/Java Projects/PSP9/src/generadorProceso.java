import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class generadorProceso {

	public static int MAXTEMP = 10;
	public void ejecutar(String ruta,String[] args) {
		
		List <String> nombreArgumentos = new ArrayList<>();
		
		nombreArgumentos.add(ruta);
				
		
		for(int i=0;i<args.length;i++) {
			nombreArgumentos.add(args[i]);
		}
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(nombreArgumentos);

		try{
			
			Process proceso = pb.start();
			
			try {
				proceso.waitFor(MAXTEMP,TimeUnit.SECONDS);
				System.out.println("test");
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}catch(IOException e){
			e.printStackTrace();
	}	
}
}