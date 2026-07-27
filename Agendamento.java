public class Agendamento {

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	private  Cliente cliente;
	private String data;
	private String horario;
	private String servico;

public Agendamento(Cliente cliente,String data,String horario,String servico) {
	this.cliente = cliente;
	this.data = data;
	this.horario = horario;
	this.servico = servico;
}
public void exibirAgendamento() {
	System.out.println("Cliente :" + cliente.getNome());
	System.out.println("Telefone :" + cliente.getTelefone());
	System.out.println("Data : " + this.data);
	System.out.println("Horario :" + this.horario);
	System.out.println("Serviço:" + this.servico);
	
}

}
