package br.edu.ifpi.Principal;

import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.DAO.FuncionarioDAO;
import br.edu.ifpi.Model.Cliente;
import br.edu.ifpi.Model.Funcionario;
import java.util.Scanner;

public class Main {

    static ClienteDAO clienteDAO = new ClienteDAO();
    static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== SISTEMA PETSHOP ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Funcionario");
            System.out.println("3. Pesquisar Cliente por CPF");
            System.out.println("4. Pesquisar Funcionario por CPF");
            System.out.println("5. Acessar area do cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    cadastrarFuncionario(scanner);
                    break;
                case 3:
                    pesquisarCliente(scanner);
                    break;
                case 4:
                    pesquisarFuncionario(scanner);
                    break;
                case 5:
                    MainCliente.executar(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
        scanner.close();
    }

    static void cadastrarCliente(Scanner scanner) {
        Cliente c = new Cliente();
        System.out.print("Nome: ");
        c.setNome(scanner.nextLine());
        System.out.print("CPF: ");
        c.setCpf(scanner.nextLine());
        System.out.print("Telefone: ");
        c.setTelefone(scanner.nextLine());
        System.out.print("Senha: ");
        c.setSenha(scanner.nextLine());
        System.out.print("Endereco: ");
        c.setEndereco(scanner.nextLine());
        clienteDAO.salvar(c);
        System.out.println("Cliente salvo com sucesso!");
    }

    static void cadastrarFuncionario(Scanner scanner) {
        Funcionario f = new Funcionario();
        System.out.print("Nome: ");
        f.setNome(scanner.nextLine());
        System.out.print("CPF: ");
        f.setCpf(scanner.nextLine());
        System.out.print("Telefone: ");
        f.setTelefone(scanner.nextLine());
        System.out.print("Cargo: ");
        f.setCargo(scanner.nextLine());
        System.out.print("Salario: ");
        f.setSalario(scanner.nextDouble());
        scanner.nextLine();
        funcionarioDAO.salvar(f);
        System.out.println("Funcionario salvo com sucesso!");
    }

    static void pesquisarCliente(Scanner scanner) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        Cliente c = clienteDAO.buscarPorCpf(cpf);
        if (c != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("  Nome: " + c.getNome());
            System.out.println("  CPF: " + c.getCpf());
            System.out.println("  Telefone: " + c.getTelefone());
            System.out.println("  Endereco: " + c.getEndereco());
        } else {
            System.out.println("Cliente nao encontrado para o CPF: " + cpf);
        }
    }

    static void pesquisarFuncionario(Scanner scanner) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        Funcionario f = funcionarioDAO.buscarPorCpf(cpf);
        if (f != null) {
            System.out.println("Funcionario encontrado:");
            System.out.println("  Nome: " + f.getNome());
            System.out.println("  CPF: " + f.getCpf());
            System.out.println("  Telefone: " + f.getTelefone());
            System.out.println("  Cargo: " + f.getCargo());
            System.out.println("  Salario: " + f.getSalario());
        } else {
            System.out.println("Funcionario nao encontrado para o CPF: " + cpf);
        }
    }
}
