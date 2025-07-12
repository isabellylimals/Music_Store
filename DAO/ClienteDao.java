package DAO;

import src.models.Cliente;
import java.sql.SQLException;
import src.conection.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDao {
    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO clientes (id, nome, email, senha, telefone, administrador) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement comandoPreparado  = null;

        try {
            comandoPreparado  = Conexao.getConexao().prepareStatement(sql);

            comandoPreparado .setInt(1, cliente.getId());
            comandoPreparado .setString(2, cliente.getNome());
            comandoPreparado .setString(3, cliente.getEmail());
            comandoPreparado .setString(4, cliente.getSenha());
            comandoPreparado .setString(5, cliente.getTelefone());
            comandoPreparado .setBoolean(6, cliente.isAdministrador());

            comandoPreparado.executeUpdate();
            System.out.println("Cliente inserido com sucesso no banco!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente no banco: ");
            e.printStackTrace();
        }
    }

    public int obterId() {
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
            return 1; // fallback
        }
    }

    public Cliente buscar(int id) {
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

}
