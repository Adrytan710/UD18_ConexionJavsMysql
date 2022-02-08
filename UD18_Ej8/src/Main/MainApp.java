package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MainApp {
	
	private static Connection conexion = null;

	public static void main(String[] args) {
		
		createDB("ud18_ej8");

		openConection();
		createTable("ud18_ej8", "Cajeros", "(Codigo int PRIMARY KEY, NomAples nvarchar(255))");
		createTable("ud18_ej8", "Productos", "(Codigo int PRIMARY KEY, Nombre nvarchar(100), Precio int)");
		createTable("ud18_ej8", "Maquinas_Registradoras", "(Codigo int PRIMARY KEY, Piso int)");
		createTable("ud18_ej8", "Venta", "(id int PRIMARY KEY AUTO_INCREMENT, Cajero int, Producto int, Maquina int,"
				  + "KEY Cajero_idx (Cajero), KEY Producto_idx (Producto), KEY Maquina_idx (Maquina), "
				  + "CONSTRAINT Cajero FOREIGN KEY (Cajero) REFERENCES Cajeros (Codigo), CONSTRAINT Producto FOREIGN KEY (Producto) REFERENCES Productos (Codigo), CONSTRAINT Maquina FOREIGN KEY (Maquina) REFERENCES Maquinas_Registradoras (Codigo))");
		insertData("ud18_ej8", "Cajeros", "(Codigo, NomAples) VALUES ('1', 'Cajero1')");
		insertData("ud18_ej8", "Cajeros", "(Codigo, NomAples) VALUES ('2', 'Cajero2')");
		insertData("ud18_ej8", "Cajeros", "(Codigo, NomAples) VALUES ('3', 'Cajero3')");
		insertData("ud18_ej8", "Cajeros", "(Codigo, NomAples) VALUES ('4', 'Cajero4')");
		insertData("ud18_ej8", "Cajeros", "(Codigo, NomAples) VALUES ('5', 'Cajero5')");
		insertData("ud18_ej8", "Productos", "(Codigo, Nombre, Precio) VALUES ('1', 'Armario', '80')");
		insertData("ud18_ej8", "Productos", "(Codigo, Nombre, Precio) VALUES ('2', 'Espejo', '150')");
		insertData("ud18_ej8", "Productos", "(Codigo, Nombre, Precio) VALUES ('3', 'Puerta', '100')");
		insertData("ud18_ej8", "Productos", "(Codigo, Nombre, Precio) VALUES ('4', 'Mesa', '250')");
		insertData("ud18_ej8", "Productos", "(Codigo, Nombre, Precio) VALUES ('5', 'Ventilador', '20')");
		insertData("ud18_ej8", "Maquinas_Registradoras", "(Codigo, Piso) VALUES ('1', '0')");
		insertData("ud18_ej8", "Maquinas_Registradoras", "(Codigo, Piso) VALUES ('2', '0')");
		insertData("ud18_ej8", "Maquinas_Registradoras", "(Codigo, Piso) VALUES ('3', '1')");
		insertData("ud18_ej8", "Maquinas_Registradoras", "(Codigo, Piso) VALUES ('4', '2')");
		insertData("ud18_ej8", "Maquinas_Registradoras", "(Codigo, Piso) VALUES ('5', '3')");
		insertData("ud18_ej8", "Venta", "(Cajero, Producto, Maquina) VALUES ('1', '1', '1')");
		insertData("ud18_ej8", "Venta", "(Cajero, Producto, Maquina) VALUES ('2', '2', '2')");
		insertData("ud18_ej8", "Venta", "(Cajero, Producto, Maquina) VALUES ('4', '4', '4')");
		insertData("ud18_ej8", "Venta", "(Cajero, Producto, Maquina) VALUES ('3', '3', '3')");
		insertData("ud18_ej8", "Venta", "(Cajero, Producto, Maquina) VALUES ('5', '5', '5')"); 
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
