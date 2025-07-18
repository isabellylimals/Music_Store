
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.conection.Conexao;
import src.models.Cliente;

public class ClienteDao {

    public static void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO clientes (id, nome, email, senha, telefone, administrador, stats) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);

            comandoPreparado.setInt(1, cliente.getId());
            comandoPreparado.setString(2, cliente.getNome());
            comandoPreparado.setString(3, cliente.getEmail());
            comandoPreparado.setString(4, cliente.getSenha());
            comandoPreparado.setString(5, cliente.getTelefone());
            comandoPreparado.setBoolean(6, cliente.isAdministrador());
            comandoPreparado.setBoolean(7, cliente.isAtivo());

            comandoPreparado.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente no banco: ");
            e.printStackTrace();
        }
    }

    public static int obterId() {
        String sql = "SELECT MAX(id) AS max_id FROM clientes";
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

    public static Cliente buscar(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, id);
            ResultSet resultado = comandoPreparado.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String senha = resultado.getString("senha");
                String telefone = resultado.getString("telefone");
                boolean administrador = resultado.getBoolean("administrador");

                Cliente cliente = new Cliente(nome, email, senha, telefone);
                cliente.setId(id);
                cliente.setAdministrador(administrador);

                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Cliente buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM clientes WHERE email = ? AND senha = ?";
        PreparedStatement comandoPreparado = null;
        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setString(1, email);
            comandoPreparado.setString(2, senha);
            ResultSet resultado = comandoPreparado.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                boolean administrador = resultado.getBoolean("administrador");
                Cliente cliente = new Cliente(nome, email, senha, telefone);
                cliente.setId(id);
                cliente.setAdministrador(administrador);

                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        PreparedStatement comandoPreparado = null;

        try {

            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultado = comandoPreparado.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String senha = resultado.getString("senha");
                String telefone = resultado.getString("telefone");
                boolean administrador = resultado.getBoolean("administrador");
                boolean stats = resultado.getBoolean("stats");

                Cliente cliente = new Cliente(nome, email, senha, telefone);
                cliente.setStatus(stats);
                cliente.setId(id);
                cliente.setAdministrador(administrador);

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public static void atualizarStatus(int id, boolean status) {
        String sql = "UPDATE clientes SET stats = ? WHERE id = ?";
        PreparedStatement comandoPreparado = null;

        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setBoolean(1, status);
            comandoPreparado.setInt(2, id);
            comandoPreparado.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do cliente.");
            e.printStackTrace();
        }
    }

    public static void atualizarStatusConformeAtividade(int id) {
        String sql = "SELECT stats FROM clientes WHERE id = ?";
        PreparedStatement comandoPreparado = null;
        try {
            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setInt(1, id);
            ResultSet resultado = comandoPreparado.executeQuery();

            if (resultado.next()) {
                boolean statusConta = resultado.getBoolean("stats");
                atualizarStatus(id, !statusConta);
                boolean novoStatus = !statusConta;
                String mensagem = novoStatus ? "Conta ativada com sucesso." : "Conta desativada com sucesso.";
                System.out.println(mensagem);

            } else {
                System.out.println("Cliente com id " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do cliente.");
            e.printStackTrace();
        }
    }

    public static boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id = ?";

        PreparedStatement comandoPreparado = null;

        try {

            comandoPreparado = Conexao.getConexao().prepareStatement(sql);
            comandoPreparado.setString(1, cliente.getNome());
            comandoPreparado.setString(2, cliente.getEmail());
            comandoPreparado.setString(3, cliente.getSenha());
            comandoPreparado.setString(4, cliente.getTelefone());
            comandoPreparado.setInt(5, cliente.getId());

            int linhasAfetadas = comandoPreparado.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
