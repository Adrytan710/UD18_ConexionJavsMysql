package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MainApp {
	
	private static Connection conexion = null;

	public static void main(String[] args) {
		
		createDB("ud18_ud12_ej7");

		openConection();
		createTable("ud18_ud12_ej7", "Nominas", "(idNominas int PRIMARY KEY, Fecha date, Salario double)");
		createTable("ud18_ud12_ej7", "Contratos", "(idContratos int PRIMARY KEY, FechaInicio date, FechaFin date)");
		createTable("ud18_ud12_ej7", "Trabajadores", "(Codigo int PRIMARY KEY, DNI varchar(9), NumSS int, Nombre varchar(45), Apellidos varchar(45), Direccion varchar(45), Telefono varchar(45), Jefe int,"
				+ "KEY Jefe_idx (Jefe), CONSTRAINT Jefe FOREIGN KEY (Jefe) REFERENCES Trabajadores (Codigo))");
		
		createTable("ud18_ud12_ej7", "Departamentos", "(Codigo int PRIMARY KEY, Nombre varchar(45), Trabajador int,"
				+ "KEY Trabajador_idx (Trabajador), CONSTRAINT Trabajador FOREIGN KEY (Trabajador) REFERENCES Trabajadores (Codigo))");
		
		createTable("ud18_ud12_ej7", "Trabajan", "(Trabajador int, Departamento int, PRIMARY KEY (Trabajador, Departamento),"
				+ "KEY Trabajador1_idx (Trabajador), KEY Departamento_idx (Trabajador), CONSTRAINT Departamento FOREIGN KEY (Departamento) REFERENCES Departamentos (Codigo))");
		
		createTable("ud18_ud12_ej7", "CategoriaProfesional", "(idCategoria int PRIMARY KEY AUTO_INCREMENT, idContrato int, CodTrabajador int, idNomina int,"
				  + "KEY idContrato_idx (idContrato), KEY CodTrabajador_idx (CodTrabajador), KEY idNomina_idx (idNomina), "
				  + "CONSTRAINT idContrato FOREIGN KEY (idContrato) REFERENCES Contratos (idContratos), CONSTRAINT CodTrabajador FOREIGN KEY (CodTrabajador) REFERENCES Trabajadores (Codigo), "
				  + "CONSTRAINT idNomina FOREIGN KEY (idNomina) REFERENCES Nominas (idNominas))");
		
		insertData("ud18_ud12_ej7", "Nominas", "(idNominas, Fecha, Salario) VALUES ('1', '2022-01-10', '1600')");
		insertData("ud18_ud12_ej7", "Nominas", "(idNominas, Fecha, Salario) VALUES ('2', '2022-02-10', '1400')");
		insertData("ud18_ud12_ej7", "Nominas", "(idNominas, Fecha, Salario) VALUES ('3', '2022-03-10', '1400')");
		insertData("ud18_ud12_ej7", "Nominas", "(idNominas, Fecha, Salario) VALUES ('4', '2021-12-10', '1300')");
		insertData("ud18_ud12_ej7", "Nominas", "(idNominas, Fecha, Salario) VALUES ('5', '2021-11-10', '1350')");
		
		insertData("ud18_ud12_ej7", "Contratos", "(idContratos, FechaInicio, FechaFin) VALUES ('1', '2022-01-10', '2022-04-01')");
		insertData("ud18_ud12_ej7", "Contratos", "(idContratos, FechaInicio, FechaFin) VALUES ('2', '2022-04-01', '2023-04-01')");
		insertData("ud18_ud12_ej7", "Contratos", "(idContratos, FechaInicio, FechaFin) VALUES ('3', '2020-10-10', '2021-12-17')");
		insertData("ud18_ud12_ej7", "Contratos", "(idContratos, FechaInicio, FechaFin) VALUES ('4', '2017-02-17', '2017-06-25')");
		insertData("ud18_ud12_ej7", "Contratos", "(idContratos, FechaInicio, FechaFin) VALUES ('5', '2022-01-10', '2022-04-01')");
		
		insertData("ud18_ud12_ej7", "Trabajadores", "(Codigo, DNI, NumSS, Nombre, Apellidos, Direccion, Telefono, Jefe) VALUES ('1', '05655542A', '577709367', 'Jesica', 'Meseguer', 'C/ Test nº 1', '123456789', '1')");
		insertData("ud18_ud12_ej7", "Trabajadores", "(Codigo, DNI, NumSS, Nombre, Apellidos, Direccion, Telefono, Jefe) VALUES ('2', '12345678F', '2367568', 'Jordi', 'Ribellas Ramos', 'C/ Test nº 2', '123456789', '1')");
		insertData("ud18_ud12_ej7", "Trabajadores", "(Codigo, DNI, NumSS, Nombre, Apellidos, Direccion, Telefono, Jefe) VALUES ('3', '25463020L', '525426381', 'Maria Laura', 'Moro', 'C/ Test nº 3', '123456789', '3')");
		insertData("ud18_ud12_ej7", "Trabajadores", "(Codigo, DNI, NumSS, Nombre, Apellidos, Direccion, Telefono, Jefe) VALUES ('4', '36584485H', '577846898', 'Rachid', 'Valera', 'C/ Test nº 4', '123456789', '2')");
		insertData("ud18_ud12_ej7", "Trabajadores", "(Codigo, DNI, NumSS, Nombre, Apellidos, Direccion, Telefono, Jefe) VALUES ('5', '49251605A', '261386950', 'Brahim', 'Castells', 'C/ Test nº 5', '123456789', '2')");
		
		insertData("ud18_ud12_ej7", "Departamentos", "(Codigo, Nombre, Trabajador) VALUES ('1', 'Medicina', '4')");
		insertData("ud18_ud12_ej7", "Departamentos", "(Codigo, Nombre, Trabajador) VALUES ('2', 'Fisica', '3')");
		insertData("ud18_ud12_ej7", "Departamentos", "(Codigo, Nombre, Trabajador) VALUES ('3', 'Matematicas', '5')");
		insertData("ud18_ud12_ej7", "Departamentos", "(Codigo, Nombre, Trabajador) VALUES ('4', 'Biologia', '1')");
		insertData("ud18_ud12_ej7", "Departamentos", "(Codigo, Nombre, Trabajador) VALUES ('5', 'Psicologia', '2')"); 

		insertData("ud18_ud12_ej7", "Trabajan", "(Trabajador, Departamento) VALUES ('1', '1')"); 
		insertData("ud18_ud12_ej7", "Trabajan", "(Trabajador, Departamento) VALUES ('2', '2')"); 
		insertData("ud18_ud12_ej7", "Trabajan", "(Trabajador, Departamento) VALUES ('3', '3')"); 
		insertData("ud18_ud12_ej7", "Trabajan", "(Trabajador, Departamento) VALUES ('4', '4')"); 
		insertData("ud18_ud12_ej7", "Trabajan", "(Trabajador, Departamento) VALUES ('5', '5')"); 

		insertData("ud18_ud12_ej7", "CategoriaProfesional", "(idCategoria, idContrato, CodTrabajador, idNomina) VALUES ('1', '1', '1', '1')"); 
		insertData("ud18_ud12_ej7", "CategoriaProfesional", "(idCategoria, idContrato, CodTrabajador, idNomina) VALUES ('2', '2', '2', '2')"); 
		insertData("ud18_ud12_ej7", "CategoriaProfesional", "(idCategoria, idContrato, CodTrabajador, idNomina) VALUES ('3', '3', '3', '3')"); 
		insertData("ud18_ud12_ej7", "CategoriaProfesional", "(idCategoria, idContrato, CodTrabajador, idNomina) VALUES ('4', '4', '4', '4')"); 
		insertData("ud18_ud12_ej7", "CategoriaProfesional", "(idCategoria, idContrato, CodTrabajador, idNomina) VALUES ('5', '5', '5', '5')"); 
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
