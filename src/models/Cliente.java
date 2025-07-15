package src.models;

import DAO.ClienteDao;
import java.util.ArrayList;
import java.util.List;

/*
*Classe para representar um cliente
*Cliente é um tipo de pessoa
*Cliente contem um telefone e os atributos de Pessoa
*/

public class Cliente extends Pessoa {
    private String telefone;
    private static List<Cliente> listaDeClientes = new ArrayList<>();



    public Cliente(String nome, String email, String senha, String telefone, boolean status) {
        super(nome, email, ClienteDao.obterId(), senha, false);
        this.telefone = telefone;
        
    }

       public void setCliente(Cliente cliente) {
        this.setId(cliente.getId());
        this.setNome(cliente.getNome());
        this.setEmail(cliente.getEmail());
        this.setSenha(cliente.getSenha());
        this.telefone = cliente.getTelefone();
    
       }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
public String getTelefone() {
        return telefone;
    }
    public  void cadastrarCliente(String nome, String email, String senha, String telefone, boolean status) {
        Cliente novoCliente = new Cliente(nome, email, senha, telefone, status);

        listaDeClientes.add(novoCliente);

        ClienteDao.cadastrar(novoCliente);

        System.out.println("Cliente cadastrado com sucesso! ID: " + novoCliente.getId());
    }

    public Cliente buscarPorId(int id) {
        return ClienteDao.buscar(id);
    }

    public void exibirDadosCadastrais(int id) {
        Cliente clienteEncontrado = buscarPorId(id);

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

    public void consultarTodosClientes() {
        if (listaDeClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente cliente : listaDeClientes) {
            System.out.print(
                    "ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail()
                            + "\n");
        }
        System.out.println("Total: " + listaDeClientes.size() + " clientes");
    }

    public void alterarCadastro(int id, String novoNome, String novoEmail, String novaSenha, String novoTelefone) {
        Cliente clienteEncontrado = buscarPorId(id);
        if (clienteEncontrado != null) {
            clienteEncontrado.setNome(novoNome);
            clienteEncontrado.setEmail(novoEmail);
            clienteEncontrado.setSenha(novaSenha);
            clienteEncontrado.setTelefone(novoTelefone);
            System.out.println("Cadastro alterado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    public void removerCliente(int id) {
        Cliente clienteEncontrado = buscarPorId(id);
        if (clienteEncontrado != null) {
            listaDeClientes.remove(clienteEncontrado);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    @Override
    public String toString() {
        String informacoesDoCliente = super.toString();
        informacoesDoCliente += "Número de telefone: " + getTelefone() + "\n";
        return informacoesDoCliente;
    }
}
