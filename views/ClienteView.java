 
package views;

import java.util.List;
import java.util.Scanner;

import DAO.VendaDao;
import src.models.Cliente;
import src.models.Produto;
import src.utils.Tratativas;

public class ClienteView {
    public static void executarMenuCliente(Scanner scanner, Cliente cliente) {
        int opcao;

        do {
            MenuUtils.exibirMenuCliente();
            System.out.print("Escolha uma opção: ");
            opcao = Tratativas.lerInteiro("Informe a opção novamente");

            switch (opcao) {
                case 1:
                    cliente.exibirDadosCadastrais(cliente.getId());
                    break;

                case 2:
                    cliente.removerCliente(cliente.getId());
                    break;

                case 3:
                    System.out.print("Digite o novo nome: ");
                    String nome = Tratativas.lerSomenteLetrasEEspacos("Informe o novo nome novamente");

                    String email;
                    do {
                        System.out.print("Digite o novo email: ");
                        email = scanner.nextLine();
                        if (!Tratativas.validarEmail(email)) {
                            System.out.println("Email inválido! Tente novamente.");
                        }
                    } while (!Tratativas.validarEmail(email));

                    System.out.print("Digite a nova senha: ");
                    String senha = Tratativas.lerSenha();

                    System.out.print("Digite o novo telefone: ");
                    String telefone = Tratativas.lerTelefone();

                    cliente.alterarCadastro(cliente.getId(), nome, email, senha, telefone);
                    break;

                case 4:
                    executarMenuConsultaDeProdutos(scanner);
                    break;

                case 5:
                VendaDao.gerarHistoricoVendas(cliente.getId());
                    break;

                case 0:
                    System.out.println("Saindo do menu do cliente...");
                    Tratativas.limparTela();

                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    public static void executarMenuConsultaDeProdutos(Scanner scanner) {
        int opcao;
        do {
            MenuUtils.exibirMenuConsultaDeProdutos();
            System.out.print("Escolha uma opção: ");
            opcao = Tratativas.lerInteiro("Informe a opção novamente");

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do produto: ");
                    String nomeBusca = Tratativas.lerSomenteLetrasEEspacos("Informe o nome do produto novamente");
                    List<Produto> encontradosNome = Produto.buscarPorNome(nomeBusca);
                    if (encontradosNome.isEmpty()) {
                        System.out.println("Produto não encontrado.");
                    } else {
                        for (Produto prod : encontradosNome) {
                            if (prod.isDisponivel()) {
                                System.out.println(prod);
                            } else {
                                System.out.println("Produto \"" + prod.getNome() + "\" não está disponível.");
                            }
                        };
                    }
                    break;

                case 2:
                    System.out.print("Digite o gênero musical: ");
                    String generoBusca = Tratativas.lerSomenteLetrasEEspacos("Informe o nome do gênero musical novamente");
                    List<Produto> encontradosGenero = Produto.buscarPorGenero(generoBusca);
                    if (encontradosGenero.isEmpty()) {
                        System.out.println("Nenhum produto encontrado com esse gênero.");
                    } else {
                        for (Produto prod : encontradosGenero) {
                            if (prod.isDisponivel()) {
                                System.out.println(prod);
                            } else {
                                System.out.println("Produto \"" + prod.getNome() + "\" não está disponível.");
                            }
                        };
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do artista: ");
                    String artistaBusca = Tratativas.lerSomenteLetrasEEspacos("Informe o nome do artista novamente");
                    List<Produto> encontradosArtista = Produto.buscarPorArtista(artistaBusca);
                    if (encontradosArtista.isEmpty()) {
                        System.out.println("Nenhum produto encontrado para esse artista.");
                    } else {
                        for (Produto prod : encontradosArtista) {
                            if (prod.isDisponivel()) {
                                System.out.println(prod);
                            } else {
                                System.out.println("Produto \"" + prod.getNome() + "\" não está disponível.");
                            }
                        };
                    }
                    break;

                case 4:
                    Produto.exibirProdutosCliente();
                    break;

                case 0:
                    System.out.println("Saindo do menu de consulta de produtos...");
                    Tratativas.limparTela();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }
}