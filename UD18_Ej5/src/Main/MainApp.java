package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MainApp {
	
	private static Connection conexion = null;

	public static void main(String[] args) {
		
		createDB("ud18_ej5");

		openConection();
		createTable("ud18_ej5", "Despachos", "(Numero int PRIMARY KEY, Capacidad int)");
		createTable("ud18_ej5", "Directores", "(DNI varchar(9) PRIMARY KEY, NomApels nvarchar(255), Despacho int, Jefe varchar(9),"
				  + "KEY Jefe_idx (Jefe), KEY Despacho_idx (Despacho), CONSTRAINT Despacho FOREIGN KEY (Despacho) REFERENCES Despachos (Numero), CONSTRAINT Jefe FOREIGN KEY (Jefe) REFERENCES Directores (DNI))");
		insertData("ud18_ej5", "Despachos", "(Numero, Capacidad) VALUES ('1', '50')");
		insertData("ud18_ej5", "Despachos", "(Numero, Capacidad) VALUES ('2', '100')");
		insertData("ud18_ej5", "Despachos", "(Numero, Capacidad) VALUES ('3', '10')");
		insertData("ud18_ej5", "Despachos", "(Numero, Capacidad) VALUES ('4', '25')");
		insertData("ud18_ej5", "Despachos", "(Numero, Capacidad) VALUES ('5', '5')");
		insertData("ud18_ej5", "Directores", "(DNI, NomApels, Despacho, Jefe) VALUES ('24513578H', 'Joan Rofes', '1', '24513578H')");
		insertData("ud18_ej5", "Directores", "(DNI, NomApels, Despacho, Jefe) VALUES ('54236789A', 'Jordi Garcia', '2', '24513578H')");
		insertData("ud18_ej5", "Directores", "(DNI, NomApels, Despacho, Jefe) VALUES ('41275345D', 'Jose Rodriguez', '4', '24513578H')");
		insertData("ud18_ej5", "Directores", "(DNI, NomApels, Despacho, Jefe) VALUES ('42356872F', 'Rafa Nadal', '3', '41275345D')");
		insertData("ud18_ej5", "Directores", "(DNI, NomApels, Despacho, Jefe) VALUES ('74253681C', 'Gerard Navas', '5', '41275345D')"); 
		closeConection();

	}
	
	public static void createDB(String name)
	{
		try 
		{
			openConection();
			String Query = "CREATE DATABASE " + name;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			closeConection();
			System.out.println("Se ha creado la base de datos " + name + " de forma exitosa.");
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			System.out.println("Error creando DB.");
		}
	}
	
	public static void createTable(String db, String name, String campos)
	{
		try 
		{
			String QueryDB = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(QueryDB);
			
			String Query = "CREATE TABLE " + name + " " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			System.out.println("Error creando tabla.");
		}
	}
	
	public static void insertData(String db, String table_name, String campos)
	{
		try 
		{
			String QueryDB = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(QueryDB);
			
			String Query = "INSERT INTO " + table_name + " " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos almacenados correctamente");
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			System.out.println("Error creando tabla.");
		}
	}
	
	public static void openConection()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://149.34.2.157:3306?userTimezone=true&serverTimezone=UTC", "javaUser", "Java1234_");
			System.out.println("Server Connected");
			
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("No se ha podido conectar con mi base de datos.");
		}
	}
	
	public static void closeConection()
	{
		try 
		{
			conexion.close();
			System.out.println("Se ha finalizado la conexión con el servidor.");
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
