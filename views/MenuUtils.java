package views;

public class MenuUtils {

    public static void exibirMenulogin() {
        System.out.println("=== MUSIC STORE LOGIN ===");
        System.out.println("1. Realizar Login");
        System.out.println("2. Criar nova conta");
        System.out.println("0. Sair");
    }

    public static void exibirMenuAdministrador() {
        System.out.println("\n=== MENU ADMINISTRADOR ===");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Gerenciar Vendas");
        System.out.println("3. Cadastrar Cliente");
        System.out.println("4. Listar Clientes cadastrados");
        System.out.println("0. voltar");
    }

    public static void exibirMenuProduto() {
        System.out.println("\n=== GERENCIAR PRODUTOS ===");
        System.out.println("1. Cadastrar Produtos");
        System.out.println("2. Exibir informações do Produto");
        System.out.println("3. Listar Produtos");
        System.out.println("4. Alterar Produto");
        System.out.println("5. Inativar Produto");
        System.out.println("6. Repor estoque do Produto");
        System.out.println("0. voltar");
    }

    public static void exibirMenuVenda() {
        System.out.println("\n=== GERENCIAR VENDAS ===");
        System.out.println("1. Realizar venda");
        System.out.println("2. Gerar Relatorio da venda");
        System.out.println("0. voltar");
    }

    public static void exibirMenuCliente() {
        System.out.println("\n=== MENU CLIENTE ===");
        System.out.println("1. Exibir Dados Cadastrais");
        System.out.println("2. Atualizar Status da Conta");
        System.out.println("3. Alterar Dados Cadastrais");
        System.out.println("4. Consultar Produtos");
        System.out.println("5. Visualizar Histórico de Compras");
        System.out.println("0. voltar");
    }

    public static void exibirMenuConsultaDeProdutos() {
        System.out.println("\n=== CONSULTA DE PRODUTOS ===");
        System.out.println("1. Buscar por nome");
        System.out.println("2. Buscar por gênero musical");
        System.out.println("3. Buscar por artista");
        System.out.println("4. Listar todos os produtos disponíveis");
        System.out.println("0. Voltar");
    }
}