package Semaforo;

import java.util.concurrent.Semaphore;

public class main {
	

	final static Semaphore SEM1 = new Semaphore(1);
	final static Semaphore SEM0 = new Semaphore(0);
	
	public static void main(String[] args) {
	
		Jugador jPing = new Jugador("Ping");
		Jugador jPong = new Jugador("Pong");
		
		jPing.start();
		jPong.start();
	}
}

