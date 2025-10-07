package Hilos_sleep;
import java.util.Random;

public class Hilo implements Runnable {

	private final String nombre;

	Hilo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		
		System.out.printf("Hola soy un hilo %s \n", nombre);
		
		for(int i=0; i<5; i++) {
			
			Random r = new Random();
			int pausa = 20 + r.nextInt(500-20);
			System.out.printf("Hilo %s hace pausa de %d ms\n",nombre,pausa);
			
			try {
				Thread.sleep(pausa);
			}catch(InterruptedException e) {
				System.out.printf("Hilo interrumpido %s \n", nombre);
			}
		
		}
		
		System.out.printf("Hilo terminado %s \n", nombre);
	
	}
	
}
