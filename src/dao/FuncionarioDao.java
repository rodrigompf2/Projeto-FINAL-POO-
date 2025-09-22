package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import classes.Funcionario;
import connection.ConnectionFactory;

public class FuncionarioDao {
	private Connection connection;

	public FuncionarioDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public int inserir(Funcionario funcionario) {
		String sql = "insert into funcionario(nome,cpf,dataNasc,salarioBruto) values(?,?,?,?)";
		int funcionarioId = -1;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, Date.valueOf(funcionario.getDataNasc()));
			stmt.setDouble(4, funcionario.getSalarioBruto());
			
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				funcionarioId = rs.getInt(1);
			}
			
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problemas ao gravar registro!");
		}
		return funcionarioId;
	}
}