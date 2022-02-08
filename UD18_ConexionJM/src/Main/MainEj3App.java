package Main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MainEj3App {
	
	private static Connection conexion = null;
	
	public static void main(String[] args) {
		createDB("LosAlmacenes");
		openConnection();
		createTable("LosAlmacenes", "Almacenes", "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Lugar NVARCHAR(100),"
				+ "Capacidad INT)");
		createTable("LosAlmacenes", "Cajas", "(NumReferencia CHAR(5) PRIMARY KEY, Contenido NVARCHAR(100),"
				+ "Valor INT, Almacen INT, KEY Almacen_idx (Almacen),"
				+ "CONSTRAINT Almacen FOREIGN KEY (Almacen) REFERENCES Almacenes (Codigo))");
		insertData("LosAlmacenes", "Almacenes", "(Codigo, Lugar, Capacidad)", "('1', 'Reus', '200')");
		insertData("LosAlmacenes", "Almacenes", "(Codigo, Lugar, Capacidad)", "('2', 'Calafell', '220')");
		insertData("LosAlmacenes", "Almacenes", "(Codigo, Lugar, Capacidad)", "('3', 'Cunit', '240')");
		insertData("LosAlmacenes", "Almacenes", "(Codigo, Lugar, Capacidad)", "('4', 'Tarragona', '260')");
		insertData("LosAlmacenes", "Almacenes", "(Codigo, Lugar, Capacidad)", "('5', 'Barcelona', '280')");
		insertData("LosAlmacenes", "Cajas", "(NumReferencia, Contenido, Valor, Almacen)", "('12345', 'Patatas', '20', '1')");
		insertData("LosAlmacenes", "Cajas", "(NumReferencia, Contenido, Valor, Almacen)", "('ABCDE', 'Ordenadores', '50', '2')");
		insertData("LosAlmacenes", "Cajas", "(NumReferencia, Contenido, Valor, Almacen)", "('ABC12', 'Piezas', '30', '3')");
		insertData("LosAlmacenes", "Cajas", "(NumReferencia, Contenido, Valor, Almacen)", "('123AB', 'Cocacola', '10', '4')");
		insertData("LosAlmacenes", "Cajas", "(NumReferencia, Contenido, Valor, Almacen)", "('12ABC', 'Manzanas', '90', '5')");
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
	
	public static void createDB(String LosAlmacenes) {
		try {
			openConnection();
			String Query = "CREATE DATABASE " + LosAlmacenes;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			closeConnection();
			JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + LosAlmacenes + " de forma exitosa.");
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
