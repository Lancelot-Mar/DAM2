package PracticaFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class Main_Pruebas {
	
	
	public static void verificarEstructura() {
	    System.out.println("=== Verificando estructura ===\n");
	    
	    // Crear todos los directorios
	    new File("PLANTAS").mkdirs();
	    new File("EMPLEADOS/BAJA").mkdirs();
	    new File("TICKETS").mkdirs();
	    new File("DEVOLUCIONES").mkdirs();
	    
	    System.out.println("Directorios verificados.\n");
	}
	
	
    public static void main(String[] args) throws IOException {

    	verificarEstructura();

				
    	
    }
}


