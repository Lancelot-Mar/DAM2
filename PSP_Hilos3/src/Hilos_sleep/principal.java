package Hilos_sleep;

import java.util.ArrayList;

public class principal {

	private static final int NUM_HILOS = 10;
	private static final int CUENTA_TOTAL = 100000;
	
	public static void main(String[] args) {
				
		Contador contCompartido = new Contador();
		
		Thread [] hilos = new Thread[NUM_HILOS];
		
		for(int i = 0;i<NUM_HILOS;i++) {
			Thread Hi = new Thread(new Hilo(i,CUENTA_TOTAL/NUM_HILOS,contCompartido));
			Hi.start();
			hilos[i] = Hi;
		}


		for(Thread h:hilos) {
			
			try {				
				h.join();
				
			}catch (InterruptedException e) {
				System.out.println("Se ha Interumpido el Hilo");
			}

		}

		System.out.printf("Cuenta Global: %s \n",contCompartido.getCuenta());
	}

}
