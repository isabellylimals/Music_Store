package src.models;
public class ItemVenda {
    private int quantidade;
   //private Produto produto; 

    
    public ItemVenda(int quantidade) {
        this.quantidade = quantidade;
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


}
