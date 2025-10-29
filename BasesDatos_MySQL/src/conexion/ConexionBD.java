package conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionBD {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/mydb";
		String usuario = "root";
		String password = "cfgs";
		
		 try {
			 
			 //1.Creamos el driver :P
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 
			 
			 //2.Conectamos el SQL
			 Connection conexion = DriverManager.getConnection(url, usuario, password);
			 System.out.println("Se ha conectado a la base de datos");
			
			 /*
			 //3.1 Creamos un statement
			 Statement sentencia = conexion.createStatement();
			 String consulta = "*Select* from Usuario";
			 ResultSet resultado = sentencia.executeQuery(consulta);
			 */
			 
			 //3.2 Prepared Statement
			 String consulta = "Select * from usuario where idUsuario = ? or nombre = ?";
			 PreparedStatement sentencia = conexion.prepareStatement(consulta);
			 int num  = 1;
			 String nom = "Luis";
			 
			//Numero de la interogante en la sentencia , valor a agregar a la interogante
			 sentencia.setInt(1, num);
			 sentencia.setString(2, nom);
			
			 ResultSet resultado = sentencia.executeQuery();
			 
			 while (resultado.next()) {
				 int idUsuario = resultado.getInt("idUsuario");
				 Date fecha = resultado.getDate("Fecha_Nacimiento");
				 String nombre = resultado.getString("Nombre");
				 String genero = resultado.getString("Genero");
				 System.out.printf("idUsuario:\s,Fecha de Nacimiento: \n, Nombre:\s , Genero: \s",idUsuario,fecha, nombre,genero);
				 
			 }
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }

	}

}
