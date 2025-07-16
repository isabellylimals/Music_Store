package src.models;

import DAO.ClienteDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import DAO.VendaDao;
import src.utils.Tratativas;

public class Venda {
    private int id;
    private Date data;
    private double valorTotal;
    Cliente cliente;

    private static List<Venda> todasAsVendas = new ArrayList<>();
    private ArrayList<ItemVenda> itensVenda = new ArrayList<>();

    public Venda(Date data, Cliente cliente) {
        this.id = VendaDao.obterIdVendaBanco();
        this.data = data;
        this.valorTotal = 0.0;
        this.itensVenda = new ArrayList<>();
        this.cliente = cliente;
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

    public List<ItemVenda> getItensVenda() {
        return this.itensVenda;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\nData da Venda: " + data + "\nValor Total da venda: " + valorTotal + "\n" +
                "Cliente: " + (cliente != null ? cliente.getNome() : "Nenhum cliente associado");
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

    public void adicionarItemVenda(ItemVenda item) {
        if (item != null) {
            itensVenda.add(item);
            valorTotal += item.calcularSubTotal();
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

    public static void finalizarVenda(Scanner leitor) {
        System.out.print("Digite o ID do cliente que está comprando: ");
        int idCliente = leitor.nextInt();
        leitor.nextLine();

        Cliente clienteSelecionado = ClienteDao.buscar(idCliente);

        if (clienteSelecionado == null) {
            System.out.println("Cliente não encontrado. Venda não pode prosseguir.");
            return;
        }

        Venda venda = new Venda(new Date(), clienteSelecionado);
        venda.setCliente(clienteSelecionado);

        boolean continuar;
        do {
            System.out.println("Produtos disponíveis:");
            Produto.exibirProdutosCliente();

            System.out.print("Digite o ID do produto que deseja comprar: ");
            int idEscolhido = leitor.nextInt();
            Produto produtoSelecionado = Produto.buscarPorId(idEscolhido);

            if (produtoSelecionado == null || !produtoSelecionado.isDisponivel()
                    || produtoSelecionado.getQtdEstoque() <= 0) {
                System.out.println("Escolha inválida, tente novamente.");
            } else {
                System.out.println("Produto selecionado:");
                produtoSelecionado.exibirInformacoes();

                System.out.print("Digite a quantidade que deseja comprar: ");
                int quantidade = leitor.nextInt();

                if (quantidade > produtoSelecionado.getQtdEstoque()) {
                    System.out.println(
                            "Estoque insuficiente! Quantidade disponível: " + produtoSelecionado.getQtdEstoque());
                    System.out.println("Item não adicionado à venda.");
                } else {
                    boolean sucesso = produtoSelecionado.reduzirEstoque(idEscolhido, quantidade);
                    if (sucesso) {
                        ItemVenda itemVenda = new ItemVenda(quantidade, produtoSelecionado);
                        venda.adicionarItemVenda(itemVenda);
                        System.out.println("Item adicionado à venda.");
                    } else {
                        System.out.println("Erro inesperado ao reduzir estoque.");
                    }
                }
            }

            leitor.nextLine();
            continuar = Tratativas.verificaEscolha("Deseja continuar adicionando produtos? (sim/nao)");

        } while (continuar);

        venda.calculaTotal();

        for (ItemVenda item : venda.getItensVenda()) {
            VendaDao.cadastrarVendaBanco(venda, idCliente, clienteSelecionado.getNome(), item);
        }

        System.out.println("Venda finalizada e salva com sucesso!");
        venda.exibirResumo();
    }

    public static String gerarRelatorioVendas() {
        // List<Venda> todasAsVendas = VendaDao.listarTodas();

        if (todasAsVendas.isEmpty()) {
            return "Nenhuma venda foi realizada.";
        }

        StringBuilder relatorio = new StringBuilder("RELATÓRIO DE TODAS AS VENDAS:\n");

        for (Venda v : todasAsVendas) {
            relatorio.append(v.toString()).append("\n");
        }

        return relatorio.toString();
    }

    public void listarItensVenda() {
        if (itensVenda.isEmpty()) {
            System.out.println("Nenhum item adicionado à venda.");
            return;
        }

        System.out.println("Itens na venda:");
        for (ItemVenda item : itensVenda) {
            System.out.println(item);
        }
    }

}