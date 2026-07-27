import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("=========== BARBEARIA ===========");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Agendar serviço");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar agendamentos");
            System.out.println("5 - Cancelar agendamento");
            System.out.println("6 - Remover cliente");
            System.out.println("7 - Editar cliente");
            System.out.println("8 - Editar agendamento");
            System.out.println("9 - Buscar cliente");
            System.out.println("10 - Buscar agendamento por cliente");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {

                case 1:

                    System.out.println("Cadastrar Cliente");

                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite seu telefone: ");
                    String telefone = sc.nextLine();

                    Cliente cliente = new Cliente(nome, telefone);
                    clientes.add(cliente);

                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                	System.out.println("Agendar serviço");
                	
                	if(clientes.size()==0){
                	   System.out.println("Nenhum cliente cadastrado");
                	   break;
                   }
 
                   for(int i=0; i< clientes.size(); i++){
                   	System.out.println((i+1) + "-" + clientes.get(i).getNome());                  
       
                    }
                   System.out.println("Escolha o cliente");
                   int escolha = sc.nextInt();
                   sc.nextLine();

                   if (escolha < 1 || escolha > clientes.size()) {
                       System.out.println("Cliente inválido.");
                       break;
                   }

                   Cliente clienteEscolhido = clientes.get(escolha - 1);
     
                   System.out.println("Digite a data: ");
                   String data = sc.nextLine();
           
                   System.out.println("Digite o horário: ");
                   String horario = sc.nextLine();
                   
                   System.out.println("Digite o serviço: ");
                   String servico = sc.nextLine();
                   
                   boolean horarioOcupado = false;

                   for (int i = 0; i < agendamentos.size(); i++) {

                       Agendamento a = agendamentos.get(i);

                       if (a.getData().equalsIgnoreCase(data)
                               && a.getHorario().equalsIgnoreCase(horario)) {

                           horarioOcupado = true;
                           break;
                       }
                   }

                   if (horarioOcupado) {
                       System.out.println("Já existe um agendamento para esse horário.");
                       break;
                   }
                   Agendamento agendamento = new Agendamento(
                		   clienteEscolhido,
                		   data,
                		   horario,
                		   servico  );  		
                  
                   agendamentos.add(agendamento);
                   System.out.println("Agendamento realizado com sucesso");
                   
                    
                  break;

                case 3:
                    System.out.println("Listar Clientes");

                    for (int i = 0; i < clientes.size(); i++) {
                        Cliente c = clientes.get(i);
                        c.exibirDados();
                        System.out.println("-----------");
                    }

                    break;

                case 4:
                    System.out.println("Listar agendamentos");
                    
                  if(agendamentos.size() == 0) {
                	  System.out.println("Nenhum agendamento cadastrado");
                	 break;
                  }  
                    for(int i =0; i<agendamentos.size();i++) {
                    	Agendamento a = agendamentos.get(i);
                    	a.exibirAgendamento();
                    	System.out.println("-----------------");
                    }
                  
                  break;
                  
                case 5:
                	System.out.println("Cancelar agendamento");
                	
                	if(agendamentos.size()==0) {
                	System.out.println("Nenhum agendamento");
                	break;
                	}
 
                	for(int i=0; i <agendamentos.size(); i++) {
                		System.out.println((i+ 1)+ "-");
                		
                		Agendamento b = agendamentos.get(i);
                		b.exibirAgendamento();
                	}
                	System.out.println("Qual agendamento deseja cancelar?");
                	int c = sc.nextInt();
                	
                	if (c >= 1 && c <= agendamentos.size()) {
                	    agendamentos.remove(c - 1);
                	    System.out.println("Agendamento cancelado com sucesso!");
                	} else {
                	    System.out.println("Opção inválida!");
                	}
                	
                	break;
                	
                case 6:
                    System.out.println("Remover cliente");

                    if (clientes.size() == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    // Lista os clientes
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + " - " + clientes.get(i).getNome());
                    }

                    System.out.print("Qual cliente deseja remover? ");
                    int remove = sc.nextInt();
                    sc.nextLine();

                    if (remove < 1 || remove > clientes.size()) {
                        System.out.println("Cliente inválido.");
                        break;
                    }

                    Cliente clienteEscolhido1 = clientes.get(remove - 1);

                    boolean possuiAgendamento = false;

                    // Verifica se o cliente possui algum agendamento
                    for (int i = 0; i < agendamentos.size(); i++) {

                        Agendamento agendamento1 = agendamentos.get(i);

                        if (agendamento1.getCliente() == clienteEscolhido1) {
                            possuiAgendamento = true;
                            break;
                        }
                    }

                    if (possuiAgendamento) {
                        System.out.println("Este cliente possui um agendamento e não pode ser removido.");
                    } else {
                        clientes.remove(remove - 1);
                        System.out.println("Cliente removido com sucesso!");
                    }

                    break;
                 
                case 7:
            

                    System.out.println("Editar cliente");

                    if (clientes.size() == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + " - " + clientes.get(i).getNome());
                    }

                    System.out.print("Escolha o cliente: ");
                    int editar = sc.nextInt();
                    sc.nextLine();

                    if (editar < 1 || editar > clientes.size()) {
                        System.out.println("Cliente inválido.");
                        break;
                    }

                    Cliente clienteEditar = clientes.get(editar - 1);

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();

                    System.out.print("Novo telefone: ");
                    String novoTelefone = sc.nextLine();

                    clienteEditar.setNome(novoNome);
                    clienteEditar.setTelefone(novoTelefone);

                    System.out.println("Cliente atualizado com sucesso!");

                    break;
                	
                case 8:
                    
                	System.out.println("Editar agendamento");
                	
                	if(agendamentos.size()== 0) {
                		System.out.println("Nenhum agendamento cadastrado.");
                		break;
                	}
                for(int i=0; i< agendamentos.size(); i++) {
                	System.out.println((i+1)+ "-"+ agendamentos.get(i).getCliente().getNome());
                }
                	System.out.println("Escolha o agendamento :");
                	int editarAgendamento = sc.nextInt();
                	sc.nextLine();
                	
                if(editarAgendamento< 1 || editarAgendamento > agendamentos.size()) {
                	System.out.println("Agendamento invalido");
                	break;
                }	
                
                Agendamento agendamentoEditar = agendamentos.get(editarAgendamento -1);
                
                
                System.out.println("Nova data :");
                String novaData = sc.nextLine();
                
                System.out.println("Novo horário:");
                String novoHorario = sc.nextLine();
                
                boolean horarioOcupado1 = false;

                for (int i = 0; i < agendamentos.size(); i++) {

                    Agendamento a = agendamentos.get(i);

                    // Ignora o próprio agendamento que está sendo editado
                    if (a != agendamentoEditar &&
                        a.getData().equalsIgnoreCase(novaData) &&
                        a.getHorario().equalsIgnoreCase(novoHorario)) {

                        horarioOcupado1 = true;
                        break;
                    }
                }

                if (horarioOcupado1) {
                    System.out.println("Já existe um agendamento nesse horário.");
                    break;
                }
                System.out.println("Novo serviço");
                String novoServico = sc.nextLine();
                
                agendamentoEditar.setData(novaData);
                agendamentoEditar.setHorario(novoHorario);
                agendamentoEditar.setServico(novoServico);
                
                System.out.println("Agendamento atualizado com sucesso!");
                
                break;
  
                case 9:

                    System.out.println("Buscar cliente");

                    if (clientes.size() == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    System.out.print("Digite o nome do cliente: ");
                    String nomeBusca = sc.nextLine();

                    boolean encontrado = false;

                    for (int i = 0; i < clientes.size(); i++) {

                        Cliente clienteBusca = clientes.get(i);

                        if (clienteBusca.getNome().equalsIgnoreCase(nomeBusca)) {

                            clienteBusca.exibirDados();
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Cliente não encontrado.");
                    }

                    break;
                    
                case 10:

                    System.out.println("Buscar agendamento por cliente");

                    if (agendamentos.size() == 0) {
                        System.out.println("Nenhum agendamento cadastrado.");
                        break;
                    }

                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = sc.nextLine();

                    boolean encontrou = false;

                    for (int i = 0; i < agendamentos.size(); i++) {

                        Agendamento agendamentoBusca = agendamentos.get(i);

                        if (agendamentoBusca.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {

                            agendamentoBusca.exibirAgendamento();
                            System.out.println("----------------------");
                            encontrou = true;
                        }
                    }

                    if (!encontrou) {
                        System.out.println("Nenhum agendamento encontrado para esse cliente.");
                    }

                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;
                    
                  
                    
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
