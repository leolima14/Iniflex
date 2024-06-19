import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

// 2 - Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Funcionario extends Pessoa{
    private static final  BigDecimal SALARIO_MINIMO = BigDecimal.valueOf(1212);
    private BigDecimal salario;
    private String funcao;

    public Funcionario(){}

    public Funcionario(String nome, String dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return  "Nome: " + super.nome + 
                ", Data de Nascimento: " + super.dataNascimento.format(FORMATACAO_DATA) +
                ", Salário: R$" + formatarBigDecimal(salario) +
                ", Função: " + funcao;
    }

    public String getNome() {
        return super.nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public LocalDate getDataNascimento() {
        return super.dataNascimento;
    }

    public void updateSalario(Double porcentos){
        this.salario = this.salario.multiply(new BigDecimal(1 + porcentos/100));
    }

    public static String formatarBigDecimal(BigDecimal valor){
        DecimalFormat formatador = new DecimalFormat("#,###.00");
        return formatador.format(valor);
    }

    public Integer calcularIdade(){
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    public double calcularQuantidadeSalariosMinimos(){
         return this.getSalario().divide(SALARIO_MINIMO, 2).doubleValue();
    }

}
