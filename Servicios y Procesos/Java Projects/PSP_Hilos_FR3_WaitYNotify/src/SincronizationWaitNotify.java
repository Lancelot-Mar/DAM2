
public class SincronizationWaitNotify {

	public static void main(String []args) {
		SincronizationWaitNotify.IniciarFlagsArrayHilos();
		
		for(int i=0;i<NUM_HILOS;i++) {
			Runnable hilo = new Hilo();
			Thread aux = new Thread(hilo);
			aux.setName(Integer.toString(i+1));
			
			aux.start();
		}
		
		try {
			synchronized(flagsArrayHilosFinalizados) {
				flagsArrayHilosFinalizados.wait();
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	//static = atributos/variables de alcanze de clase
	private static void IniciarFlagsArrayHilos() {
		// TODO Auto-generated method stub
		
	}
	
	
}
