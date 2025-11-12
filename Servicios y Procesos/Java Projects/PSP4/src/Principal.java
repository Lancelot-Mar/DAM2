
public class Principal {

	public static void main(String[] args) {

		String ruta = "ipconfig";
		String parametros[] = {
				"/all"
		};

		generadorProceso lanzador = new generadorProceso();
		lanzador.ejecutar(ruta,parametros);
		
	}

}
