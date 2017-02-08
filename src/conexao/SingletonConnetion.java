package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnetion {

	private static Connection connection = null;

	static {
		conectar();
	}

	public SingletonConnetion() {
		conectar();
	}

	private static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_imagens", "admin", "admin");
				connection.setAutoCommit(false);
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com a base de dados."
					+ e);
		}

	}

	public static Connection getConnection() {
		return connection;
	}

}
