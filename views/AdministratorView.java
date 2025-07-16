package views;

import java.util.Scanner;

import src.models.Cliente;
import src.models.Produto;
import src.utils.Tratativas;

public class AdministratorView {
      public static void executarMenuAdministrator(Scanner scanner, Cliente cliente) {
        int opcao;
        do {
            MenuUtils.exibirMenuAdministrador();
            System.out.print("Escolha uma opção: ");
            opcao = Tratativas.lerInteiro();

            switch (opcao) {
                case 1:
                    executarMenuProduto(scanner);
                    break;
                case 2:
                    // Menu de vendas ainda será implementado
                    break;
                case 3:
                System.out.println("=== Cadastro de Cliente ===");
                System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    String email;

                    while (true) {
                        System.out.print("Digite o email: ");
                        email = scanner.nextLine();
                        if (Tratativas.validarEmail(email)) {
                            break;
                        } else {
                            System.out.println("Email inválido! Tente novamente.");
                        }
                    }

                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();

                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();

                    Cliente.cadastrarCliente(nome, email, senha, telefone);
                    break;
                case 4:
                    Cliente.consultarTodosClientes();
                    break;
                case 0:
                    System.out.println("Saindo do menu de Administrador...");
                    break;
                default:
                    System.out.println("Valor inválido.");
            }
        } while (opcao != 0);
    }

    public static void executarMenuProduto(Scanner scanner) {
        int opcao;
        do {
            MenuUtils.exibirMenuProduto();
            System.out.print("Escolha uma opção: ");
            opcao = Tratativas.lerInteiro();

            switch (opcao) {
                case 1:
                    System.out.println("=== Cadastro de Produto ===");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();
                    System.out.print("Artista: ");
                    String artista = scanner.nextLine();
                    System.out.print("Ano de lançamento: ");
                    int ano = Tratativas.lerInteiro();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Quantidade em estoque: ");
                    int qtd = Tratativas.lerInteiro();

                    Produto novo = new Produto(nome, genero, artista, ano, preco, qtd);
                    Produto.cadastrarProduto(novo);
                    break;

                case 2:
                    System.out.print("Informe o ID do produto: ");
                    int idBusca = Tratativas.lerInteiro();
                    Produto p = Produto.buscarPorId(idBusca);
                    if (p != null) {
                        p.exibirInformacoes();
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 3:
                    Produto.exibirTodosProdutos();
                    break;

                case 4:
                System.out.print("Digite o ID do produto que deseja alterar: ");
                int idProduto = Tratativas.lerInteiro();
                Produto produto = Produto.buscarPorId(idProduto);
                if (produto != null) {
                    System.out.println("Produto encontrado:");
                    System.out.println(produto);
                    System.out.println("\nDigite os novos dados para o produto:");
                    System.out.print("Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String novoGenero = scanner.nextLine();
                    System.out.print("Artista: ");
                    String novoArtista = scanner.nextLine();
                    System.out.print("Ano de Lançamento: ");
                    int novoAno = Tratativas.lerInteiro();
                    System.out.print("Preço: ");
                    double novoPreco = Double.parseDouble(scanner.nextLine());
                    System.out.print("Quantidade em Estoque: ");
                    int novoEstoque = Tratativas.lerInteiro();
                    System.out.print("Produto disponível? (true/false): ");
                    boolean novoDisponivel = Boolean.parseBoolean(scanner.nextLine());
                    produto.alterarProduto(novoNome, novoGenero, novoArtista, novoAno, novoPreco, novoEstoque, novoDisponivel);
                } else {
                    System.out.println("Produto com ID " + idProduto + " não encontrado.");
                }
                    break;
                case 5:
                    System.out.print("Informe o ID do produto a ser inativado: ");
                    int idInativar = Tratativas.lerInteiro();
                    Produto.excluirProduto(idInativar);
                    break;

                case 0:
                    System.out.println("Saindo do menu de Produtos");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
