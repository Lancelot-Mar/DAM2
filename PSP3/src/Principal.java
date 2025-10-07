
public class Principal {

	public static void main(String[] args) {

		String ruta = "C:/Windows/System32/notepad.exe";

		generadorProceso lanzador = new generadorProceso();
		lanzador.ejecutar(ruta);
		
	}

}
