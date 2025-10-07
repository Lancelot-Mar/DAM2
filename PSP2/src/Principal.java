
public class Principal {

	public static void main(String[] args) {

		String ruta = "C:\\Windows\\System32";
		String nombre = "cmd.exe";
		
		generadorProceso lanzador = new generadorProceso();
		lanzador.ejecutar(ruta,nombre);
		System.out.println("Proceso Ejecutado");
		
	}

}
