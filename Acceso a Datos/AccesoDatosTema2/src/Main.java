import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner entrada = new Scanner(System.in);
	
	public static void ej1() {

		String url = "jdbc:mysql://localhost:3306/nba";
		
		String usuario="root";
		String contr="cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url,usuario,contr);
			
			System.out.println("Introduce la Inicial del jugador: ");
			String letraBuscar = entrada.next()+'%';
			
			String consulta = "select * from jugadores where Nombre like ?";
			PreparedStatement sentencia = conexion.prepareCall(consulta);
			sentencia.setString(1, letraBuscar);
			
			ResultSet resultado = sentencia.executeQuery(); //Cuidado usar libreria Prepares Statement
			
			while(resultado.next()) {
				int codigo = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo = resultado.getString("Nombre_Equipo");
				
				System.out.printf("codigo: %d, nombre: %s,procedencia: %s,altura: %s,peso: %d,posicion: %s,equipo: %s\n",codigo,nombre,procedencia,altura,peso,posicion,equipo);
			}
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void ej2() {

		String url = "jdbc:mysql://localhost:3306/nba";
		
		String usuario="root";
		String contr="cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url,usuario,contr);
			
			
			String consulta = "select avg(Peso) peso_general from jugadores";
			PreparedStatement sentencia = conexion.prepareCall(consulta);
			
			ResultSet resultado = sentencia.executeQuery(); //Cuidado usar libreria Prepares Statement
			
			while(resultado.next()) {
				int codigo = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo = resultado.getString("Nombre_Equipo");
				
				System.out.printf("codigo: %d, nombre: %s,procedencia: %s,altura: %s,peso: %d,posicion: %s,equipo: %s\n",codigo,nombre,procedencia,altura,peso,posicion,equipo);
			}
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void ej3() {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		
		String usuario="root";
		String contr="cfgs";
		
		ArrayList <String> nombres = new ArrayList<>();
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(url, usuario, contr);

			int codigo=0;

			String consultacodigo = "select nombre from equipos";
			PreparedStatement sentencia = conexion.prepareStatement(consultacodigo);

			ResultSet resultado = sentencia.executeQuery(); // Cuidado usar libreria Prepares Statement

			while (resultado.next()) {
				String nombre = resultado.getString("Nombre");
				System.out.println(codigo+": "+nombre);
				nombres.add(nombre);
				codigo++;
			}
			
			System.out.println("Introducir el numero del equipo:");
			int numequipo = entrada.nextInt();

			
			String consultaagregarjugador = "select nombre from jugadores where Nombre_equipo=?";
			PreparedStatement sentencia2 = conexion.prepareStatement(consultaagregarjugador);

			sentencia2.setString(1,nombres.get(numequipo));
			
			resultado = sentencia2.executeQuery(); //Cuidado usar libreria Prepares Statement


			while(resultado.next()) {
				String nombre = resultado.getString("Nombre");
				System.out.printf("%s\n",nombre);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void ej4() {

		String url = "jdbc:mysql://localhost:3306/nba";
		
		String usuario="root";
		String contr="cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url,usuario,contr);

			int codigo = 1;
			
			String consultacodigo = "select codigo from jugadores order by codigo desc limit 1";
			PreparedStatement sentencia = conexion.prepareCall(consultacodigo);

			ResultSet resultado = sentencia.executeQuery(); //Cuidado usar libreria Prepares Statement
			
			while(resultado.next()) {
				codigo = resultado.getInt("codigo");
			}
			
			System.out.println("Introducir el nombre del nuevo jugador:");
			String nombre = entrada.next();
			
			String consultaagregarjugador = "insert into jugadores (codigo,nombre) values (?,?)";
			PreparedStatement sentencia2 = conexion.prepareCall(consultaagregarjugador);

			ResultSet resulta2 = sentencia.executeQuery(); //Cuidado usar libreria Prepares Statement
			sentencia2.setString(1,(codigo+1));
			sentencia2.setString(2, nombre);
			
			while(resultado.next()) {
				codigo = resultado.getInt("codigo");
				nombre = resultado.getString("Nombre");
				
				System.out.printf("codigo: %d, nombre: %s\n",codigo,nombre);
			}

		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void ej5() {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		
		String usuario="root";
		String contr="cfgs";
		
		ArrayList <String> nombres = new ArrayList<>();
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(url, usuario, contr);

			System.out.println("Introducir el numero del jugador a borrar:");
			int num = entrada.nextInt();
			
			String consultacodigo = "delete from jugadores where codigo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consultacodigo);
			
			sentencia.setLong(1,num);

			ResultSet resultado = sentencia.executeQuery(); // Cuidado usar libreria Prepares Statement

			while (resultado.next()) {
				String nombre = resultado.getString("Nombre");
				System.out.println(nombre+": "+nombre);
				nombres.add(nombre);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		ej5();
		
		//Ej 3 y 1 son los unicos que funcionan
	}

}
