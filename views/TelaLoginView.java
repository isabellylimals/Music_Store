package views;

import java.util.Scanner;
import src.models.Cliente;
import src.utils.Tratativas;
import DAO.ClienteDao;

public class TelaLoginView {
    public static void executarMenuLogin(Scanner scanner) {
        int opcao;
        do {
            MenuUtils.exibirMenulogin();

            System.out.print("Informe a opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();

                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();

                    Cliente usuarioEncontrado = ClienteDao.buscarPorEmailSenha(email, senha);

                    if (usuarioEncontrado != null) {
                        if (usuarioEncontrado.login(email, senha)) {
                            System.out.println("Login autorizado!");

                            if (usuarioEncontrado.isAdministrador()) {
                                // aqui entra o menu de administrador
                            } else {
                                ClienteView.executarMenuCliente(scanner, usuarioEncontrado);
                                // aqui entra o menu de cliente
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
                    String novoNome = scanner.nextLine();

                    System.out.print("Digite o email: ");
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
                    String novaSenha = scanner.nextLine();

                    System.out.print("Digite o telefone: ");
                    String novoTelefone = scanner.nextLine();

                    System.out.print("Você é administrador? (sim/nao): ");
                    String perfilUsuario = scanner.nextLine();

                    boolean isAdministrador = perfilUsuario.equalsIgnoreCase("sim");

                    Cliente usuario = new Cliente(novoNome, novoEmail, novaSenha, novoTelefone);
                    usuario.setAdministrador(isAdministrador);

                    ClienteDao.cadastrar(usuario);

                    System.out.println("Conta criada com sucesso!");
                    break;

                case 0:
                    System.out.println("Saindo do Sistema ...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);

    }

}
