package interfaces;

import classes.Funcionario;

public interface Metodos {
	double calculoSalarioLiquido(double salarioBruto, double descontoInss, double descontoIr);
	double calculoInss(double salarioBruto);
	double calculoImpostodeRenda(Funcionario funcionario, double descontoInss);
	
}
