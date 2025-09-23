package classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FolhaPagamento {

    private int codigo;
    private Funcionario funcionario;
    private LocalDate dataPagamento;
    private double descontoINSS;
    private double descontoIR;
    private double salarioLiquido;

    public FolhaPagamento(int codigo, Funcionario funcionario, LocalDate dataPagamento,
                          double descontoINSS, double descontoIR, double salarioLiquido) {
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.dataPagamento = dataPagamento;
        this.descontoINSS = descontoINSS;
        this.descontoIR = descontoIR;
        this.salarioLiquido = salarioLiquido;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        		return  "============================\n"  +
        				"Folha Pagamento\n"  +
        				"============================\n"  +
                        "Data de Pagamento: " + this.dataPagamento.format(formatador) + "\n" +
                        "Funcionário: " + this.funcionario.getNome() + " (CPF: " + this.funcionario.getCpf() + ")\n" +
                        "---------------------------------------\n" +
                        String.format(Locale.forLanguageTag("pt-BR"), "Salário Bruto:   R$ %.2f\n", this.funcionario.getSalarioBruto()) +
                        String.format(Locale.forLanguageTag("pt-BR"), "(-) Desconto INSS: R$ %.2f\n", this.descontoINSS) +
                        String.format(Locale.forLanguageTag("pt-BR"), "(-) Desconto IR:   R$ %.2f\n", this.descontoIR) +
                        "---------------------------------------\n" +
                        String.format(Locale.forLanguageTag("pt-BR"), "Salário Líquido: R$ %.2f\n", this.salarioLiquido) +
                        "\n";
    }

	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getDescontoINSS() {
        return descontoINSS;
    }

    public void setDescontoINSS(double descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    public double getDescontoIR() {
        return descontoIR;
    }

    public void setDescontoIR(double descontoIR) {
        this.descontoIR = descontoIR;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }
}