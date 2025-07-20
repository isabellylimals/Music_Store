package src.models;

import DAO.ProdutoDao;
import DAO.VendaDao;
import java.util.ArrayList;
import java.util.List;

public class Produto {

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
        for (Produto produtoBanco : produtosDoBanco) {
            boolean atualizado = false;
            for (int i = 0; i < listaDeProdutos.size(); i++) {
                Produto existente = listaDeProdutos.get(i);
                if (existente.getId() == produtoBanco.getId()) {
                    listaDeProdutos.set(i, produtoBanco);
                    atualizado = true;
                    break;
                }
            }
            if (!atualizado) {
                listaDeProdutos.add(produtoBanco);
            }
        }
    }

    public boolean reduzirEstoque(int id, int quantidade) {
        if (this.qtdEstoque >= quantidade) {
            this.qtdEstoque -= quantidade;
            VendaDao.reduzirEstoque(id, quantidade);
            return true;
        }
        return false;
    }

    public void reporEstoque(int id, int quantidade) {
        this.qtdEstoque += quantidade;
        VendaDao.reporEstoque(id, quantidade);
    }

    public static void exibirProdutosCliente() {
        List<Produto> listaDeProdutos = ProdutoDao.listarTodos();
        boolean encontrouDisponivel = false;

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

    public void alterarProduto(String nome, String genero, String artista, int ano, double preco, int estoque,
            boolean disponivel) {
        this.nome = nome;
        this.genero = genero;
        this.artista = artista;
        this.anoLancamento = ano;
        this.preco = preco;
        this.qtdEstoque = estoque;
        this.disponivel = disponivel;
        boolean atualizado = ProdutoDao.atualizarProduto(this);
        if (atualizado) {
            System.out.println("Produto alterado com sucesso e atualizado no banco de dados!");
        } else {
            System.out.println("Falha ao atualizar o produto no banco de dados.");
        }
    }

    public static Produto buscarPorId(int id) {
        for (Produto produto : listaDeProdutos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public static void excluirProduto(int id) {
    Produto produto = buscarPorId(id);
    if (produto != null) {
        if (!produto.isDisponivel()) {
            System.out.println("O produto já está inativo.");
            return;
        }

        produto.setDisponivel(false);
        ProdutoDao.atualizarDisponibilidade(id, false);
        System.out.println("Produto inativado com sucesso.");
        carregarProdutosDoBanco();
    } else {
        System.out.println("Produto não encontrado.");
    }
}


    public static void cadastrarProduto(Produto produto) {
        int proximoId = ProdutoDao.obterId();

        produto.setId(proximoId);

        listaDeProdutos.add(produto);
        ProdutoDao.cadastrar(produto);

        System.out.println("O produto " + "|" + produto.getNome() + "|" + " foi cadastrado com sucesso");
    }

    public static Produto buscarProdutoPorId(int id) {
        return ProdutoDao.buscar(id);
    }

    public static List<Produto> buscarPorNome(String nomeBusca) {
        return ProdutoDao.buscarPorNome(nomeBusca);
    }

    public static List<Produto> buscarPorArtista(String artistaBusca) {
        return ProdutoDao.buscarPorArtista(artistaBusca);
    }

    public static List<Produto> buscarPorGenero(String generoBusca) {
        return ProdutoDao.buscarPorGenero(generoBusca);
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