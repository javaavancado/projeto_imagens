package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe responsável por controlar , criar e retornar a conexão com o banco de dados
 * @author alex
 *
 */
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
				Class.forName("com.mysql.jdbc.Driver"); // driver mysql
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_imagens", "admin", "admin");// url do banco de dados com user e senha
				connection.setAutoCommit(false);// não dar commit automatico
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); // tipo da transação no banco de dados
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com a base de dados."
					+ e);
		}

	}

	/**
	 * Retorna a conexão com o banco de dados
	 * @return Connection
	 */
	public static Connection getConnection() {
		return connection;
	}

}
