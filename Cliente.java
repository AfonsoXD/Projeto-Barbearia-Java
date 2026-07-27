public class Cliente {
	private String nome;
	private String telefone;
	
	public void setNome(String nome) {
		this.nome = nome;
	}


	
	
public Cliente(String nome,String telefone) {
this.nome = nome;
this.telefone = telefone;
}


public String getNome() {
	return nome;
}

public  void exibirDados() {
	System.out.println("Nome: "+ nome);
	System.out.println("Telefone:"+ telefone);
}


public String getTelefone() {
	return telefone;
}


public void setTelefone(String telefone) {
	this.telefone = telefone;
}




}
