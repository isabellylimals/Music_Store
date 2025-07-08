package src.models;
public class ItemVenda {
    private int quantidade;
   private Produto produto; 

    
    public ItemVenda(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto= produto;
    }



    public int getQuantidade() {
        return quantidade;
    }



    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }



    @Override
    public String toString() {
        return "ItemVenda: \nquantidade:" + quantidade;
    }

public Produto getProduto(){
    return produto;
}


    
    public double calcularSubTotal(){
        if(produto!= null){
            return produto.getPreco() * quantidade;
        } else {
            System.out.println("Produto não definido para o item de venda.");
            return 0.0;
        }
    }

}

