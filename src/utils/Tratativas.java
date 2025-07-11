package src.utils;

import java.util.Scanner;

public class Tratativas {

    public static Scanner leitor = new Scanner(System.in);

    public static void fecharScanner(){
        leitor.close();
    }

    /*Funcao que verifica o login informado, garantindo que nao contenha espacos em branco
     */
    public static String lerLogin(){
        String login;

        do{
            login=leitor.nextLine().trim();
            if(login.contains(" ")){
                System.out.println("Login nao deve conter espaços em branco. \nDigite o seu login novamente.");
            }
        }while (login.contains(" "));
        return login;
    }
    
    public static String lerSenha(){
        String senhas;
        do{
            senhas=leitor.nextLine().trim();

            if(senhas.length()<8){
                System.out.println("A senha deve ter pelo menos 8 caracteres. ");
            }else if (senhas.contains(" ")){
                System.out.println("A senha não pode conter espaços em branco.");
            }

        }while(senhas.length()<8 || senhas.contains(" "));
        return senhas;
    }

    /* */
    public static void limparTela(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {

                /*Cria um processo do sistema operacional*/
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Código ANSI para sistemas Unix-like
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50)); // Fallback universal
        }
    }   
    

    public static int lerInteiro(){
        while (true) {
            try{
                return Integer.parseInt(leitor.nextLine().trim());
            } catch(NumberFormatException e){
                System.out.println("Por favor, digite um numero inteiro válido!");
            };
        }
    }
 
    public static String lerSomenteLetrasEEspacos(){
        while (true) {
            String entrada = leitor.nextLine().trim();

            if(entrada.matches("[a-sA-Z]+")){
                return entrada;
            }
            System.out.println("Por favor, digite apenas letras e espaços!");
        }
    }

    public static String lerTelefone(){
        while (true) {
            String telefone = leitor.nextLine().trim().replaceAll("[^0-9]", "");

            if (telefone.length()>=10 && telefone.matches("[0-9]+")){ 
                return telefone;
            }
            System.out.println("Numero inválido. Digite pelo menos 10 numeros.");
        }
    }
}