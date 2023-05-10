import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.crypto.dsig.spec.XPathType.Filter;

//import org.jcp.xml.dsig.internal.SignerOutputStream;

public class App {

    public static void main(String[] args) {
         List<Pessoa> lista = Arrays.asList(
                new Pessoa(1, "Huguinho", Departamento.FINANCEIRO, 40),
                new Pessoa(4, "Zezinho", Departamento.FINANCEIRO, 32),
                new Pessoa(3, "Luizinho", Departamento.VENDAS, 57),
                new  Pessoa(9, "Patinhas",   Departamento.VENDAS, 40),
                new Pessoa(10, "Donald", Departamento.GERENCIA, 54),
                new Pessoa(2, "Margarida", Departamento.FINANCEIRO, 40),
                new Pessoa(8, "Joe Doe", Departamento.VENDAS, 34),
                new Pessoa(5, "Jane Doe", Departamento.VENDAS, 22),
                new Pessoa(6, "Sr Smith", Departamento.VENDAS, 40),
                new Pessoa(7, "Sra Smith", Departamento.GERENCIA, 39),
                 new Pessoa(11, "Trinity", Departamento.VENDAS, 34),
                 new Pessoa(14, "Morpheus", Departamento.FINANCEIRO, 22),
                 new Pessoa(16, "AgenteSmith", Departamento.VENDAS, 47),
                 new Pessoa(13, "Neo", Departamento.GERENCIA, 29)
        );

        /***
         * Exercício
         *      - escreva as consultas solicitadas utilizando
         *        apenas expressões lambda e operações de agregação
          */

        System.out.println("\n 1. Funcionários do setor de vendas:");
        List <Pessoa> dept = lista.stream()
        .filter(p -> p.getDpto() == Departamento.VENDAS)
        .collect(Collectors.toList());

        dept.forEach( System.out::println );
        System.out.println("________________________________________________________________");

        System.out.println("\n 2. Funcionários do setor de vendas com idade entre 20 e 30 anos");
        List <Pessoa> deptIdade = lista.stream()
        .filter(p -> p.getDpto() == Departamento.VENDAS && p.getIdade() >= 20 && p.getIdade() <= 30)
        .collect(Collectors.toList());

        deptIdade.forEach(System.out::println);
        System.out.println("________________________________________________________________");
        
        System.out.println("\n 3. Nomes (em maiúsculas) dos funcionários do setor de vendas (usando reduce");

        List<Pessoa> nome = lista.stream()
        .filter(p -> p.getDpto() == Departamento.VENDAS)
        .collect(Collectors.toList());

        nome.forEach( p->System.out.printf("-> %s\n",p.getNome().toUpperCase()));
        System.out.println("________________________________________________________________");


        System.out.println("\n 4. Todos os gerentes:");

        List <Pessoa> gerente = lista.stream()
        .filter(p -> p.getDpto() == Departamento.GERENCIA)
        .collect(Collectors.toList());

        gerente.forEach( System.out::println );
        System.out.println("________________________________________________________________");

        System.out.println("\n 5. Idade média dos gerentes:");
        double idadeGer= lista.stream()
        .filter(p -> p.getDpto() == Departamento.GERENCIA)
        .mapToInt(p-> p.getIdade()).average().getAsDouble();

       
        System.out.printf("\t %.2f" ,idadeGer); 
        System.out.println(" ");
        
        //.collect(Collectors.toList());
        System.out.println("________________________________________________________________");



        System.out.println("\n 6. Funcionarios ordenados pelo código:");
        lista.stream()
        .sorted(Comparator.comparing( Pessoa::getCodigo )).
        forEach(System.out::println);

        System.out.println("________________________________________________________________");

        System.out.println("\n 7. Funcionários ordenados pela idade+nome:");
        lista.stream()
        .sorted(Comparator.comparing( Pessoa::getIdade )
                          .thenComparing( Pessoa::getNome ))
        .forEach(System.out::println);



        System.out.println("________________________________________________________________");

        System.out.println("\n 8. Criar uma nova lista apenas com os funcionarios do financeiro:");
        List<Pessoa> financ = lista.stream()
        .filter(p -> p .getDpto() == Departamento.FINANCEIRO)
        .collect(Collectors.toList());
        financ.forEach( System.out::println );
        System.out.println("________________________________________________________________");

        System.out.println("\n 9. Nome e setor da pessoa mais jovem:");
        lista.stream().sorted(Comparator.comparing(Pessoa::getIdade))
        .limit(1)
        .map(p->p.getNome() +" - " + p.getDpto())
        .forEach(System.out::println);
        
        


    }
}
