package Main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MainEj7App {
	
	private static Connection conexion = null;
	
	public static void main(String[] args) {
		createDB("LosCientificos");
		openConnection(); 
		createTable("LosCientificos", "Cientificos", "(DNI VARCHAR(9) PRIMARY KEY, NomApels NVARCHAR(255))");
		createTable("LosCientificos", "Proyecto", "(Id CHAR(4) PRIMARY KEY, Nombre NVARCHAR(255), Horas INT)");
		createTable("LosCientificos", "Asignado_A", "(Cientifico VARCHAR(9) PRIMARY KEY, Proyecto CHAR(4),"
				+ "KEY Cientifico_idx (Cientifico), KEY Proyecto_idx (Proyecto),"
				+ "CONSTRAINT Cientifico FOREIGN KEY (Cientifico) REFERENCES Cientificos (DNI),"
				+ "CONSTRAINT Proyecto FOREIGN KEY (Proyecto) REFERENCES Proyecto (Id))");
		insertData("LosCientificos", "Cientificos", "(DNI, NomApels)", "('12345678Z', 'Adrián Rodriguez')");
		insertData("LosCientificos", "Cientificos", "(DNI, NomApels)", "('87654321N', 'Joan Rofes')");
		insertData("LosCientificos", "Cientificos", "(DNI, NomApels)", "('13579241L', 'Jordi Ribelles')");
		insertData("LosCientificos", "Cientificos", "(DNI, NomApels)", "('45678912N', 'Albert Pérez')");
		insertData("LosCientificos", "Cientificos", "(DNI, NomApels)", "('12398765Z', 'Victor Castillo')");
		insertData("LosCientificos", "Proyecto", "(Id, Nombre, Horas)", "('1234', 'Proyecto1', '50')");
		insertData("LosCientificos", "Proyecto", "(Id, Nombre, Horas)", "('2345', 'Proyecto2', '100')");
		insertData("LosCientificos", "Proyecto", "(Id, Nombre, Horas)", "('3456', 'Proyecto3', '80')");
		insertData("LosCientificos", "Proyecto", "(Id, Nombre, Horas)", "('4567', 'Proyecto4', '90')");
		insertData("LosCientificos", "Proyecto", "(Id, Nombre, Horas)", "('5678', 'Proyecto5', '140')");
		insertData("LosCientificos", "Asignado_A", "(Cientifico, Proyecto)", "('12345678Z', '1234')");
		insertData("LosCientificos", "Asignado_A", "(Cientifico, Proyecto)", "('87654321N', '2345')");
		insertData("LosCientificos", "Asignado_A", "(Cientifico, Proyecto)", "('13579241L', '3456')");
		insertData("LosCientificos", "Asignado_A", "(Cientifico, Proyecto)", "('45678912N', '4567')");
		insertData("LosCientificos", "Asignado_A", "(Cientifico, Proyecto)", "('12398765Z', '5678')");
		closeConnection();
	}
	
	public static void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.160:3306?useTimezone=true&serverTimezine=UTC","javaUser","Java1234_");
			System.out.println("Servidor conectado");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar con la base de datos");
			System.out.println(ex);
		}
	}
	
	public static void closeConnection() {
		try {
			conexion.close();
			JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error cerrando conexion.");
		}
	}
	
	public static void createDB(String db) {
		try {
			openConnection();
			String Query = "CREATE DATABASE " + db;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			closeConnection();
			JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + db + " de forma exitosa.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando bd.");
		}
	}
	
	public static void createTable(String db, String name, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			String Query = "CREATE TABLE " + name + " " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando tabla.");
		}
	}
	
	public static void insertData(String db, String name, String valores, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			String Query = "INSERT INTO " + name + " " + valores + " VALUE " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos almacenados correctamente");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en el almacenamiento.");
		}
	}
}