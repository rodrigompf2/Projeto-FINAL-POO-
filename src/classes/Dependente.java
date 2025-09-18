package classes;

import excecao.DependenteException;
import java.time.LocalDate;
import java.time.Period;

import enums.Parentesco;

public class Dependente extends Pessoa {
	private Parentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) throws DependenteException {
		super(nome, cpf, dataNascimento);
		if (Period.between(dataNascimento, LocalDate.now()).getYears() >=18) {
			throw new DependenteException("Dependente deve ter menos de 18 anos.");
		}
		this.parentesco = parentesco;
	}
	
	
	public Parentesco getParentesco() {
		return parentesco;
	}
	
	
}
