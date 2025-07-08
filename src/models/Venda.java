package src.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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

public void finalizarVenda(Scanner leitor, Cliente cadastro) {
    System.out.print("Digite o ID do cliente que está comprando: ");
    int idCliente = leitor.nextInt();
    leitor.nextLine(); // limpar buffer

    Cliente clienteSelecionado = cadastro.buscarPorId(idCliente);

    if (clienteSelecionado == null) {
        System.out.println("Cliente não encontrado. Venda cancelada.");
        return;
    }

    this.cliente = clienteSelecionado; 

    String continuar;
    do {
        System.out.println("Produtos disponíveis:");
        Produto.exibirTodosProdutos();

        System.out.print("Digite o ID do produto que deseja comprar: ");
        int idEscolhido = leitor.nextInt();
        Produto produtoSelecionado = Produto.buscarPorId(idEscolhido);

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
        } else {
            System.out.println("Produto selecionado:");
            produtoSelecionado.exibirInformacoes();

            System.out.print("Digite a quantidade que deseja comprar: ");
            int quantidade = leitor.nextInt();

            if (quantidade > produtoSelecionado.getQtdEstoque()) {
                System.out.println("Estoque insuficiente! Produto disponível : " + produtoSelecionado.getQtdEstoque());
            } else {
                boolean sucesso = produtoSelecionado.reduzirEstoque(quantidade);
                if (sucesso) {
                    ItemVenda itemVenda = new ItemVenda(quantidade,produtoSelecionado);
                    adicionarItemVenda(itemVenda);
                    System.out.println("Item adicionado à venda com sucesso!");
                } else {
                    System.out.println("Erro ao reduzir estoque. Item não adicionado.");
                }
            }
        }

        leitor.nextLine();
        System.out.print("Deseja adicionar outro produto? (s/n): ");
        continuar = leitor.nextLine();

    } while (continuar.equalsIgnoreCase("s"));

    calculaTotal();
    System.out.println("Venda finalizada com sucesso!");
    exibirResumo();
}


}

