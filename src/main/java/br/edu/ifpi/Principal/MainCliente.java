package br.edu.ifpi.Principal;

import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.Model.*;
import java.util.Scanner;

public class MainCliente {

    static ClienteDAO clienteDAO = new ClienteDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
    }

    public static void executar(Scanner scanner) {
        Cliente cliente = null;

        while (cliente == null) {
            System.out.println("=== ACESSO DO CLIENTE ===");
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            cliente = clienteDAO.login(cpf, senha);

            if (cliente == null) {
                System.out.println("CPF ou senha invalidos. Tente novamente.\n");
            }
        }

        System.out.println("Bem-vindo(a), " + cliente.getNome() + "!");

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== MENU DO CLIENTE ===");
            System.out.println("1. Cadastrar animal");
            System.out.println("2. Meus animais");
            System.out.println("3. Agendar servico");
            System.out.println("4. Meus dados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAnimal(scanner, cliente);
                    break;
                case 2:
                    listarAnimais(cliente);
                    break;
                case 3:
                    agendarServico(scanner, cliente);
                    break;
                case 4:
                    exibirDados(cliente);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    static void cadastrarAnimal(Scanner scanner, Cliente cliente) {
        System.out.print("Tipo do animal (1 - Cachorro / 2 - Gato): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Animal animal;
        if (tipo == 1) {
            animal = new Cachorro();
        } else if (tipo == 2) {
            animal = new Gato();
        } else {
            System.out.println("Tipo invalido.");
            return;
        }

        System.out.print("Nome do animal: ");
        animal.setNome(scanner.nextLine());
        System.out.print("Idade: ");
        animal.setIdade(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Raca: ");
        animal.setRaca(scanner.nextLine());

        cliente.adicionarAnimal(animal);
        clienteDAO.atualizar(cliente);
        System.out.println("Animal cadastrado com sucesso!");
    }

    static void listarAnimais(Cliente cliente) {
        if (cliente.getAnimais().isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
            return;
        }
        System.out.println("\n=== ANIMAIS ===");
        for (Animal a : cliente.getAnimais()) {
            String tipo = a instanceof Cachorro ? "Cachorro" : "Gato";
            System.out.println("- " + a.getNome() + " (" + tipo + ", " + a.getRaca() + ", " + a.getIdade() + " ano(s))");
        }
    }

    static void agendarServico(Scanner scanner, Cliente cliente) {
        if (cliente.getAnimais().isEmpty()) {
            System.out.println("Cadastre um animal primeiro.");
            return;
        }

        listarAnimais(cliente);
        System.out.print("Escolha o animal (digite o nome): ");
        String nomeAnimal = scanner.nextLine();

        Animal animalEscolhido = null;
        for (Animal a : cliente.getAnimais()) {
            if (a.getNome().equalsIgnoreCase(nomeAnimal)) {
                animalEscolhido = a;
                break;
            }
        }

        if (animalEscolhido == null) {
            System.out.println("Animal nao encontrado.");
            return;
        }

        System.out.print("Tipo de servico (banho / tosa): ");
        String tipoServico = scanner.nextLine().toLowerCase();

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setAnimal(animalEscolhido);

        try {
            agendamento.decidirServico(tipoServico);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Data do agendamento (dd/MM/yyyy): ");
        agendamento.setData(scanner.nextLine());

        agendamento.agendarServico();
        System.out.println("Agendamento realizado com sucesso!");

        if (animalEscolhido instanceof Cachorro) {
            ((Cachorro) animalEscolhido).emitirSom();
        } else if (animalEscolhido instanceof Gato) {
            ((Gato) animalEscolhido).emitirSom();
        }
    }

    static void exibirDados(Cliente cliente) {
        System.out.println("\n=== MEUS DADOS ===");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Endereco: " + cliente.getEndereco());
    }
}
