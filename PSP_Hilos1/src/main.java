
public class main {

	public static void main(String[] args) {

		// Creamos los hilos
		Thread h1 = new Thread(new Hilo("H1"));
		Thread h2 = new Thread(new Hilo("H2"));

		// Lnazamos los hilos
		h1.start();
		h2.start();

		System.out.printf("Hilo principal terminado \n");

	}

}
