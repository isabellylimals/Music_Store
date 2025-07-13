package src.models;
/*Classe para representar Pessoa
*Pessoa contem nome, email, id, senha e administrador
*/

public class Pessoa {
  private String nome;
  private String email;
  private int id;
  private String senha;
  private boolean administrador; //true: administrador , false: cliente
  private boolean ativo;

  public Pessoa(String nome, String email, int id, String senha, boolean administrador) {
    this.nome = nome;
    this.email = email;
    this.id = id;
    this.senha = senha;
    this.administrador = administrador;
    this.ativo=true;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email ==null || !email.contains("@")){
      throw new IllegalArgumentException("Email inválido");
    }
    this.email = email;
  }

  public void setAdministrador(boolean administrador) {
    this.administrador = administrador;
  }

  public String getAdministrador() {
    return administrador ? "Sim" : "Nao";
  }

  public boolean isAdministrador() {
    return administrador;
  }

  public void setAdministrador(String administrador) {
    this.administrador = "Sim".equalsIgnoreCase(administrador);
  }

  public boolean isAtivo(){
    return ativo;
  }

  public void ativaConta(){
    this.ativo=true;
    System.out.println("Conta ativa com sucesso!");
  }

  public String getStatus() {
      return ativo ? "Ativo" : "Inativo";
  }

  public boolean login(String email, String senha) {
      if (!this.ativo) {
          System.out.println("Login falhou: conta desativada");
          return false;
      }
      return this.email.equals(email) && this.senha.equals(senha);
  }

  public void criarNovoLogin(String email, String senha) {
    this.email = email;
    this.senha = senha;
  }

  @Override
  public String toString() {
    String resultado = "";
    resultado += "ID: " + getId() + "\n";
    resultado += "Nome completo: " + getNome() + "\n";
    resultado += "Endereço de e-mail: " + getEmail() + "\n";
    resultado += "Perfil de acesso: " + (isAdministrador() ? "Administrador" : "Cliente");
    resultado += "Status da conta: " + getStatus();
    return resultado;
  }
}