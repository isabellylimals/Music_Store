package src.models;
import java.util.Date;
import java.util.Scanner;

import src.utils.Tratativas;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Cliente cadastro = new Cliente();
        Produto.carregarProdutosDoBanco();
         int idVenda = 1;
        Venda v = new Venda(idVenda, new Date(), cadastro);
       
        int opcao;

        do {
            System.out.println("===== MUSIC STORE =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Exibir Dados Cadastrais");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Listar Clientes");
            System.out.println("5 - Alterar Dados Cadastrais");
            System.out.println("6 - Cadastrar Produto");
            System.out.println("7 - Remover item");
            System.out.println("8 - Finalizar venda");
            System.out.println("9 - Listar produtos, teste");
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
                    String email;
                        while (true) {
                            System.out.print("Digite o email: ");
                            email = leitor.nextLine();
                            if (Tratativas.validarEmail(email)) {
                                break;
                            } else {
                                System.out.println("Email inválido! Tente novamente.");
                            }
                        }
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
                case 6:
                    System.out.println("Cadastro de novo produto para venda:");

                    System.out.print("Digite o nome do produto: ");
                    String nomeProduto = leitor.nextLine();

                    System.out.print("Digite o gênero musical: ");
                    String genero = leitor.nextLine();

                    System.out.print("Digite o nome do artista: ");
                    String artista = leitor.nextLine();

                    System.out.print("Digite o ano de lançamento: ");
                    int ano = leitor.nextInt();

                    System.out.print("Digite o preço: ");
                    double preco = leitor.nextDouble();

                    System.out.print("Digite a quantidade em estoque: ");
                    int estoque = leitor.nextInt();
                    leitor.nextLine(); 

                    Produto novoProduto = new Produto(nomeProduto, genero, artista, ano, preco, estoque);
                    Produto.cadastrarProduto(novoProduto);
                    System.out.println("Produto cadastrado com ID: " + novoProduto.getId());
                    break;

                case 7:
                    System.out.print("Digite o ID do item a venda pra remover "); 
                    int idRemover = leitor.nextInt();
                    v.removerItem(idRemover);
                    v.listarItensVenda();
                    
                    break;
                case 8: 
                v.finalizarVenda(leitor, cadastro);
                    break;
                case 9:
                     Produto.exibirProdutosCliente();
                
                break;
                case 10:
                Produto.exibirTodosProdutos();
            break;
            case 11:
            int i = leitor.nextInt();
                    Produto.excluirProduto(i);
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
