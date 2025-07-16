package src.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Tratativas {

    public static Scanner leitor = new Scanner(System.in);

    public static void fecharScanner() {
        leitor.close();
    }

    public static String lerLogin() {
        String login;

        do {
            login = leitor.nextLine().trim();
            if (login.contains(" ")) {
                System.out.println("Login nao deve conter espaços em branco. \nDigite o seu login novamente.");
            }
        } while (login.contains(" "));
        return login;
    }

    public static String lerSenha() {
        String senhas;
        do {
            senhas = leitor.nextLine().trim();

            if (senhas.length() < 8) {
                System.out.println("A senha deve ter pelo menos 8 caracteres. ");
                System.out.print("Digite a senha: ");
            } else if (senhas.contains(" ")) {
                System.out.println("A senha não pode conter espaços em branco.");
                System.out.print("Digite a senha: ");
            }

        } while (senhas.length() < 8 || senhas.contains(" "));
        return senhas;
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {

                /* Cria um processo do sistema operacional */
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Código ANSI para sistemas Unix-like
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }

    public static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(leitor.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero inteiro válido!");
                System.out.print("Informe a opção novamente: ");
            }
        }
    }

    public static String lerSomenteLetrasEEspacos(String texto) {

        while (true) {

            String entrada = leitor.nextLine().trim();

            if (entrada.matches("[A-Za-zÀ-ÿ ]+") && entrada.replaceAll("[^A-Za-zÀ-ÿ]", "").length() >= 2) {
                return entrada;
            }

            System.out.println("Por favor, digite apenas letras e espaços (mínimo duas letras)!");
            System.out.print(texto + ": ");
        }
    }

    public static String lerTelefone() {
        while (true) {
            String telefone = leitor.nextLine().trim().replaceAll("[^0-9]", "");

            if (telefone.length() >= 10 && telefone.matches("[0-9]+")) {
                return telefone;
            }
            System.out.println("Numero inválido. Digite pelo menos 10 numeros.");
        }
    }

    public static LocalDate lerData() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/mm/yyyy");

        while (true) {
            System.out.print("Digite a data (DD/MM/AAAA): ");
            String entrada = leitor.nextLine().trim();

            try {
                return LocalDate.parse(entrada, formatador);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Formato correto: DD/MM/AAAA");
            }
        }
    }

    public static String formatarData(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static boolean validarEmail(String email) {
        return email.matches("^[\\w](\\.?[\\w-])*@[\\w-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean verificaEscolha(String texto) {
        String perfilUsuario;

        while (true) {
            System.out.print(texto + ": ");
            perfilUsuario = leitor.nextLine().trim();

            if ((perfilUsuario.equalsIgnoreCase("sim") || perfilUsuario.equalsIgnoreCase("nao"))
                    && !perfilUsuario.contains(" ")) {
                return perfilUsuario.equalsIgnoreCase("sim");
            } else {
                System.out.println("Entrada inválida. Digite apenas 'sim' ou 'nao', sem espaços.");
            }
        }
    }

}