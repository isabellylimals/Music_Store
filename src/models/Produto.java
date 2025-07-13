package src.models;

import java.util.ArrayList;
import java.util.List;
import DAO.ProdutoDao;

public class Produto {
    //private static int proximoIdProd = 1;

    private int id;
    private String nome;
    private String genero;
    private String artista;
    private int anoLancamento;
    private double preco;
    private int qtdEstoque;
    private boolean disponivel;

    private static List<Produto> listaDeProdutos = new ArrayList<>();

       public Produto(String nome, String genero, String artista, int anoLancamento, double preco, int qtdEstoque) {
        this.id = ProdutoDao.obterId();
        this.nome = nome;
        this.genero = genero;
        this.artista = artista;
        this.anoLancamento = anoLancamento;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.disponivel = true;
    }

    //Funções para os clientes

    public void exibirInformacoes() {
        System.out.println("=== Informações do Produto ===");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Gênero: " + genero);
        System.out.println("Artista: " + artista);
        System.out.println("Ano de Lançamento: " + anoLancamento);
        System.out.printf("Preço: R$ %.2f\n", preco);
        System.out.println("Quantidade em Estoque: " + qtdEstoque);
    }

    public static void carregarProdutosDoBanco() {
        List<Produto> produtosDoBanco = ProdutoDao.listarTodos();
        for (Produto produto : produtosDoBanco) {//caso seja chamado mais de 1 vez, evita duplicação
            boolean jaExiste = false;
            for (Produto existente : listaDeProdutos) {
                if (existente.getId() == produto.getId()) {
                    jaExiste = true;
                    break;
                }
            }
            if (!jaExiste) {
                listaDeProdutos.add(produto);
            }
        }
    }

    // após cada compra o estoque vai diminuir
    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= qtdEstoque) {
            this.qtdEstoque -= quantidade;
            return true;
        }//não pode comprar mais do oque tem no estoque
        return false;
    }

    public static void exibirProdutosCliente() {//produtos excluidos são false
    boolean encontrouDisponivel = false;//logo os clientes não podem visualizar

    System.out.println("=== Produtos Disponíveis para Compra ===");
    for (Produto produto : listaDeProdutos) {
        if (produto.isDisponivel()) {
            System.out.println(produto);
            encontrouDisponivel = true;
        }
    }

    if (!encontrouDisponivel) {
        System.out.println("Nenhum produto disponível para clientes no momento.");
    }
}


     public static void exibirTodosProdutos() {
        if (listaDeProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("=== Lista de Produtos ===");
        for (Produto produto : listaDeProdutos) {
            System.out.println(produto);
        }
    }

    //Funções adm

    public void alterarProduto(String nome, String genero, String artista, int ano, double preco, int estoque, boolean disponivel) {
        this.nome = nome;
        this.genero = genero;
        this.artista = artista;
        this.anoLancamento = ano;
        this.preco = preco;
        this.qtdEstoque = estoque;
        this.disponivel = disponivel;
        System.out.println("Produto alterado com sucesso!");
    }

    public static Produto buscarPorId(int id) {
        for (Produto produto : listaDeProdutos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public static void excluirProduto(int id) { //não vai excluir do banco de dados
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produto.setDisponivel(false);//vai fazer com que disponivel fique false e os clientes não possam ver

            ProdutoDao.atualizarDisponibilidade(id, false);

            System.out.println("Produto removido");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void cadastrarProduto(Produto produto){
        int proximoId = ProdutoDao.obterId();
        
        produto.setId(proximoId);

        listaDeProdutos.add(produto);
        ProdutoDao.cadastrar(produto);

        System.out.println("O produto " + produto.getNome() + " foi cadastrado com sucesso");
    }

    public Produto buscarProdutoPorId(int id) {
        return ProdutoDao.buscar(id);
    }
    
    public void reporEstoque(int quantidade) {
        this.qtdEstoque += quantidade;
    }

     @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Artista: %s | Gênero: %s | Preço: R$ %.2f | Estoque: %d",
                id, nome, artista, genero, preco, qtdEstoque);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    } 
    
}
