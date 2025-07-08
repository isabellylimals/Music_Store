package src.models;
import java.util.ArrayList;
import java.util.Date;

public class Venda {
private int id;
private Date data;
private double valorTotal;
Cliente cliente;

private ArrayList <ItemVenda> itensVenda = new ArrayList<>();
//private ArrayList<Venda> vendas= new ArrayList<>();
 //incluir cliente dps em todos os metodos que precisa
public Venda(int id, Date data, Cliente cliente){
    this.id= id;
    this.data=data;
    this.valorTotal= 0.0;
    this.itensVenda = new ArrayList<>();
    this.cliente= cliente;
}

public int getId() {
    return id;
}

public Date getData() {
    return data;
}

public double getValorTotal() {
    return valorTotal;
}

public void setId(int id) {
    this.id = id;
}

public void setData(Date data) {
    this.data = data;
}

public void setValorTotal(double valorTotal) {
    this.valorTotal = valorTotal;
}

@Override
public String toString() {
    return "\nID: " + id + "\nData da Venda: " + data + "\nValor Total da venda: " + valorTotal;
}

public void exibirResumo() {
    System.out.println("===== RESUMO DA VENDA =====");
    System.out.println("ID: " + id);
    System.out.println("Data: " + data);

    if (cliente != null) {
        System.out.println("Cliente: " + cliente.getNome() + " (ID: " + cliente.getId() + ")");
    }
    System.out.println("Itens da venda:");
    for (ItemVenda item : itensVenda) {
        System.out.println("- " + item.getProduto().getNome() + " x" + item.getQuantidade() +
                " = R$ " + item.calcularSubTotal());
    }
    System.out.printf("Total: R$ %.2f\n", valorTotal);
    System.out.println("=============================");
}

public void adicionarItemVenda(ItemVenda item){
    if(item!=null){
        itensVenda.add(item);
        valorTotal+= item.calcularSubTotal();
    } else {
        System.out.println("Item de venda inválido.");
    }
}

public void removerItem(int idProcurar) {
    boolean itemRemovido = false;

    for (int i = 0; i < itensVenda.size(); i++) {
        ItemVenda item = itensVenda.get(i);
        if (item.getProduto().getId() == idProcurar) {
            itensVenda.remove(i);
            itemRemovido = true;
            System.out.println("Item com id:" + idProcurar + ",removido com sucesso.");
            break; 
        }
    }

    if (!itemRemovido) {
        System.out.println("Nenhum produto encontrado com esse id");
    }
}


public double calculaTotal() {
    double total = 0.0;
    for (ItemVenda item : itensVenda) {
        total += item.calcularSubTotal();
    }
    this.valorTotal = total;
    return total;
}



}

