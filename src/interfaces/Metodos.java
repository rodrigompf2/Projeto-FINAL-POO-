package interfaces;

import classes.Funcionario;

public interface Metodos {
	public double calculoSalarioLiquido (double salarioBruto, double descontoInss, double descontoIr);
	public double calculoInss (double salarioBruto);
	public double calculoImpostodeRenda(Funcionario funcionario, double descontoInss);
}
