package enums;

public enum EnumImpostoDeRenda  {
	ISENTO(0.00, 2259.00, 0.0, 0.0), 
	FAIXA1(2259.21, 2826.65, 0.075, 169.44), 
	FAIXA2(2826.66, 3751.05, 0.15, 381.44),
	FAIXA3(3751.06, 4664.68, 0.225, 662.77), 
	FAIXA4(4664.68, Double.MAX_VALUE, 0.275, 896.00);

	private final double min;
	private final double max;
	private final double aliquota;
	private final double parcelaDeduzir;

	private EnumImpostoDeRenda(double min, double max, double aliquota, double parcelaDeduzir) {
		this.min = min;
		this.max = max;
		this.aliquota = aliquota;
		this.parcelaDeduzir = parcelaDeduzir;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public double getAliquota() {
		return aliquota;
	}

	public double getParcelaDeduzir() {
		return parcelaDeduzir;
	}

}