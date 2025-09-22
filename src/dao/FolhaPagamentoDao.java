package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import classes.FolhaPagamento;
import connection.ConnectionFactory;

public class FolhaPagamentoDao {
    private Connection connection;

    public FolhaPagamentoDao() {
        connection = new ConnectionFactory().getConnection();
    }

    public void inserir(FolhaPagamento folhapagamento) {
        String sql = "insert into folha_pagamento(inss,ir,salarioLiquido,id_funcionario,data) values(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, folhapagamento.getDescontoINSS());
            stmt.setDouble(2, folhapagamento.getDescontoIR());
            stmt.setDouble(3, folhapagamento.getSalarioLiquido());
            stmt.setInt(4, folhapagamento.getFuncionario().getId());
            stmt.setDate(5, Date.valueOf(folhapagamento.getDataPagamento()));

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problemas ao gravar registro!");
        }
    }
}