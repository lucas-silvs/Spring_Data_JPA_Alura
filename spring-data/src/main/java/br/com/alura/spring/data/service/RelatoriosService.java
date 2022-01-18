package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;


    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - busca funcionario por nome");
            System.out.println("2 - buscar usuarios por salario, data de contratação e salario");
            System.out.println("3 - buscar por data de contratação");
            System.out.println("4 - pesquisa por salário de funcionário");


            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaUsuariosPeloNome(scanner);
                    break;
                case 2:
                    buscFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioPorDataDeContratacao(scanner);
                    break;
                case 4:
                    pesquisaPorFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }

        }

    }

    private void buscaUsuariosPeloNome(Scanner scanner) {
        System.out.println("Digite o nome do funcionário que deseja buscar");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);

        funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));


    }

    private void  buscFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();

        System.out.println("Qual data de contratacao deseja pesquisar");
        String data = scanner.next();

        LocalDate dataeHora = LocalDate.parse(data,formatter);
        System.out.println("qual salario deseja pesquisar");
        Double salario = scanner.nextDouble();

        List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome,salario,dataeHora);

        funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));


    }

    private void buscaFuncionarioPorDataDeContratacao(Scanner scanner){

        System.out.println("Qual data de contratacao deseja pesquisar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data,formatter);
        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);

        funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));


    }

    private void pesquisaPorFuncionarioSalario(){
        List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
        lista.forEach(funcionarioProjecao -> System.out.println("Funcionario: id - "+ funcionarioProjecao.getId() + " nome - "+funcionarioProjecao.getNome()+" salario - "+ funcionarioProjecao.getSalario()));
    }


}
