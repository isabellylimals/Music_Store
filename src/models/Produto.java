package src.models;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private static int proximoIdProd = 1;

    private int id;
    private String nome;
    private String genero;
    private String artista;
    private int anoLancamento;
    private double preco;
    private int qtdEstoque;

    private static List<Produto> listaDeProdutos = new ArrayList<>();

       public Produto(String nome, String genero, String artista, int anoLancamento, double preco, int qtdEstoque) {
        this.id = proximoIdProd++;
        this.nome = nome;
        this.genero = genero;
        this.artista = artista;
        this.anoLancamento = anoLancamento;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
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

    // após cada compra o estoque vai diminuir
    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= qtdEstoque) {
            this.qtdEstoque -= quantidade;
            return true;
        }//não pode comprar mais do oque tem no estoque
        return false;
    }

     public static void exibirTodosProdutos() {
        if (listaDeProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("=== Lista de Produtos ===");
        for (Produto p : listaDeProdutos) {
            System.out.println(p);
        }
    }

    //Funções adm

    public void alterarProduto(String nome, String genero, String artista, int ano, double preco, int estoque) {
        this.nome = nome;
        this.genero = genero;
        this.artista = artista;
        this.anoLancamento = ano;
        this.preco = preco;
        this.qtdEstoque = estoque;
        System.out.println("Produto alterado com sucesso!");
    }

    public static Produto buscarPorId(int id) {
        for (Produto p : listaDeProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static void removerProduto(int id) {
        Produto p = buscarPorId(id);
        if (p != null) {
            listaDeProdutos.remove(p);
            System.out.println("Produto removido");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void cadastrarProduto(Produto p){
        listaDeProdutos.add(p);
        System.out.println("O produto " + p.getNome() + " foi cadastrado com sucesso");
    }

    
    public void reporEstoque(int quantidade) {
        this.qtdEstoque += quantidade;
    }

     @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Artista: %s | Gênero: %s | Preço: R$ %.2f | Estoque: %d",
                id, nome, artista, genero, preco, qtdEstoque);
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
    
}
