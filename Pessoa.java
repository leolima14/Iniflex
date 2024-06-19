import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// 1 -  Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
public class Pessoa {
    protected static final DateTimeFormatter FORMATACAO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected String nome;
    protected LocalDate dataNascimento;

    public Pessoa() {
    }

    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = LocalDate.parse(dataNascimento, FORMATACAO_DATA);
    }   
}
