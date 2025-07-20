package src.models;

import DAO.ClienteDao;
import DAO.VendaDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import src.utils.sons.vendafinalizada.*;
import src.utils.Tratativas;

public class Venda {
    private int id;
    private Date data;
    private double valorTotal;
    Cliente cliente;

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
            System.out.print("- " + item.getProduto().getNome() + " x" + item.getQuantidade() +
                    " = R$ " );
            System.out.printf("%.2f", item.calcularSubTotal());
            System.out.println();
        }
        System.out.printf("Total: R$ %.2f\n", valorTotal);
        System.out.println("===========================");
    }

    public void adicionarItemVenda(ItemVenda item) {
        if (item != null) {
            itensVenda.add(item);
            valorTotal += item.calcularSubTotal();
        } else {
            System.out.println("Item de venda inválido.");
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

    public static String gerarRelatorioVendas() {
        List<Integer> idsClientes = VendaDao.listarIdsClientesComVenda();
        StringBuilder relatorio = new StringBuilder(
                "\n\n======================================\nRELATÓRIO DE VENDAS");

        if (idsClientes.isEmpty()) {
            return "Nenhuma venda foi realizada por nenhum cliente.";
        }

        for (int idCliente : idsClientes) {
            List<String> linhas = VendaDao.gerarRelatorioVendasCliente(idCliente);
            for (String linha : linhas) {
                relatorio.append(linha).append("\n");
            }

        }

        return relatorio.toString();
    }

    public static void finalizarVenda(Scanner leitor) {
        Cliente cliente = solicitarCliente(leitor);
        if (cliente == null)
            return;

        Venda venda = new Venda(new Date(), cliente);

        String continuar = "sim";
        do {
            Produto produto = solicitarProduto(leitor);
            if (produto == null)
                continue;

            int quantidade = solicitarQuantidade(leitor, produto);
            if (quantidade == -1)
                continue;

            boolean sucesso = produto.reduzirEstoque(produto.getId(), quantidade);
            if (sucesso) {
                venda.adicionarItemVenda(new ItemVenda(quantidade, produto));
                System.out.println("Item adicionado à venda.");
            } else {
                System.out.println("Erro ao reduzir estoque.");
            }

            boolean adicionarMais = Tratativas.verificaEscolha("Deseja adicionar outro produto (sim/nao)");
            continuar = adicionarMais ? "sim" : "nao";
        } while (continuar.equalsIgnoreCase("sim"));

        TirarItemdaCompra(leitor, venda);

        if (!validarItensAtivos(venda)) {
            System.out.println("Nenhum item ativo na venda. Venda não finalizada.");
            return;
        }

        venda.calculaTotal();

        for (ItemVenda item : venda.getItensVenda()) {
            VendaDao.cadastrarVendaBanco(venda, cliente.getId(), cliente.getNome(), item);
        }
        SomUtil.carregarSom("src/utils/sons/vendafinalizada/venda.wav");

        System.out.println("Venda finalizada e salva com sucesso!");
        SomUtil.tocarSom();
        venda.exibirResumo();
    }

    private static boolean validarItensAtivos(Venda venda) {
        return venda.getItensVenda()
                .stream()
                .anyMatch(ItemVenda::isAtivo);
    }

    private static Cliente solicitarCliente(Scanner leitor) {
        System.out.print("Digite o ID do cliente que está comprando: ");
        int idCliente = Tratativas.lerInteiro("Infome o Id novamente");

        Cliente cliente = ClienteDao.buscar(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado. Venda não pode prosseguir.");
        }
        return cliente;
    }

    private static Produto solicitarProduto(Scanner leitor) {
        System.out.println("Produtos disponíveis:");
        Produto.exibirProdutosCliente();

        System.out.print("Digite o ID do produto que deseja comprar: ");
        int id = Tratativas.lerInteiro("Infome o Id novamente");

        Produto produto = Produto.buscarProdutoPorId(id);
        if (produto == null || !produto.isDisponivel() || produto.getQtdEstoque() <= 0) {
            System.out.println("Produto inválido.");
            return null;
        }
        produto.exibirInformacoes();
        return produto;
    }

    private static int solicitarQuantidade(Scanner leitor, Produto produto) {
        int qtd;
        do {
            System.out.print("Digite a quantidade que deseja comprar: ");
            qtd = Tratativas.lerInteiro("Infome a quantidade que deseja comprar novamente");

            if (qtd <= 0 || qtd > produto.getQtdEstoque()) {
                System.out.println("Quantidade inválida! Estoque disponível: " + produto.getQtdEstoque());
                return -1;
            }
        } while (qtd <= 0 || qtd > produto.getQtdEstoque());
        return qtd;
    }

    private static void TirarItemdaCompra(Scanner leitor, Venda venda) {
        boolean desejaRemover;

        do {
            desejaRemover = Tratativas.verificaEscolha("Deseja remover algum item antes de finalizar a venda? (sim/nao)");

            if (!desejaRemover) {
                break;
            }

            if (venda.getItensVenda().isEmpty()) {
                System.out.println("Nenhum item na venda para remover.");
                return;
            }

            System.out.println("Itens adicionados à venda:");
            for (ItemVenda item : venda.getItensVenda()) {
                if (item.isAtivo()) {
                    System.out.println("- Produto ID " + item.getProduto().getId() + ": " +
                            item.getProduto().getNome() + " x" + item.getQuantidade());
                }
            }

            System.out.print("Digite o ID do produto que deseja remover: ");
            int idRemover = Tratativas.lerInteiro("Infome o Id novamente");

            venda.removerItem(idRemover);

        } while (true);
    }

    public void removerItem(int idProcurar) {
        boolean itemRemovido = false;

        for (int i = 0; i < itensVenda.size(); i++) {
            ItemVenda item = itensVenda.get(i);
            if (item.getProduto().getId() == idProcurar) {
                Produto produto = item.getProduto();
                int quantidade = item.getQuantidade();

                produto.reporEstoque(produto.getId(), quantidade);
                itensVenda.remove(i);

                itemRemovido = true;
                System.out.println("Item com ID: " + idProcurar + " removido e estoque reposto.");
                break;
            }
        }

        if (!itemRemovido) {
            System.out.println("Nenhum item encontrado com esse ID.");
        }
    }

}