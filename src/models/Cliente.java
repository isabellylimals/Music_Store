package src.models;

import java.util.ArrayList;
import java.util.List;

import DAO.ClienteDao;

/*
*Classe para representar um cliente
*Cliente é um tipo de pessoa
*Cliente contem um telefone e os atributos de Pessoa
*/

public class Cliente extends Pessoa {
    private String telefone;
    private static List<Cliente> listaDeClientes = new ArrayList<>();

    public Cliente(String nome, String email, String senha, String telefone) {
        super(nome, email, ClienteDao.obterId(), senha, false);
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void cadastrarCliente(String nome, String email, String senha, String telefone) {
        Cliente novoCliente = new Cliente(nome, email, senha, telefone);

        listaDeClientes.add(novoCliente);

        ClienteDao.cadastrar(novoCliente);

        System.out.println("Cliente cadastrado com sucesso! ID: " + novoCliente.getId());
    }

    public Cliente buscarClientePorId(int id) {
        return ClienteDao.buscar(id);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void exibirDadosCadastrais(int id) {
        Cliente clienteEncontrado = buscarClientePorId(id);

        if (clienteEncontrado != null) {
            System.out.println("Informações do cliente:");
            System.out.println("ID: " + clienteEncontrado.getId());
            System.out.println("Nome completo: " + clienteEncontrado.getNome());
            System.out.println("Endereço de e-mail: " + clienteEncontrado.getEmail());
            System.out.println("Número de telefone: " + clienteEncontrado.getTelefone());

        } else {
            System.out.println("Cliente não encontrado!");
        }
    }


    public void alterarCadastro(int id, String novoNome, String novoEmail, String novaSenha, String novoTelefone) {
        Cliente clienteEncontrado = ClienteDao.buscar(id);
        if (clienteEncontrado != null) {
            clienteEncontrado.setNome(novoNome);
            clienteEncontrado.setEmail(novoEmail);
            clienteEncontrado.setSenha(novaSenha);
            clienteEncontrado.setTelefone(novoTelefone);

            boolean sucesso = ClienteDao.atualizarCliente(clienteEncontrado);
            if (sucesso) {
                System.out.println("Cadastro alterado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar cadastro no banco.");
            }
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    // ajustar.
    public void removerCliente(int id) {
        Cliente clienteEncontrado = ClienteDao.buscar(id);

        if (clienteEncontrado != null) {
            clienteEncontrado.setStatus(false);

            ClienteDao.atualizarStatus(clienteEncontrado.getId(), false);

            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void consultarTodosClientes() {
        listaDeClientes = ClienteDao.buscarTodos();

        if (listaDeClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("Lista de Clientes:");
        System.out.println("---------------------------------------------------------------");

        for (Cliente cliente : listaDeClientes) {
            System.out.printf("ID: %d | Nome: %s | Email: %s%n", cliente.getId(), cliente.getNome(), cliente.getEmail());
            System.out.printf("Telefone: %s | Status: %s%n",
                    cliente.getTelefone(), cliente.getStatus());
            System.out.println("---------------------------------------------------------------");
        }

        System.out.println("Total: " + listaDeClientes.size() + " clientes");

    }

    @Override
    public String toString() {
        String informacoesDoCliente = super.toString();
        informacoesDoCliente += "Número de telefone: " + getTelefone() + "\n";
        return informacoesDoCliente;
    }
}
