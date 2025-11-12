package Hilos_sleep;

public class Hilo implements Runnable {

	private final Contador cont;
	int id, MiParte, MiCuenta;

	Hilo(int id,int MiParte,Contador c) {
		this.id = id;
		this.MiParte = MiParte;
		this.cont = c;

	}

	public int getCuenta() {
		return MiCuenta;
	}
	
	@Override
	public void run() {
		
		for(int i=0; i<MiParte; i++) {
			
			this.cont.Incrementa();
			MiCuenta++;
		
		}
		
		System.out.printf("Hilo %d terminado, cuenta %d \n",id,getCuenta());
		
	}
	
}
