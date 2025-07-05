package src.models;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Cliente cadastro = new Cliente();
        int opcao;

        do {
            System.out.println("===== MUSIC STORE =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Exibir Dados Cadastrais");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Listar Clientes");
            System.out.println("5 - Alterar Dados Cadastrais");
            System.out.println("0 - Sair");
            System.out.println("=======================");
            System.out.print("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastro de Cliente");
                    System.out.print("Digite o nome: ");
                    String nome = leitor.nextLine();
                    System.out.print("Digite o email: ");
                    String email = leitor.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = leitor.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefone = leitor.nextLine();
                    cadastro.cadastrarCliente(nome, email, senha, telefone);                    
                    break;

                case 2:
                    System.out.print("Digite o ID do cliente: ");
                    int id = leitor.nextInt();
                    cadastro.exibirDadosCadastrais(id);
                    break;
                case 3:
                    System.out.print("Digite o ID do cliente a ser excluído: ");
                    int idExcluir = leitor.nextInt();
                    cadastro.removerCliente(idExcluir);
                    break;

                case 4:
                    System.out.println("Lista de Clientes:");
                    cadastro.consultarTodosClientes();
                    break;
                case 5:
                    System.out.print("Digite o ID do cliente a ser alterado: ");
                    int idAlterar = leitor.nextInt();
                    leitor.nextLine(); 

            
                    System.out.print("Digite o novo nome: ");
                    String novoNome = leitor.nextLine();
                    System.out.print("Digite o novo email: ");
                    String novoEmail = leitor.nextLine();
                    System.out.print("Digite a nova senha: ");
                    String novaSenha = leitor.nextLine();
                    System.out.print("Digite o novo telefone: ");
                    String novoTelefone = leitor.nextLine();
                    cadastro.alterarCadastro(idAlterar, novoNome, novoEmail, novaSenha, novoTelefone);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        leitor.close();

    }
}
