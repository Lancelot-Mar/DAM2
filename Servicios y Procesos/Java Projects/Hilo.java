package Hilos;
import java.util.Random;

public class Hilo extends Thread {

	@Override //Anotacion
	public void run() {
		
		//El hilo oncocoalmente duerme una cantidad aleatoria de milisegundos
		
		Random r = new Random();
		long mseconds = r.nextLong(1000);
		
		try {
			Thread.sleep(mseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		//Imprimimos valor variable de recurso compartido contador
		
		synchronized(main.contador) {
			System.out.println("Hilo: " + this.getName());
			main.contador--;
			System.out.println("Contador: "+main.contador);
		}
		
	}

}
