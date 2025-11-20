package Semaforo;

import java.util.concurrent.Semaphore;

public class Jugador extends Thread{
	

	public Jugador(String string) {
		// TODO Auto-generated constructor stub
		this.setName(string);
	}
	
	@Override //Anotacion
	public void run() {
		
		while(true) {
			
			try {
				main.SEM1.acquire();
				System.out.println(" ping \n");
				Thread.sleep(500);
				main.SEM0.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				main.SEM0.acquire();
				System.out.println(" pong \n");
				Thread.sleep(500);
				main.SEM1.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
