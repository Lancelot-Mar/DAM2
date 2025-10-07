import java.util.ArrayList;
import java.util.List;

public class generadorProceso {

	public void ejecutar() {
		
		List <String> nombreArgumentos = new ArrayList<>();
		
		nombreArgumentos.add("C:/Windows/System32/cmd.exe");
		
		ProcessBuilder pb = new ProcessBuilder();
		
		try {
			//Process proceso = pb.start();
			pb.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
