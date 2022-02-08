/**
 * @author Jordi Ribellas Ramos
 * UD18 - Act9
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.MysqlType;

public class ej9App {
	
	private static Connection conexion = null;

	public static void main(String[] args) {
		createDB("Investigadores");
		openConnection();
		createTable("Investigadores", "Facultad", "Reserva", "Equipos", "Investigadores");
		insert("Investigadores", "Facultad", "Reserva", "Equipos", "Investigadores");
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
	
	public static void createTable(String db, String tabla1, String tabla2, String tabla3, String tabla4) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			//Facultad
			String QueryT1 = "CREATE TABLE " + tabla1 + " (Codigo INT PRIMARY KEY, Nombre NVARCHAR(100))";
			
			//Equipos
			String QueryT3 = "CREATE TABLE " + tabla3 + " (NumSerie VARCHAR(4) PRIMARY KEY, Nombre NVARCHAR(100), Facultad INT, "
					+ "KEY Facultad_IDX(Facultad), constraint Facultad FOREIGN KEY (Facultad) references Facultad (Codigo))"; 
				
			//Investigadores
			String QueryT4 = "CREATE TABLE " + tabla4 + " (DNI VARCHAR(9) PRIMARY KEY, NombreApels NVARCHAR(255), Facultad INT, "
					+ "KEY Facultad2_IDX(Facultad), constraint Facultad2 FOREIGN KEY (Facultad) references Facultad (Codigo))"; 
			
			//Reserva
			String QueryT2 = "CREATE TABLE " + tabla2 + " (DNI VARCHAR(9) PRIMARY KEY, NumSerie VARCHAR(4), Comienzo DATE, Fin DATE, KEY DNI_IDX(DNI), KEY NumSerie_IDX(NumSerie), constraint DNI FOREIGN KEY (DNI) references Investigadores (DNI), "
					+ "constraint NumSerie FOREIGN KEY (NumSerie) references Equipos (NumSerie))";
			
			Statement st = conexion.createStatement();
			
			st.executeUpdate(QueryT1);
			System.out.println("Se ha creado la tabla " + tabla1);
			
			st.executeUpdate(QueryT3);
			System.out.println("Se ha creado la tabla " + tabla3);
			
			st.executeUpdate(QueryT4);
			System.out.println("Se ha creado la tabla " + tabla4);
			
			st.executeUpdate(QueryT2);
			System.out.println("Se ha creado la tabla " + tabla2);
											
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
			System.out.println("Error creando tablas");
		}
	}

	public static void insert(String db, String tabla1, String tabla2, String tabla3, String tabla4) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Insert1T1 = "Insert into " + tabla1 + " (Codigo, Nombre) VALUES ('1', 'Facultad1')";
			String Insert2T1 = "Insert into " + tabla1 + " (Codigo, Nombre) VALUES ('2', 'Facultad2')";
			String Insert3T1 = "Insert into " + tabla1 + " (Codigo, Nombre) VALUES ('3', 'Facultad3')";
			String Insert4T1 = "Insert into " + tabla1 + " (Codigo, Nombre) VALUES ('4', 'Facultad4')";
			String Insert5T1 = "Insert into " + tabla1 + " (Codigo, Nombre) VALUES ('5', 'Facultad5')";
			
			String Insert1T3 = "Insert into " + tabla3 + " (NumSerie, Nombre, Facultad) VALUES ('AAA1', 'Team1', '1')";
			String Insert2T3 = "Insert into " + tabla3 + " (NumSerie, Nombre, Facultad) VALUES ('AAA2', 'Team2', '2')";
			String Insert3T3 = "Insert into " + tabla3 + " (NumSerie, Nombre, Facultad) VALUES ('AAA3', 'Team3', '3')";
			String Insert4T3 = "Insert into " + tabla3 + " (NumSerie, Nombre, Facultad) VALUES ('AAA4', 'Team4', '4')";
			String Insert5T3 = "Insert into " + tabla3 + " (NumSerie, Nombre, Facultad) VALUES ('AAA5', 'Team5', '5')";
			
			String Insert1T4 = "Insert into " + tabla4 + " (DNI, NombreApels, Facultad) VALUES ('12345678A', 'Jordi Ribellas', '1')";
			String Insert2T4 = "Insert into " + tabla4 + " (DNI, NombreApels, Facultad) VALUES ('98765432F', 'Joan Rofes', '2')";
			String Insert3T4 = "Insert into " + tabla4 + " (DNI, NombreApels, Facultad) VALUES ('78912345B', 'Adrian Rodriguez', '3')";
			String Insert4T4 = "Insert into " + tabla4 + " (DNI, NombreApels, Facultad) VALUES ('46132578P', 'Eloy Altozano', '4')";
			String Insert5T4 = "Insert into " + tabla4 + " (DNI, NombreApels, Facultad) VALUES ('79468513M', 'Miquel Angel Montero', '5')";
			
			String Insert1T2 = "Insert into " + tabla2 + " (DNI, NumSerie, Comienzo, Fin) VALUES ('12345678A', 'AAA1', '2021-01-01', '2022-01-30')";
			String Insert2T2 = "Insert into " + tabla2 + " (DNI, NumSerie, Comienzo, Fin) VALUES ('98765432F', 'AAA2', '2021-10-14', '2022-01-30')";
			String Insert3T2 = "Insert into " + tabla2 + " (DNI, NumSerie, Comienzo, Fin) VALUES ('78912345B', 'AAA3', '2021-05-27', '2022-01-30')";
			String Insert4T2 = "Insert into " + tabla2 + " (DNI, NumSerie, Comienzo, Fin) VALUES ('46132578P', 'AAA4', '2021-07-20', '2022-01-30')";
			String Insert5T2 = "Insert into " + tabla2 + " (DNI, NumSerie, Comienzo, Fin) VALUES ('79468513M', 'AAA5', '2021-08-10', '2022-01-30')";
			
			Statement st = conexion.createStatement();
			
			st.executeUpdate(Insert1T1);
			st.executeUpdate(Insert2T1);
			st.executeUpdate(Insert3T1);
			st.executeUpdate(Insert4T1);
			st.executeUpdate(Insert5T1);
			
			st.executeUpdate(Insert1T3);
			st.executeUpdate(Insert2T3);
			st.executeUpdate(Insert3T3);
			st.executeUpdate(Insert4T3);
			st.executeUpdate(Insert5T3);
			
			st.executeUpdate(Insert1T4);
			st.executeUpdate(Insert2T4);
			st.executeUpdate(Insert3T4);
			st.executeUpdate(Insert4T4);
			st.executeUpdate(Insert5T4);
			
			st.executeUpdate(Insert1T2);
			st.executeUpdate(Insert2T2);
			st.executeUpdate(Insert3T2);
			st.executeUpdate(Insert4T2);
			st.executeUpdate(Insert5T2);				
			
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
			System.out.println("Error con els inserts");
		}		
	}
}