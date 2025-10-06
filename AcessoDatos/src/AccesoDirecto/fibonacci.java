package AccesoDirecto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class fibonacci {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		List <Integer> SerieFibonacci = new ArrayList<>();
		
		int a = 0;
	    int b = 1;
	    
		System.out.println("Que tamaño quieres que tenga la serie fibonacci: ");
		
		int numFibonacci = entrada.nextInt()-1;
	    
	    for (int i = 0; i < numFibonacci; i++) {
	        int siguiente = a + b;
	        a = b;
	        b = siguiente;
	        
	        SerieFibonacci.add(a);
	    }
	    
		try {
			
			File fichero = new File("fibonacci");
			fichero.createNewFile();
			
			RandomAccessFile raf = new RandomAccessFile(fichero,"rw");
			
			for(int h:SerieFibonacci) {
				raf.writeInt(h);
			}
			
			raf.seek(0);
			
			System.out.println("Serie Fibonacci: ");
				
			System.out.println(SerieFibonacci);	
			
			
			System.out.println("Que numero de la serie fibonacci quieres imprimir: ");
			
			int posicionUser = entrada.nextInt()-1;
			int posicion = posicionUser * 4;
			
			raf.seek(posicion);
			System.out.print(raf.readInt());

			
			raf.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}