package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import classes.Dependente;
import connection.ConnectionFactory;

public class DependenteDao {
    private Connection connection;

    public DependenteDao() {
        connection = new ConnectionFactory().getConnection();
    }

    public void inserir(Dependente dependente) {
        String sql = "insert into dependente(nome,cpf,dataNasc,parentesco,id_funcionario) values(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, dependente.getNome());
            stmt.setString(2, dependente.getCpf());
            stmt.setDate(3, Date.valueOf(dependente.getDataNasc()));
            stmt.setString(4, dependente.getParentesco().name());
            stmt.setInt(5, dependente.getId_funcionario());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Problemas ao gravar registro!");
            e.printStackTrace();
        }
    }
}