package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excecao.DependenteException;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private List<Dependente> dependentes = new ArrayList<>();
	
	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
			}
	
	public void adicionarDependente(Dependente d) throws DependenteException{
		for (Dependente dependente : this.dependentes) {
			if (dependente.getCpf().equals(d)) {
				throw new DependenteException("CPF Duplicado!");
			}
		}
		this.dependentes.add(d); 
	}
	public List<Dependente> getDependente() {
		return dependentes;
	}
	public double getSalarioBruto() {
		return salarioBruto;
	}
	

}
//ver sobre pool e quest-