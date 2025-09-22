package classes;

import exceptions.DependenteException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
	private int id;
	private double salarioBruto;
	private double descontoInss;
	private double descontoIr;
	private List<Dependente> dependentes;

	public Funcionario(String nome, String cpf, LocalDate dataNasc, double salarioBruto)
			throws DependenteException {
		super(nome, cpf, dataNasc);
		this.salarioBruto = salarioBruto;
		this.dependentes = new ArrayList<>();
	}

	public void adicionarDependente(Dependente d) throws DependenteException {
		boolean cpfJaExiste = dependentes.stream().anyMatch(dep -> dep.getCpf().equals(d.getCpf()));
		if (cpfJaExiste) {
			throw new DependenteException("CPF de dependente duplicado: " + d.getCpf());
		}
		dependentes.add(d);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getDescontoIr() {
		return descontoIr;
	}

	public void setDescontoIr(double descontoIr) {
		this.descontoIr = descontoIr;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
}