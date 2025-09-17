package classes;

import java.time.LocalDate;

import enums.Parentesco;

public class Dependente extends Pessoa { 
    
    private Parentesco parentesco;

    public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
        super(nome, cpf, dataNascimento);
        this.parentesco = parentesco;
    }

    public Parentesco getParentesco() { return parentesco; }
    public void setParentesco(Parentesco parentesco) { this.parentesco = parentesco; }
}