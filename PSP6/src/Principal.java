
public class Principal {

	public static void main(String[] args) {

		String ruta = "notepad";

		generadorProceso lanzador = new generadorProceso();
		lanzador.ejecutarYdestruir(ruta);
		
		System.out.println("Proceso Ejecutado");
		
	}

}
