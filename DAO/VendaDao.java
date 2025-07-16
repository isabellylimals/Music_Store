package DAO;

import src.models.ItemVenda;
import src.models.Produto;
import src.models.Venda;

import java.sql.SQLException;

import src.conection.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VendaDao {
    public static void cadastrarVendaBanco(Venda venda, int idCliente, String nomeCliente, ItemVenda item) {
        String sql = "INSERT INTO vendas (idVenda, dat, valorTotal, idProduto, nomeProduto, precoProduto, quatidade, idCliente, nomeCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement comandoPreparado = null;
        Produto produto = item.getProduto();
        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);

            comandoPreparado.setInt(1, venda.getId());
            comandoPreparado.setDate(2, new java.sql.Date(venda.getData().getTime()));  // ano-mes-dia
            comandoPreparado.setDouble(3, venda.getValorTotal());

            comandoPreparado.setInt(4, produto.getId());
            comandoPreparado.setString(5, produto.getNome());
            comandoPreparado.setDouble(6, produto.getPreco());
            comandoPreparado.setInt(7, item.getQuantidade());

            comandoPreparado.setInt(8, idCliente);
            comandoPreparado.setString(9, nomeCliente);

            comandoPreparado.executeUpdate();
            System.out.println("Venda cadastrada com sucesso!");

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
            return 1; // fallback
        }
    }


   
    public static void atualizarEstoque(int id, int novoEstoque) {
        String sql = "UPDATE produtos SET qtdEstoque = ? WHERE id = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, novoEstoque);
            comandoPreparado.setInt(2, id);
            comandoPreparado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disponibilidade do produto.");
            e.printStackTrace();
        }
    }

    public static void reporEstoque(int id, int quantidade) {
        String sql = "UPDATE produtos SET qtdEstoque = qtdEstoque + ? WHERE id = ?";
        PreparedStatement comandoPreparado = null;
            try {
            comandoPreparado= Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, quantidade);
            comandoPreparado.setInt(2, id);
            comandoPreparado.executeUpdate();
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
        } catch (SQLException e) {
            System.out.println("Erro ao reduzir estoque.");
            e.printStackTrace();
        }
    }

}