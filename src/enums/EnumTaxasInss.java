package enums;

public enum EnumTaxasInss {

    FAIXA1(1518.00, 0.075, 0.0),
    FAIXA2(2793.88, 0.09, 22.77),
    FAIXA3(4190.83, 0.12, 106.59),
    FAIXA4(8157.41, 0.14, 190.40);

    private final double valorMaximo;
    private final double percentualAliquota;
    private final double valorDeducao;

    private EnumTaxasInss(double valorMaximo, double percentualAliquota, double valorDeducao) {
        this.valorMaximo = valorMaximo;
        this.percentualAliquota = percentualAliquota;
        this.valorDeducao = valorDeducao;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public double getPercentualAliquota() {
        return percentualAliquota;
    }

    public double getValorDeducao() {
        return valorDeducao;
    }
}