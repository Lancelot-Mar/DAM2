package PF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class Main_Pruebas {
	
	
	public static void cargarEmpleados() {
		ArrayList<Empleado> empleadosIntermedia = new ArrayList<>();
		File archivo = new File("EMPLEADOS/empleados.dat");
			try {

				ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));

				// pasamos el texto de dat a texto plano
				empleadosIntermedia = (ArrayList<Empleado>) in.readObject();

				if (empleadosIntermedia != null) {

					empleadosArr.addAll(empleadosIntermedia);

				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} 


	
	
    public static void main(String[] args) throws IOException {

    	verificarEstructura();

				
    	
    }
}


