package AccesoDirecto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class productos {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Cantidad de Productos a Agregar: ");
		
		int numProductos = entrada.nextInt();
		
			try {
				
				File fichero = new File("productos.dat");
				fichero.createNewFile();
				
				RandomAccessFile raf = new RandomAccessFile(fichero,"rw");
			
				
				for(int i=0;i<numProductos;i++) {
					
					System.out.println("ID del Producto: ");
					int ID = entrada.nextInt();
					raf.writeInt(ID);
					entrada.nextLine();

					System.out.println("Stock del Producto: ");
					int CantidadStock = entrada.nextInt();
					raf.writeInt(CantidadStock);
					entrada.nextLine();

					System.out.println("Precio del Producto: ");
					double Precio = entrada.nextDouble();
					raf.writeDouble(Precio);
					entrada.nextLine();

				}
				
				raf.seek(0);
				
				while(raf.getFilePointer()<raf.length()) {
					System.out.print(raf.readInt()+",");
					System.out.print(raf.readInt()+",");
					System.out.println(raf.readDouble());
				}

				
				raf.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
	
	
	}

}