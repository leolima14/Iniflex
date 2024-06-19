import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 3 - Deve conter uma classe Principal para executar as seguintes ações
public class Principal {
    public static String[][] dadosFuncionarios = {
            {"Maria","18/10/2000","2009.44","Operador"},
            {"João","12/05/1990","2284.38","Operador"},
            {"Caio","02/05/1961","9836.14","Coordenador"},
            {"Miguel","14/10/1988","19119.88","Diretor"},
            {"Alice","05/01/1995","2234.68","Recepcionista"},
            {"Heitor","19/11/1999","1582.72","Operador"},
            {"Arthur","31/03/1993","4071.84","Contador"},
            {"Laura","08/07/1994","3017.45","Gerente"},
            {"Heloísa","24/05/2003","1606.85","Eletricista"},
            {"Helena","02/09/1996","2799.93","Gerente"}
    };

    public static void main(String[] args) {

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = new ArrayList<>();

        for(String[] dadosFuncionario: dadosFuncionarios){
            Funcionario funcionario = new Funcionario(dadosFuncionario[0],dadosFuncionario[1],new BigDecimal(dadosFuncionario[2]),dadosFuncionario[3]);
            funcionarios.add(funcionario);

            // Debug para acompanhar inserção dos funcionarios
            //System.out.println("Funcionario adicionado" + funcionario);
        }
        
        //3.2 – Remover o funcionário “João” da lista.

        removerFuncionarioPorNome("João", funcionarios);

        //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        //• informação de data deve ser exibido no formato dd/mm/aaaa;
        //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.

        imprimirListaDeFuncionarios(funcionarios);

        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        for (Funcionario f : funcionarios){
            f.updateSalario(10.0);
        }

        // Debug para verificar se aumento funcionou
        // imprimirListaDeFuncionarios(funcionarios);
        
        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        Map<String, List<Funcionario>> funcionariosPorFuncaoMap = new HashMap<>();

        for(Funcionario funcionario: funcionarios){
            String funcao = funcionario.getFuncao();
            if(!funcionariosPorFuncaoMap.containsKey(funcao)){
                funcionariosPorFuncaoMap.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncaoMap.get(funcao).add(funcionario);
        }

        //3.6 - Imprimir os funcionários, agrupados por função.

        for (Map.Entry<String, List<Funcionario>> entry: funcionariosPorFuncaoMap.entrySet()){
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario: entry.getValue()){
                System.out.println("\t" + funcionario);
            }
        }
        
        //3.7 null

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        for(Funcionario funcionario: funcionarios){
            Month mesAniversario = funcionario.getDataNascimento().getMonth();
            if(mesAniversario.equals(Month.OCTOBER) || mesAniversario.equals(Month.DECEMBER)){
                System.out.println(funcionario);
            }
        }

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario funcMaisVelho = new Funcionario();
        LocalDate dataMaisVelho = LocalDate.parse("31/12/9999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        for (Funcionario f : funcionarios) {
            LocalDate dNasc = f.getDataNascimento();
            if (dNasc.isBefore(dataMaisVelho)) {
                dataMaisVelho = f.getDataNascimento();
                funcMaisVelho = f;
            }
        }
        System.out.println("Nome: " + funcMaisVelho.getNome() + "\n" + "Idade: " + funcMaisVelho.calcularIdade());

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        List<Funcionario> funcionariosOrdemAlfabetica = new ArrayList<>();
        for (Funcionario f: funcionarios)
            funcionariosOrdemAlfabetica.add(f);
        funcionariosOrdemAlfabetica.sort(Comparator.comparing(Funcionario::getNome));
        imprimirListaDeFuncionarios(funcionariosOrdemAlfabetica);

        //3.11 – Imprimir o total dos salários dos funcionários.

        BigDecimal total = BigDecimal.ZERO;
        for(Funcionario funcionario: funcionarios){
            total = total.add(funcionario.getSalario());
        }

        System.out.println("\nO valor total do salários dos funcionários é de: R$" + Funcionario.formatarBigDecimal(total) + "\n");

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

        for (Funcionario funcionario: funcionarios){
            System.out.printf("Nome: %s , Salários Mínimos: %.2f\n", funcionario.getNome(), funcionario.calcularQuantidadeSalariosMinimos());
        }

    }

    public static void imprimirListaDeFuncionarios(List<Funcionario> func){
        func.forEach(System.out::println);
    }
    public static void removerFuncionarioPorNome(String nome, List<Funcionario> func){
        func.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }
}
