
public class Principal {

	public static void main(String[] args) {

		String ruta = "dir C:\\\\Users\\\\DAM\\\\Downloads\\\\*.exe";

		generadorProceso lanzador = new generadorProceso();
		lanzador.ejecutar(ruta);
		
	}

}
