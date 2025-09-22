package connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ImportarCsv {
    public static void main(String[] args) {
        String csvFile = "C:/trabalhofinal/funcionario.csv";
        String line;
        String splitBy = ";";

        String url = "jdbc:postgresql://localhost:5432/poo";
        String user = "postgres";
        String password = "142536";

        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyyMMdd")
        };

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             Connection conn = DriverManager.getConnection(url, user, password)) {

            String sqlFuncionario = "insert into funcionario(nome,cpf,dataNasc,salarioBruto) values (?,?,?,?) returning id";
            String sqlDependente = "insert into dependente(nome,cpf,dataNasc,parentesco,id_funcionario) values (?,?,?,?,?)";

            PreparedStatement stmtFunc = conn.prepareStatement(sqlFuncionario);
            PreparedStatement stmtDep = conn.prepareStatement(sqlDependente);

            int currentFuncionarioId = -1;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] dados = line.split(splitBy);
                
                LocalDate localDate = null;
                
                for (DateTimeFormatter formatter : formatters) {
                    try {
                        localDate = LocalDate.parse(dados[2], formatter);
                        break;
                    } catch (DateTimeParseException e) {
                    }
                }
                
                if (localDate == null) {
                    throw new IllegalArgumentException("Formato de data inválido para: " + dados[2]);
                }

                if (dados.length == 4 && dados[3].matches("\\d+(\\.\\d+)?")) {
                    String nome = dados[0];
                    String cpf = dados[1].replaceAll("[^0-9]", "");
                    double salarioBruto = Double.parseDouble(dados[3]);

                    stmtFunc.setString(1, nome);
                    stmtFunc.setString(2, cpf);
                    stmtFunc.setDate(3, Date.valueOf(localDate));
                    stmtFunc.setDouble(4, salarioBruto);

                    ResultSet rs = stmtFunc.executeQuery();
                    if (rs.next()) {
                        currentFuncionarioId = rs.getInt("id");
                    }
                    rs.close();
                } else if (dados.length == 4) {
                    String nome = dados[0];
                    String cpf = dados[1].replaceAll("[^0-9]", "");
                    String parentesco = dados[3];

                    stmtDep.setString(1, nome);
                    stmtDep.setString(2, cpf);
                    stmtDep.setDate(3, Date.valueOf(localDate));
                    stmtDep.setString(4, parentesco);
                    stmtDep.setInt(5, currentFuncionarioId);

                    stmtDep.executeUpdate();
                }
            }

            stmtFunc.close();
            stmtDep.close();
            System.out.println("Importação concluída com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}