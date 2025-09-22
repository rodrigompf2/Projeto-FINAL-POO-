package arquivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import classes.Dependente;
import classes.Funcionario;
import enums.EnumParentesco;
import exceptions.DependenteException;
import metodos.CalcularFolha;

public class EntradaFun {

	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static List<Funcionario> lerArquivoEntrada(String nomeArqEntrada) throws IOException, DependenteException {
		List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario atual = null;

		try (Scanner in = new Scanner(
				new InputStreamReader(new FileInputStream(nomeArqEntrada), StandardCharsets.UTF_8))) {
			while (in.hasNextLine()) {
				String line = in.nextLine().trim();

				if (line.isEmpty()) {
					if (atual != null) {
						funcionarios.add(atual);
						atual = null;
					}
					continue;
				}

				String[] dados = line.split(";", -1);
				
				for (int i = 0; i < dados.length; i++) {
					dados[i] = dados[i].trim();
				}

				if (atual == null) {
					String nome = dados[0];
					String cpf = dados[1];
					LocalDate data = LocalDate.parse(dados[2], DF);
					double salario = Double.parseDouble(dados[3]);
					atual = new Funcionario(nome, cpf, data, salario);

				} else {
					String nome = dados[0];
					String cpf = dados[1];
					LocalDate data = LocalDate.parse(dados[2], DF);
					EnumParentesco parentesco = EnumParentesco.valueOf(dados[3].toUpperCase());

					Dependente dependente = new Dependente(nome, cpf, data, parentesco);
					atual.adicionarDependente(dependente);
				}
			}
		}

		if (atual != null) {
			funcionarios.add(atual);
		}
		return funcionarios;
	}

	public static void escreverArquivoSaida(Set<Funcionario> funcionarios, String nomeArqSaida) throws IOException {
		CalcularFolha calcularFolha = new CalcularFolha();
		try (PrintWriter out = new PrintWriter(
				new OutputStreamWriter(new FileOutputStream(nomeArqSaida), StandardCharsets.UTF_8))) {
			for (Funcionario f : funcionarios) {
				double inss = calcularFolha.calculoInss(f.getSalarioBruto());
				double ir = calcularFolha.calculoImpostodeRenda(f, inss);
				double liquido = calcularFolha.calculoSalarioLiquido(f.getSalarioBruto(), inss, ir);

				String linha = String.join(";", f.getNome(), f.getCpf(), String.format(Locale.US, "%.2f", inss),
						String.format(Locale.US, "%.2f", ir), String.format(Locale.US, "%.2f", liquido));
				out.println(linha);
			}
		}
	}
}