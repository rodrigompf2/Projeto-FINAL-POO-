package classes;

import java.time.LocalDate;
import java.util.List;

public class Funcionario extends Pessoa {
    private double salarioBruto;
    private double descontoInss;
    private double descontoIr;
    private List<Dependente> dependentes;

    public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto, List<Dependente> dependentes) {
        super(nome, cpf, dataNascimento);
        this.salarioBruto = salarioBruto;
        this.dependentes = dependentes;
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