package views;

import DAO.ClienteDao;
import java.util.Scanner;
import src.models.Cliente;
import src.models.Produto;
import src.utils.Tratativas;
import src.utils.sons.entranaconta.SomUtil;

public class TelaLoginView {
    public static void executarMenuLogin(Scanner scanner) {
        int opcao;
        do {
            MenuUtils.exibirMenulogin();

            System.out.print("Informe a opção: ");
            opcao = Tratativas.lerInteiro("Informe a opção novamente");

            switch (opcao) {
                case 1:
                    String email;
                    do {
                         
                        System.out.print("Digite o email: ");
                        email = scanner.nextLine();
                        if (!Tratativas.validarEmail(email)) {
                            System.out.println("Email inválido! Tente novamente.");
                        }
                    } while (!Tratativas.validarEmail(email));

                    System.out.print("Digite a senha: ");
                    String senha = Tratativas.lerSenha();

                    Cliente usuarioEncontrado = ClienteDao.buscarPorEmailSenha(email, senha);

                    if (usuarioEncontrado != null) {
                        if (usuarioEncontrado.login(email, senha)) {
                             SomUtil.carregarSom("src/utils/sons/entranaconta/conta.wav");
                            System.out.println("Login autorizado!");
                            SomUtil.tocarSom();
                           
                            Produto.carregarProdutosDoBanco();

                            if (usuarioEncontrado.isAdministrador()) {
                                AdministratorView.executarMenuAdministrator(scanner, usuarioEncontrado);
                            } else {
                                ClienteView.executarMenuCliente(scanner, usuarioEncontrado);
                            }
                        } else {
                            System.out.println("Login falhou: conta desativada.");
                        }
                    } else {
                        System.out.println("Usuário não cadastrado no sistema.");
                    }

                    break;

                case 2:
                    System.out.println("Criando nova conta de usuário:");

                    System.out.print("Digite o nome: ");
                    String novoNome = Tratativas.lerSomenteLetrasEEspacos("Informe o nome novamente");
                    String novoEmail;

                    while (true) {
                        System.out.print("Digite o email: ");
                        novoEmail = scanner.nextLine();
                        if (Tratativas.validarEmail(novoEmail)) {
                            break;
                        } else {
                            System.out.println("Email inválido! Tente novamente.");
                        }
                    }

                    System.out.print("Digite a senha: ");
                    String novaSenha = Tratativas.lerSenha();

                    System.out.print("Digite o telefone: ");
                    String novoTelefone = Tratativas.lerTelefone();

                    boolean isAdministrador = Tratativas.verificaEscolha("O usuário é administrador (sim/nao)");

                    Cliente.cadastrarCliente(novoNome, novoEmail, novaSenha, novoTelefone, isAdministrador);

        
                    break;

                case 0:
                    System.out.println("Saindo do Sistema ...");
                    Tratativas.limparTela();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);

    }

}