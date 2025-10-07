
public class Principal {

	public static void main(String[] args) {

		String ruta = "notepad";
		String parametros[] = {
				"C:\\Users\\DAM\\eclipse-workspace\\AcessoDatos\\FicheroEjemplo.txt"
		};

		generadorProceso lanzador = new generadorProceso();
		lanzador.ejecutar(ruta,parametros);
		
	}

}
