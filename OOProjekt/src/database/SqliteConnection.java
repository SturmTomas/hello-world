package database;
import gui.*;
import main.*;
import controller.*;
import java.sql.*;


public class SqliteConnection {

		public static Connection connect() throws SQLException, ClassNotFoundException {
			
			try {			
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Tom Burka\\qlite-tools-win32-x86-3270200\\testDatabase\\testdatabase.db");
							
		return conn;
	} catch (Exception e) {
		
		System.out.println(e);
		return null;
		}
	}	
}
