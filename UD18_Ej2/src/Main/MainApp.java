package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MainApp {
	
	private static Connection conexion = null;

	public static void main(String[] args) {
		
		createDB("ud18_ej2");

		openConection();
		createTable("ud18_ej2", "Departamentos", "(Codigo int PRIMARY KEY, Nombre varchar(100), Presupuesto int)");
		createTable("ud18_ej2", "Empleados", "(DNI varchar(9) PRIMARY KEY, Nombre varchar(100), Apellidos varchar(255), Departamento int,"
				+ "KEY Departamento_idx (Departamento), CONSTRAINT Departamento FOREIGN KEY (Departamento) REFERENCES Departamentos (Codigo))");
		insertData("ud18_ej2", "Departamentos", "(Codigo, Nombre, Presupuesto) VALUES ('1', 'Informatica', '1500')");
		insertData("ud18_ej2", "Departamentos", "(Codigo, Nombre, Presupuesto) VALUES ('2', 'RRHH', '100')");
		insertData("ud18_ej2", "Departamentos", "(Codigo, Nombre, Presupuesto) VALUES ('3', 'Secretaria', '1000')");
		insertData("ud18_ej2", "Departamentos", "(Codigo, Nombre, Presupuesto) VALUES ('4', 'Logistica', '2500')");
		insertData("ud18_ej2", "Departamentos", "(Codigo, Nombre, Presupuesto) VALUES ('5', 'Seguridad', '500')");
		insertData("ud18_ej2", "Empleados", "(DNI, Nombre, Apellidos, Departamento) VALUES ('24513578H', 'Joan', 'Rofes', '1')");
		insertData("ud18_ej2", "Empleados", "(DNI, Nombre, Apellidos, Departamento) VALUES ('54236789A', 'Jordi', 'Garcia', '2')");
		insertData("ud18_ej2", "Empleados", "(DNI, Nombre, Apellidos, Departamento) VALUES ('41275345D', 'Jose', 'Rodriguez', '4')");
		insertData("ud18_ej2", "Empleados", "(DNI, Nombre, Apellidos, Departamento) VALUES ('42356872F', 'Rafa', 'Nadal', '3')");
		insertData("ud18_ej2", "Empleados", "(DNI, Nombre, Apellidos, Departamento) VALUES ('74253681C', 'Gerard', 'Navas', '5')");
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
