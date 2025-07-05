package src.models;
import java.util.ArrayList;
import java.util.List;

/*
*Classe para representar um cliente
*Cliente é um tipo de pessoa
*Cliente contem um telefone e os atributos de Pessoa
*/

public class Cliente extends Pessoa {
    private String telefone;
    private static int proximoId = 1;
    
    private static List<Cliente> listaDeClientes = new ArrayList<>();

    public Cliente(String nome, String email, String senha, String telefone) {
        super(nome, email, proximoId++, senha, false);
        this.telefone = telefone;
    }

    public Cliente(){
        super("", "", 0, "", false);
        this.telefone = "";
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
        System.out.println("Cliente cadastrado com sucesso! ID: " + novoCliente.getId());
    }

    private Cliente buscarPorId(int id) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void exibirDadosCadastrais(int id) {
        Cliente clienteEncontrado = buscarPorId(id);

        if (clienteEncontrado != null) {
            System.out.println("\nInformações do cliente:");
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
                    "ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail() + "\n");
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
