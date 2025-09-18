package PracticaVehiculosInteligentes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List <CocheAutonomo> coches = new ArrayList<>();
	static List <DroneAereo> dron = new ArrayList<>();
	static List <RoboRepartidor> robot = new ArrayList<>();
	
	public static void agregarCoche() {
			
		Scanner entrada = new Scanner(System.in);
	
		System.out.println("Cantidad de coches a introducir: ");
		int cant = entrada.nextInt();
			
		for(int i=0;i<cant;i++) {
				
			System.out.println("Introduce el id: ");
			int id = entrada.nextInt();
				
			System.out.println("Introduce el modelo: ");
			String modeloString = entrada.next();
				
			System.out.println("Tiene sensores activos: ");
			boolean sensores = entrada.nextBoolean();
				
			System.out.println("Nivel de bateria: ");
			double bateria = entrada.nextDouble();
				
			System.out.println("Fecha de activacion: ");
			String fechaActivacion = entrada.next();
				
			System.out.println("Nivel de autonomia: ");
			int nivelAutonomia = entrada.nextInt();
				
			System.out.println("Velocidad Maxima: ");
			double velocidadMaxima = entrada.nextDouble();
				
			System.out.println("Introduce la Matricula: ");
			String matricula = entrada.next();
				
			System.out.println("Numero de pasajeros: ");
			int numeroPasajeros = entrada.nextInt();
				
			System.out.println("El Software de version: ");
			String softwareVersion = entrada.next();
				
			CocheAutonomo cocheAutonomo = new CocheAutonomo(id,modeloString,sensores,bateria,fechaActivacion,nivelAutonomia,velocidadMaxima,matricula,numeroPasajeros,softwareVersion);
				
			coches.add(cocheAutonomo);	
		}
	}
	
	public static void agregarDron() {
		
		Scanner entrada = new Scanner(System.in);

		System.out.println("Cantidad de drones a introducir: ");
		int cant = entrada.nextInt();
		
		for(int i=0;i<cant;i++) {
			
			System.out.println("Introduce el id: ");
			int id = entrada.nextInt();
			
			System.out.println("Introduce el modelo: ");
			String modeloString = entrada.next();
			
			System.out.println("Tiene sensores activos: ");
			boolean sensores = entrada.nextBoolean();
			
			System.out.println("Nivel de bateria: ");
			double bateria = entrada.nextDouble();
			
			System.out.println("Fecha de activacion: ");
			String fechaActivacion = entrada.next();
			
			System.out.println("Altitud Maxima: ");
			double altitud = entrada.nextDouble();
			
			System.out.println("Numero Helices: ");
			int numeroHelices = entrada.nextInt();
			
			System.out.println("Peso Maximo: ");
			double pesoCarga = entrada.nextDouble();
			
			System.out.println("GPS Integrado: ");
			boolean gps = entrada.nextBoolean();
			
			System.out.println("Fabricante: ");
			String fabricante = entrada.next();
			
			DroneAereo dronaereo = new DroneAereo(id,modeloString,sensores,bateria,fechaActivacion,altitud,numeroHelices,pesoCarga,gps,fabricante);
			
			dron.add(dronaereo);	
		}
}

	public static void main(String[] args) {
	
		agregarCoche();
		agregarDron();
		
	}
	
}

