package src.models;

public class ItemVenda {
    private int quantidade;
    private Produto produto;
    private boolean ativo = true;

    public ItemVenda(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Quantidade: " + quantidade + "|" + (produto != null ? produto.toString() : "Produto não definido");
    }

    public Produto getProduto() {
        return produto;
    }

    public void remover() {
        this.ativo = false;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public boolean setAtivo(boolean ativo) {
        this.ativo = ativo;
        return this.ativo;
    }

    public double calcularSubTotal() {
        if (produto != null) {
            return produto.getPreco() * quantidade;
        } else {
            System.out.println("Produto não definido para o item de venda.");
            return 0.0;
        }
    }

}