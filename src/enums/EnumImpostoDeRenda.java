package enums;

public enum EnumImpostoDeRenda {
    ISENTO(2259.20, 0.0, 0.0),
    FAIXA1(2826.65, 0.075, 169.44),
    FAIXA2(3751.05, 0.15, 381.44),
    FAIXA3(4664.68, 0.225, 662.77),
    FAIXA4(Double.MAX_VALUE, 0.275, 896.00);

    private final double max;
    private final double aliquota;
    private final double parcelaDeduzir;

    private EnumImpostoDeRenda(double max, double aliquota, double parcelaDeduzir) {
        this.max = max;
        this.aliquota = aliquota;
        this.parcelaDeduzir = parcelaDeduzir;
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