/**
 * @author Jordi Ribellas Ramos
 * UD18 - Act1
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.MysqlType;

public class ej1App {
	
	private static Connection conexion = null;
	
	public static void main(String[] args) {
		createDB("TiendaInformatica");
		openConnection();
		createTable("TiendaInformatica", "Fabricantes", "Articulos");
		closeConnection();
	}
	
	public static void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.210?useTimezone=true&serverTimezone=UTC", "javaUser", "Java1234_");
			System.out.println("La connexion se ha podido realizar correctamente");

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("La connexion no se ha podido realizar correctamente");
			System.out.println(ex);
		}
	}

	public static void closeConnection() {
		try {
			conexion.close();
			
		} catch (SQLException ex) {
			Logger.getLogger(MysqlType.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public static void createDB(String nombre) {
		
		try {
			openConnection();
			String Query = "Create Database " + nombre;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			closeConnection();
			System.out.println("Se ha creado la base de datos " + nombre);
			
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void createTable(String db, String tabla1, String tabla2) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String QueryT1 = "CREATE TABLE " + tabla1 + " (Codigo INT PRIMARY KEY, Nombre NVARCHAR(100))";
			String QueryT2 = "CREATE TABLE " + tabla2 + " (Codigo INT PRIMARY KEY, Nombre NVARCHAR(100), Precio INT, Fabricante INT, "
					+ "KEY Fabricante_IDX(Fabricante), constraint Fabricante FOREIGN KEY (Fabricante) references Fabricantes (Codigo))";
			
			Statement st = conexion.createStatement();
			st.executeUpdate(QueryT1);
			System.out.println("Se ha creado la tabla " + tabla1);
			
			st.executeUpdate(QueryT2);
			System.out.println("Se ha creado la tabla " + tabla2);
			
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
			System.out.println("Error creando tablas");
		}
	}

}
