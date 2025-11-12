import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class generadorProceso {

	public void ejecutar(String rutaDirectorio,String nombreEjecutable) {
		
		List <String> nombreArgumentos = new ArrayList<>();

		File directorio = new File(rutaDirectorio);
		
		ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);
		
		// command = nombre del ejecutable
		pb.command(nombreEjecutable);
		// directorio = ponemos la ruta como objeto de la clase file
		pb.directory(directorio);
		
		try {
			pb.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
