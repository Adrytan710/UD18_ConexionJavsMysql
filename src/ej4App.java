
/**
 * @author Jordi Ribellas Ramos
 * UD18 - Act4
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.MysqlType;

public class ej4App {
			
		private static Connection conexion = null;
		
		public static void main(String[] args) {
			createDB("Cine");
			openConnection();
			createTable("Cine", "Salas", "Peliculas");
			insert("Cine", "Salas", "Peliculas");
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
				
				String QueryT1 = "CREATE TABLE " + tabla1 + " (Codigo INT PRIMARY KEY, Nombre NVARCHAR(100), Pelicula INT, "
						+ "KEY Pelicula_IDX(Pelicula), constraint Pelicula FOREIGN KEY (Pelicula) references Peliculas (Codigo))";
				
				String QueryT2 = "CREATE TABLE " + tabla2 + " (Codigo INT PRIMARY KEY, Nombre NVARCHAR(100), CalificacionEdad INT)";
				
				Statement st = conexion.createStatement();
				st.executeUpdate(QueryT2);
				System.out.println("Se ha creado la tabla " + tabla2);
				
				st.executeUpdate(QueryT1);
				System.out.println("Se ha creado la tabla " + tabla1);
												
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error creando tablas");
			}
		}

		public static void insert(String db, String tabla1, String tabla2) {
			try {
				String Querydb = "USE " + db + ";";
				Statement stdb = conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Insert1T1 = "Insert into " + tabla1 + " (Codigo, Nombre, Pelicula) VALUES ('1', 'Sala1', '1')";
				String Insert2T1 = "Insert into " + tabla1 + " (Codigo, Nombre, Pelicula) VALUES ('2', 'Sala2', '2')";
				String Insert3T1 = "Insert into " + tabla1 + " (Codigo, Nombre, Pelicula) VALUES ('3', 'Sala3', '3')";
				String Insert4T1 = "Insert into " + tabla1 + " (Codigo, Nombre, Pelicula) VALUES ('4', 'Sala4', '4')";
				String Insert5T1 = "Insert into " + tabla1 + " (Codigo, Nombre, Pelicula) VALUES ('5', 'Sala5', '5')";
				
				String Insert1T2 = "Insert into " + tabla2 + " (Codigo, Nombre, CalificacionEdad) VALUES ('1', 'Indiana Jones', '16')";
				String Insert2T2 = "Insert into " + tabla2 + " (Codigo, Nombre, CalificacionEdad) VALUES ('2', 'Star Wars', '18')";
				String Insert3T2 = "Insert into " + tabla2 + " (Codigo, Nombre, CalificacionEdad) VALUES ('3', 'El padrino', '18')";
				String Insert4T2 = "Insert into " + tabla2 + " (Codigo, Nombre, CalificacionEdad) VALUES ('4', 'El mago de Oz', '3')";
				String Insert5T2 = "Insert into " + tabla2 + " (Codigo, Nombre, CalificacionEdad) VALUES ('5', 'Ciudadano Kane', '12')";
				
				Statement st = conexion.createStatement();
				
				st.executeUpdate(Insert1T2);
				st.executeUpdate(Insert2T2);
				st.executeUpdate(Insert3T2);
				st.executeUpdate(Insert4T2);
				st.executeUpdate(Insert5T2);
				
				st.executeUpdate(Insert1T1);
				st.executeUpdate(Insert2T1);
				st.executeUpdate(Insert3T1);
				st.executeUpdate(Insert4T1);
				st.executeUpdate(Insert5T1);
						
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error con els inserts");
			}		
		}
}

