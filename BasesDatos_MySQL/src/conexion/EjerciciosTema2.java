package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EjerciciosTema2 {

	public static void Ej1(String url,String usuario,String password) {
		
		Scanner entrada = new Scanner(System.in);
		
		 try {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 
			 Connection conexion = DriverManager.getConnection(url, usuario, password);
			 System.out.println("Se ha conectado a la base de datos");
			
			 String consulta = "Select * from jugadores where nombre like ?";
			 PreparedStatement sentencia = conexion.prepareStatement(consulta);

			 System.out.println("Introduce la primera letra del nobre del jugador a buscar: ");
			 String nom = entrada.next();
			 
			 nom += "%";
			 
			 sentencia.setString(1, nom);
			 
			 ResultSet resultado = sentencia.executeQuery();
			 
			 while (resultado.next()) {
				 int Codigo = resultado.getInt("codigo");
				 String Nombre = resultado.getString("Nombre");
				 String Procedencia = resultado.getString("Procedencia");
				 String Altura = resultado.getString("Altura");
				 int Peso = resultado.getInt("Peso");
				 String Posicion = resultado.getString("Posicion");
				 String Nombre_equipo = resultado.getString("Nombre_equipo");

				 System.out.printf("codigo = %d, nombre = %s, procedencia = %s, Altura = %s, peso = %d, posicion = %s, nombre Equipo = %s \n",	
				 Codigo,Nombre,Procedencia,Altura,Peso,Posicion,Nombre_equipo);	 
			 }
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }
		 
		 entrada.close();
	}
	
	public static void Ej2() {
		
	}
	
	public static void Ej3() {
		
	}
	
	public static void Ej4() {
		
	}
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		 Ej1(url,usuario,password);
	}
		
}
