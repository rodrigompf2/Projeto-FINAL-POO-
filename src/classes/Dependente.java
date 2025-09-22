package classes;

import java.time.LocalDate;
import java.time.Period;

import enums.EnumParentesco;
import exceptions.DependenteException;

public class Dependente extends Pessoa { 
    private int id_funcionario;
    private EnumParentesco parentesco;

    public Dependente(String nome, String cpf, LocalDate dataNasc, EnumParentesco parentesco) throws DependenteException {
        super(nome, cpf, dataNasc);
        this.parentesco = parentesco;
        if (Period.between(dataNasc, LocalDate.now()).getYears() >= 18) {
            throw new DependenteException("Dependente deve ter menos de 18 anos.");
        }
    
    }

    public EnumParentesco getParentesco() { return parentesco; }
    public void setParentesco(EnumParentesco parentesco) { this.parentesco = parentesco; }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
    
    
    
}