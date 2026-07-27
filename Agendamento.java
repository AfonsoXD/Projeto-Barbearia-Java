public class Agendamento {
    private int id;
    private Cliente cliente;
    private String servico;
    private String dataHora;
    private double valor;
    private String status; // Ex: "Ativo", "Cancelado"

    public Agendamento(int id, Cliente cliente, String servico, String dataHora, double valor) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.dataHora = dataHora;
        this.valor = valor;
        this.status = "Ativo";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID Agendamento: " + id +
               " | Cliente: " + (cliente != null ? cliente.getNome() : "N/A") +
               " | Serviço: " + servico +
               " | Data/Hora: " + dataHora +
               " | Valor: R$ " + String.format("%.2f", valor) +
               " | Status: " + status;
    }
}
