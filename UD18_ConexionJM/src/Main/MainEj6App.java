package Main;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MainEj6App {
	
	private static Connection conexion = null;
	
	public static void main(String[] args) {
		createDB("Piezas_Proveedores");
		openConnection();
		createTable("Piezas_Proveedores", "Piezas", "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre NVARCHAR(100))");
		createTable("Piezas_Proveedores", "Proveedores", "(Id CHAR(4) PRIMARY KEY, Nombre NVARCHAR(100))");
		createTable("Piezas_Proveedores", "Suministra", "(CodigoPieza INT PRIMARY KEY, IdProveedor CHAR(4), Precio INT,"
				+ "KEY CodigoP_idx (CodigoPieza), KEY IdP_idx (IdProveedor),"
				+ "CONSTRAINT CodigoPieza FOREIGN KEY (CodigoPieza) REFERENCES Piezas (Codigo),"
				+ "CONSTRAINT IdProveedor FOREIGN KEY (IdProveedor) REFERENCES Proveedores (Id))");
		insertData("Piezas_Proveedores", "Piezas", "(Codigo, Nombre)", "('1', 'Pieza1')");
		insertData("Piezas_Proveedores", "Piezas", "(Codigo, Nombre)", "('2', 'Pieza2')");
		insertData("Piezas_Proveedores", "Piezas", "(Codigo, Nombre)", "('3', 'Pieza3')");
		insertData("Piezas_Proveedores", "Piezas", "(Codigo, Nombre)", "('4', 'Pieza4')");
		insertData("Piezas_Proveedores", "Piezas", "(Codigo, Nombre)", "('5', 'Pieza5')");
		insertData("Piezas_Proveedores", "Proveedores", "(Id, Nombre)", "('1234', 'Proveedor1')");
		insertData("Piezas_Proveedores", "Proveedores", "(Id, Nombre)", "('2345', 'Proveedor2')");
		insertData("Piezas_Proveedores", "Proveedores", "(Id, Nombre)", "('3456', 'Proveedor3')");
		insertData("Piezas_Proveedores", "Proveedores", "(Id, Nombre)", "('4567', 'Proveedor4')");
		insertData("Piezas_Proveedores", "Proveedores", "(Id, Nombre)", "('5678', 'Proveedor5')");
		insertData("Piezas_Proveedores", "Suministra", "(CodigoPieza, IdProveedor, Precio)", "('1', '1234', '40')");
		insertData("Piezas_Proveedores", "Suministra", "(CodigoPieza, IdProveedor, Precio)", "('2', '2345', '50')");
		insertData("Piezas_Proveedores", "Suministra", "(CodigoPieza, IdProveedor, Precio)", "('3', '3456', '60')");
		insertData("Piezas_Proveedores", "Suministra", "(CodigoPieza, IdProveedor, Precio)", "('4', '4567', '70')");
		insertData("Piezas_Proveedores", "Suministra", "(CodigoPieza, IdProveedor, Precio)", "('5', '5678', '80')");
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