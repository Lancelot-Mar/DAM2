import java.util.Date;

public class Hilo implements Runnable {

	/*
	private final String nombre;

	Hilo(String nombre) {
		this.nombre = nombre;
	}

	public void run() {
		System.out.printf("Hola soy un hilo %s \n", this.nombre);
		System.out.printf("Hilo terminado %s \n", this.nombre);
	}
	*/
	
	@Override
	
	public void run() {
		
		int ThreadNumber = Integer.parseInt(Thread.currentThread().getName());
		
		//El tiempo en SI se mide en mls desde el 00:00:00 1 de enero de 1970
		long tsInicio = (new Date()).getTime();
		
		System.out.println("COMIENZA LA EJECUCION DEL HILO => "+ThreadNumber);
		
		try {
			Thread.sleep(2000);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		
		}
		
		System.out.println("FIN DEL HILO => "+ThreadNumber+"\nTIEMPO => "+(ThreadNumber-tsInicio)/1000.0f);
		long tsFin = (new Date()).getTime();

		System.out.println();
		
		registrarFinEjecucionHilo(ThreadNumber);
	}

	private void registrarFinEjecucionHilo(int threadNumber) {

		boolean[] arrayHilosFinalizados = SincronizationWaitNotify.getFlagsArrayHilosFinalizados();
		arrayHilosFinalizados[threadNumber-1]=true;
		
		for(boolean b:arrayHilosFinalizados) {	
			if(b == false) {	
				return;
			}
		}
		
	}

}
