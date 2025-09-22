package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String url = "jdbc:postgresql://localhost:5432/poo";
	private String usuario = "postgres";
	private String senha = "142536";
	private Connection connection;

	public Connection getConnection() {
		System.out.println("Conectando ao banco....");
		try {
			connection = DriverManager.getConnection(url, usuario, senha);
			if (connection != null) {
				System.out.println("Conectado com sucesso!");
			}
		} catch (SQLException e) {
			System.out.println("não foi possivel acessar!");
		}
		return connection;
	}

}
