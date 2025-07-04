package models;

import java.util.ArrayList;
import java.util.List;

/*
*Classe para representar um cliente
*Cliente é um tipo de pessoa
*Cliente contem um telefone e os atributos de Pessoa
*/

public class Cliente extends Pessoa{
    private String telefone;
    private static int proximoId = 1;

	  public Cliente(String nome, String email, int id, String senha, String telefone){
		  super(nome, email,id, senha, false);
		  this.telefone=telefone;
    }

    public Cliente(){
    }

    public String getTelefone( ){
	    return telefone;
    }

    public String setTelefone(String telefone){
	    this.telefone=telefone;
    }
	
    public void cadastrarCliente(String nome, String email, String senha, String telefone) {
      Cliente novoCliente = new Cliente( nome, email, senha, telefone);
      clientes.add(novoCliente);
  	  System.out.println("Cliente cadastrado com sucesso! ID: " + novoCliente.getId());
    }

    public void exibirDadosCadastrais(int id) {
      Cliente cliente = buscarPorId(id);
      if (cliente != null) {
          System.out.println("\nInformacoes do cliente");
        	System.out.println("ID: " + cliente.getId());
        	System.out.println("Nome: " + cliente.getNome());
          System.out.println("Email: " + cliente.getEmail());
        	System.out.println("Telefone: " + cliente.getTelefone());
        } else {
          System.out.println("Cliente não encontrado!");
        }
    }
   
    public void consultarTodosClientes() {
      System.out.println("\n");
      for (Cliente c : clientes) {
          System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | Email: " + c.getEmail());
      }
      System.out.println("Total: " + clientes.size() + " clientes");
    }

    
    public void alterarCadastro(int id, String novoNome, String novoEmail, String novaSenha, String novoTelefone) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setEmail(novoEmail);
            cliente.setSenha(novaSenha);
            cliente.setTelefone(novoTelefone);
            System.out.println("Cadastro alterado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    public void removerCliente(int id) {
       	 Cliente cliente = buscarPorId(id);
     	   if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
  
    @Override
	  public String toString(){
		  String s=super.toString();
      s+="Telefone: " + getTelefone() +"\n";
		  return s;
    }
}
