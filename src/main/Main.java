package main;

import arquivos.EntradaFun;
import classes.Dependente;
import classes.FolhaPagamento;
import classes.Funcionario;
import connection.ConnectionFactory;
import dao.DependenteDao;
import dao.FolhaPagamentoDao;
import dao.FuncionarioDao;
import exceptions.DependenteException;
import metodos.CalcularFolha;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        limparTabelas();
        
        String arquivoEntrada = "C:/trabalhofinal/funcionario.csv";

        try {
            List<Funcionario> funcionarios = EntradaFun.lerArquivoEntrada(arquivoEntrada);

            FuncionarioDao funcDao = new FuncionarioDao();
            DependenteDao depDao = new DependenteDao();
            FolhaPagamentoDao folhaDao = new FolhaPagamentoDao();
            CalcularFolha calcular = new CalcularFolha();

            for (Funcionario f : funcionarios) {
                int funcionarioId = funcDao.inserir(f);
                f.setId(funcionarioId);

                if (f.getDependentes() != null) {
                    for (Dependente d : f.getDependentes()) {
                        d.setId_funcionario(f.getId());
                        depDao.inserir(d);
                    }
                }

                double inss = calcular.calculoInss(f.getSalarioBruto());
                double ir = calcular.calculoImpostodeRenda(f, inss);
                double liquido = calcular.calculoSalarioLiquido(f.getSalarioBruto(), inss, ir);

                FolhaPagamento folha = new FolhaPagamento(
                        0, f, LocalDate.now(), inss, ir, liquido
                );

                folhaDao.inserir(folha);
                
                System.out.println(folha);
            }

            System.out.println("Processamento concluído com sucesso!");

        } catch (DependenteException e) {
            System.out.println("Erro em dependente: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void limparTabelas() {
        System.out.println("Limpando tabelas...");
        try (Connection conn = new ConnectionFactory().getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate("TRUNCATE TABLE folha_pagamento RESTART IDENTITY;");
            stmt.executeUpdate("TRUNCATE TABLE dependente RESTART IDENTITY;");
            stmt.executeUpdate("TRUNCATE TABLE funcionario RESTART IDENTITY CASCADE;");

            System.out.println("Tabelas limpas com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao limpar as tabelas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}