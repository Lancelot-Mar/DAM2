package Hilos_sleep;

public class ContadorEncuesta {

	private int num;
	
	
	synchronized public int getCuenta() {
		return num;
	}
	
	synchronized public int Incrementa() {
		this.num++;
		return num;
	}
	
}
