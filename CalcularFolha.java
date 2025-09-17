package metodos;

import classes.Funcionario;
import enuns.EnumImpostoDeRenda;
import enuns.TaxasInss;
import interfaces.Metodos; 

public class CalcularFolha implements Metodos {

	@Override
	public double calculoSalarioLiquido(double salarioBruto, double descontoInss, double descontoIr) {
		return 0;
	}

	@Override
	public double calculoInss(double salarioBruto) {
		double desconto;

		if (salarioBruto <= TaxasInss.FAIXA1.getValorMaximo()) {
			desconto = salarioBruto * TaxasInss.FAIXA1.getPercentualAliquota() - TaxasInss.FAIXA1.getValorDeducao();

		} else if (salarioBruto <= TaxasInss.FAIXA2.getValorMaximo()) {
			desconto = salarioBruto * TaxasInss.FAIXA2.getPercentualAliquota() - TaxasInss.FAIXA2.getValorDeducao();

		} else if (salarioBruto <= TaxasInss.FAIXA3.getValorMaximo()) {
			desconto = salarioBruto * TaxasInss.FAIXA3.getPercentualAliquota() - TaxasInss.FAIXA3.getValorDeducao();

		} else if (salarioBruto <= TaxasInss.FAIXA4.getValorMaximo()) {
			desconto = salarioBruto * TaxasInss.FAIXA4.getPercentualAliquota() - TaxasInss.FAIXA4.getValorDeducao();

		} else {
			double teto = TaxasInss.FAIXA4.getValorMaximo();
			double aliquota = TaxasInss.FAIXA4.getPercentualAliquota();
			double deducao = TaxasInss.FAIXA4.getValorDeducao();
			desconto = (teto * aliquota) - deducao;
		}
		return Math.round(desconto * 100.0) / 100.0;

	}

	@Override
	public double calculoImpostodeRenda(Funcionario funcionario, double descontoInss) {
		double baseCalculo = funcionario.getSalarioBruto() - descontoInss
				- (funcionario.getDependentes().size() * 189.59);

		double imposto;

		if (baseCalculo <= EnumImpostoDeRenda.ISENTO.getMax()) {
			imposto = 0.0;
		} else if (baseCalculo <= EnumImpostoDeRenda.FAIXA1.getMax()) {
			imposto = (baseCalculo * EnumImpostoDeRenda.FAIXA1.getAliquota())
					- EnumImpostoDeRenda.FAIXA1.getParcelaDeduzir();
		} else if (baseCalculo <= EnumImpostoDeRenda.FAIXA2.getMax()) {
			imposto = (baseCalculo * EnumImpostoDeRenda.FAIXA2.getAliquota())
					- EnumImpostoDeRenda.FAIXA2.getParcelaDeduzir();
		} else if (baseCalculo <= EnumImpostoDeRenda.FAIXA3.getMax()) {
			imposto = (baseCalculo * EnumImpostoDeRenda.FAIXA3.getAliquota())
					- EnumImpostoDeRenda.FAIXA3.getParcelaDeduzir();
		} else {
			imposto = (baseCalculo * EnumImpostoDeRenda.FAIXA4.getAliquota())
					- EnumImpostoDeRenda.FAIXA4.getParcelaDeduzir();
		}

		return Math.round(Math.max(0, imposto) * 100.0) / 100.0;
	}

}
