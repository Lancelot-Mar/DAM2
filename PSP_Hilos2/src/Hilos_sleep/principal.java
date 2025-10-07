package Hilos_sleep;

public class principal {

	public static void main(String[] args) {
		// Creamos los hilos
		Thread h1 = new Thread(new Hilo("H1"));
		Thread h2 = new Thread(new Hilo("H2"));

		// Lnazamos los hilos
		h1.start();
		h2.start();

		try {

			h1.join();
			h2.join();

		} catch (InterruptedException e) {
			System.out.println("Se ha Interumpido el Hilo");
		}

		System.out.printf("Hilo principal terminado \n");
	}

}
