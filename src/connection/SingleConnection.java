package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * responsavel por fazer a conexão com o DB
 * 
 */
public class SingleConnection {

	private static String url = "jdbc:mysql://localhost/curso_jsp?autoReconnect=true";
	private static String password = "";
	private static String user = "root";
	private static Connection con = null;

	static {
		connect();
	}

	public SingleConnection() {
		connect();
	}

	private static void connect() {
		try {
			if (con == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
				con.setAutoCommit(false);
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}

	public static Connection getConnection() {
		return con;
	}

}
