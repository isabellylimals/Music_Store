/*Classe para representar Pessoa
*Pessoa contem nome, email, id, senha e administrador
*/
public class Pessoa{
	private String nome;
  private String email;
	private int id;
	private String senha;
	private boolean administrador; //true: administrador , false: cliente

	public Pessoa(String nome, String email, Int id, String senha, boolean administrador){
		this.nome=nome;
		this.email=email;
    this.id=id;
    this.senha=senha;
		this.administrador=administrador;
  }

  public Pessoa( ){
    
  }

  public String setNome(String nome){
    this.nome=nome;
  }

  public void getNome(){
	  return nome;
  }

  public String setEmail( String Email){
	  this.email=email;
  }

  public String getEmail( ){
	  return email;
  }

  public void setAdminstrador(boolean administrador){
    this.administrador=administrador;
  }

  public String getAdministrador(){
	  return administrador ? “S”;”N”;
  }

  public boolean isAdministrador( ) {
	  return administrador;
  }

  public void setAdministrador(String administrador){
	  this.administrador=administrador.equals(“S”)? true: false;
  }

  public boolean login(String email, String senha) {
	  return this.email.equals(email) && this.senha.equals(senha);
  }

  public void criarNovoLogin(String email, String senha){
	  this.email=email;
    this.senha=senha;
  }

  @Override
	  public String toString(){
		  String s =””;
		  s+=”ID: ”+ id;
		  s+= “Cliente:  ”+ nome() + “\n”;
		  s+=”Email: ” + email() +”\n”;
		  s+=”Administrador: ”+administrador;
		  return s;
	  }
}
