package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.conection.Conexao;
import src.models.Produto;

public class ProdutoDao {
    public static void cadastrar(Produto produto) {
        String sql = "INSERT INTO produtos (id, nome, genero, artista, anoLancamento, preco, qtdEstoque, disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement comandoPreparado  = null;

        try {
            comandoPreparado  = Conexao.getConexao().prepareStatement(sql);

            comandoPreparado .setInt(1, produto.getId());
            comandoPreparado .setString(2, produto.getNome());
            comandoPreparado .setString(3, produto.getGenero());
            comandoPreparado .setString(4, produto.getArtista());
            comandoPreparado .setInt(5, produto.getAnoLancamento());
            comandoPreparado .setDouble(6, produto.getPreco());
            comandoPreparado .setInt(7, produto.getQtdEstoque());
            comandoPreparado .setBoolean(8, produto.isDisponivel());

            comandoPreparado.executeUpdate();
            

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto no banco: ");
            e.printStackTrace();
        }
    }

    public static int obterId() {
        String sql = "SELECT MAX(id) AS max_id FROM produtos";
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

    public static Produto buscar(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, id);
            ResultSet resultado = comandoPreparado.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String genero = resultado.getString("genero");
                String artista = resultado.getString("artista");
                int anoLancamento = resultado.getInt("anoLancamento");
                double preco = resultado.getDouble("preco");
                int qtdEstoque = resultado.getInt("qtdEstoque");
                boolean disponivel = resultado.getBoolean("disponivel");

                Produto produto = new Produto(nome, genero, artista, anoLancamento,preco,qtdEstoque);
                produto.setId(id); 
                produto.setDisponivel(disponivel);

                return produto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Produto> listarTodos() {
    List<Produto> produtos = new ArrayList<>();
    String sql = "SELECT * FROM produtos";
    PreparedStatement comandoPreparado = null;

    try {
        comandoPreparado = Conexao.getConexao().prepareStatement(sql);
        ResultSet resultado = comandoPreparado.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nome = resultado.getString("nome");
            String genero = resultado.getString("genero");
            String artista = resultado.getString("artista");
            int anoLancamento = resultado.getInt("anoLancamento");
            double preco = resultado.getDouble("preco");
            int qtdEstoque = resultado.getInt("qtdEstoque");
            boolean disponivel = resultado.getBoolean("disponivel");

            Produto p = new Produto(nome, genero, artista, anoLancamento, preco, qtdEstoque);
            p.setId(id);
            p.setDisponivel(disponivel);

            produtos.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return produtos;
}

public static void atualizarDisponibilidade(int id, boolean disponivel) {
    String sql = "UPDATE produtos SET disponivel = ? WHERE id = ?";
    PreparedStatement comandoPreparado = null;

    try {
        comandoPreparado = Conexao.getConexao().prepareStatement(sql);
        comandoPreparado.setBoolean(1, disponivel);
        comandoPreparado.setInt(2, id);
        comandoPreparado.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar disponibilidade do produto.");
        e.printStackTrace();
    }
}

public static boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, genero = ?, artista = ?, anoLancamento = ?, preco = ?, qtdEstoque = ?, disponivel = ? WHERE id = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);

            comandoPreparado.setString(1, produto.getNome());
            comandoPreparado.setString(2, produto.getGenero());
            comandoPreparado.setString(3, produto.getArtista());
            comandoPreparado.setInt(4, produto.getAnoLancamento());
            comandoPreparado.setDouble(5, produto.getPreco());
            comandoPreparado.setInt(6, produto.getQtdEstoque());
            comandoPreparado.setBoolean(7, produto.isDisponivel());
            comandoPreparado.setInt(8, produto.getId());

            int linhasAfetadas = comandoPreparado.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

public static List<Produto> buscarPorNome(String nomeBusca) {
    List<Produto> resultados = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE LOWER(nome) = LOWER(?)";
    PreparedStatement comandoPreparado = null;

    try {
        comandoPreparado = Conexao.getConexao().prepareStatement(sql);
        comandoPreparado.setString(1, nomeBusca);
        ResultSet resultado = comandoPreparado.executeQuery();

        while (resultado.next()) {
            Produto p = criarProdutoDoResultSet(resultado);
            resultados.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resultados;
}

public static List<Produto> buscarPorArtista(String artistaBusca) {
    List<Produto> resultados = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE LOWER(artista) = LOWER(?)";
    PreparedStatement comandoPreparado = null;

    try {
        comandoPreparado = Conexao.getConexao().prepareStatement(sql);
        comandoPreparado.setString(1, artistaBusca);
        ResultSet resultado = comandoPreparado.executeQuery();

        while (resultado.next()) {
            Produto p = criarProdutoDoResultSet(resultado);
            resultados.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resultados;
}

public static List<Produto> buscarPorGenero(String generoBusca) {
    List<Produto> resultados = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE LOWER(genero) = LOWER(?)";
    PreparedStatement comandoPreparado = null;

    try {
        comandoPreparado = Conexao.getConexao().prepareStatement(sql);
        comandoPreparado.setString(1, generoBusca);
        ResultSet resultado = comandoPreparado.executeQuery();

        while (resultado.next()) {
            Produto p = criarProdutoDoResultSet(resultado);
            resultados.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resultados;
}



private static Produto criarProdutoDoResultSet(ResultSet resultado) throws SQLException {
    int id = resultado.getInt("id");
    String nome = resultado.getString("nome");
    String genero = resultado.getString("genero");
    String artista = resultado.getString("artista");
    int ano = resultado.getInt("anoLancamento");
    double preco = resultado.getDouble("preco");
    int estoque = resultado.getInt("qtdEstoque");
    boolean disponivel = resultado.getBoolean("disponivel");

    Produto p = new Produto(nome, genero, artista, ano, preco, estoque);
    p.setId(id);
    p.setDisponivel(disponivel);

    return p;
    }
}


