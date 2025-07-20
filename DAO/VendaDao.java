package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.conection.Conexao;
import src.models.ItemVenda;
import src.models.Produto;
import src.models.Venda;
import java.sql.Date;


public class VendaDao {
    public static void cadastrarVendaBanco(Venda venda, int idCliente, String nomeCliente, ItemVenda item) {
        String sql = "INSERT INTO vendas (idVenda, dat, valorTotal, idProduto, nomeProduto, precoProduto, quantidade, idCliente, nomeCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement comandoPreparado = null;
        Produto produto = item.getProduto();
        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);

            comandoPreparado.setInt(1, venda.getId());
            comandoPreparado.setDate(2, new java.sql.Date(venda.getData().getTime())); 
            comandoPreparado.setDouble(3, venda.getValorTotal());

            comandoPreparado.setInt(4, produto.getId());
            comandoPreparado.setString(5, produto.getNome());
            comandoPreparado.setDouble(6, produto.getPreco());
            comandoPreparado.setInt(7, item.getQuantidade());

            comandoPreparado.setInt(8, idCliente);
            comandoPreparado.setString(9, nomeCliente);

            comandoPreparado.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente no banco: ");
            e.printStackTrace();
        }
    }

    public static int obterIdVendaBanco() {
        String sql = "SELECT MAX(idVenda) AS max_id FROM vendas";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultado = comandoPreparado.executeQuery();

            if (resultado.next()) {
                int ultimoId = resultado.getInt("max_id");
                return ultimoId + 1;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 1; 
        }
    }

    public static void reporEstoque(int id, int quantidade) {
        String sql = "UPDATE produtos SET qtdEstoque = qtdEstoque + ? WHERE id = ?";
        PreparedStatement comandoPreparado = null;
        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, quantidade);
            comandoPreparado.setInt(2, id);
            comandoPreparado.executeUpdate();
            atualizarConformeEstoqueDisponibilidade(id);
        } catch (SQLException e) {
            System.out.println("Erro ao repor estoque.");
            e.printStackTrace();
        }
    }

    public static void reduzirEstoque(int id, int quantidade) {
        String sql = "UPDATE produtos SET qtdEstoque = qtdEstoque - ? WHERE id = ?";
        PreparedStatement comandoPreparado = null;
        try  {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, quantidade);
            comandoPreparado.setInt(2, id);
            comandoPreparado.executeUpdate();
            atualizarConformeEstoqueDisponibilidade(id);
        } catch (SQLException e) {
            System.out.println("Erro ao reduzir estoque.");
            e.printStackTrace();
        }
    }

    public static List<String> gerarRelatorioVendasCliente(int idCliente) {
        String sql = """
        SELECT idVenda, dat, nomeProduto, precoProduto, quantidade, valorTotal, nomeCliente
        FROM vendas
        WHERE idCliente = ?
        ORDER BY idVenda;
        """;

        List<String> relatorio = new ArrayList<>();
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, idCliente);
            ResultSet resultado = comandoPreparado.executeQuery();

            int vendaAtual = -1;
            String nomeCliente = null;

            while (resultado.next()) {
                int idVenda = resultado.getInt("idVenda");
                String data = resultado.getString("dat");
                String nomeProduto = resultado.getString("nomeProduto");
                double preco = resultado.getDouble("precoProduto");
                int qtd = resultado.getInt("quantidade");
                double valorTotal = resultado.getDouble("valorTotal");
                nomeCliente = resultado.getString("nomeCliente");

                if (idVenda != vendaAtual) {
                    relatorio.add(""); 
                    relatorio.add(String.format("VENDA #%d | Data: %s | Cliente: %s", idVenda, data, nomeCliente));
                    relatorio.add("Produto                 | Preço   | Qtd | Total");
        
                    vendaAtual = idVenda;
                }

                String linha = String.format("%-22s | R$%6.2f | %3d | R$%6.2f", nomeProduto, preco, qtd, valorTotal);
                relatorio.add(linha);
            }

            if (relatorio.isEmpty()) {
                relatorio.add("Nenhuma venda encontrada para este cliente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorio;
    }

    public static List<Integer> listarIdsClientesComVenda() {
        List<Integer> ListaDeIds = new ArrayList<>();
        String sql = "SELECT DISTINCT idCliente FROM vendas ORDER BY idCliente";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultado = comandoPreparado.executeQuery();
            while (resultado.next()) {
                ListaDeIds.add(resultado.getInt("idCliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ListaDeIds;
    }

    public static void gerarHistoricoVendas(int idCliente) {
        String sql = "SELECT idVenda, dat, nomeProduto, quantidade, precoProduto, valorTotal, nomeCliente "
                + "FROM vendas WHERE idCliente = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, idCliente);
            ResultSet resultado = comandoPreparado.executeQuery();

            boolean encontrou = false;
            String nomeCliente = "";

            while (resultado.next()) {
                if (!encontrou) {
                    nomeCliente = resultado.getString("nomeCliente");
                    System.out.println("=== Histórico de Compras de " + nomeCliente + " ===");
                }

                encontrou = true;
                int idVenda = resultado.getInt("idVenda");
                Date data = resultado.getDate("dat");
                String nomeProduto = resultado.getString("nomeProduto");
                int quantidade = resultado.getInt("quantidade");
                double preco = resultado.getDouble("precoProduto");
                double total = resultado.getDouble("valorTotal");

                System.out.println("Venda ID: " + idVenda + " | Data: " + data);
                System.out.println("Produto: " + nomeProduto);
                System.out.println("Quantidade: " + quantidade + " | Preço Unitário: R$" + preco);
                System.out.println("Total da Venda: R$" + total);
                System.out.println("---------------------------------------------");
            }

            if (!encontrou) {
                System.out.println("Nenhuma venda encontrada para o cliente com ID: " + idCliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao gerar histórico.");
            e.printStackTrace();
        }
    }

    public static void atualizarConformeEstoqueDisponibilidade(int id) {
        String sql = "SELECT qtdEstoque, disponivel FROM produtos WHERE id = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, id);
            ResultSet resultado = comandoPreparado.executeQuery();

            if (resultado.next()) {
                int estoque = resultado.getInt("qtdEstoque");
                boolean disponivel = resultado.getBoolean("disponivel");

                if (estoque <= 0 && disponivel) {
                    ProdutoDao.atualizarDisponibilidade(id, false);
                } else if (estoque > 0 && !disponivel) {
                    ProdutoDao.atualizarDisponibilidade(id, true);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Disponibilidade.");
            e.printStackTrace();
        }
    }

}