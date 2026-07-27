import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Agendamento> agendamentos = new ArrayList<>();
    private static int contadorClienteId = 1;
    private static int contadorAgendamentoId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarCliente();
                case 4 -> editarCliente();
                case 5 -> removerCliente();
                case 6 -> cadastrarAgendamento();
                case 7 -> listarAgendamentos();
                case 8 -> buscarAgendamento();
                case 9 -> editarAgendamento();
                case 10 -> cancelarAgendamento();
                case 0 -> System.out.println("Saindo do sistema... Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("========= SISTEMA DE BARBEARIA =========");
        System.out.println("--- GESTÃO DE CLIENTES ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Buscar Cliente");
        System.out.println("4. Editar Cliente");
        System.out.println("5. Remover Cliente");
        System.out.println("--- GESTÃO DE AGENDAMENTOS ---");
        System.out.println("6. Cadastrar Agendamento");
        System.out.println("7. Listar Agendamentos");
        System.out.println("8. Buscar Agendamento");
        System.out.println("9. Editar Agendamento");
        System.out.println("10. Cancelar Agendamento");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // --- CLIENTE CRUD ---

    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Cliente novoCliente = new Cliente(contadorClienteId++, nome, telefone, email);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso! ID: " + novoCliente.getId());
    }

    private static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    private static void buscarCliente() {
        System.out.println("\n--- Buscar Cliente ---");
        System.out.print("Digite o ID ou o Nome do cliente: ");
        String busca = scanner.nextLine();

        boolean encontrado = false;
        for (Cliente c : clientes) {
            if (String.valueOf(c.getId()).equals(busca) || c.getNome().equalsIgnoreCase(busca)) {
                System.out.println(c);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum cliente encontrado com os dados informados.");
        }
    }

    private static void editarCliente() {
        System.out.println("\n--- Editar Cliente ---");
        System.out.print("Digite o ID do cliente a editar: ");
        int id = lerInt();
        Cliente c = encontrarClientePorId(id);

        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo Nome (" + c.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isBlank()) c.setNome(novoNome);

        System.out.print("Novo Telefone (" + c.getTelefone() + "): ");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.isBlank()) c.setTelefone(novoTelefone);

        System.out.print("Novo E-mail (" + c.getEmail() + "): ");
        String novoEmail = scanner.nextLine();
        if (!novoEmail.isBlank()) c.setEmail(novoEmail);

        System.out.println("Cliente atualizado com sucesso!");
    }

    private static void removerCliente() {
        System.out.println("\n--- Remover Cliente ---");
        System.out.print("Digite o ID do cliente a remover: ");
        int id = lerInt();
        Cliente c = encontrarClientePorId(id);

        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Validação: Impedir remover cliente com agendamentos ativos
        for (Agendamento a : agendamentos) {
            if (a.getCliente().getId() == id && a.getStatus().equalsIgnoreCase("Ativo")) {
                System.out.println("ERRO: Não é possível remover o cliente pois ele possui agendamentos ativos!");
                return;
            }
        }

        clientes.remove(c);
        System.out.println("Cliente removido com sucesso!");
    }

    // --- AGENDAMENTO CRUD ---

    private static void cadastrarAgendamento() {
        System.out.println("\n--- Cadastrar Agendamento ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }

        System.out.print("Digite o ID do cliente: ");
        int idCliente = lerInt();
        Cliente cliente = encontrarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Data e Hora (ex: 28/07/2026 14:00): ");
        String dataHora = scanner.nextLine();

        // Validação: Impedir horários duplicados
        for (Agendamento a : agendamentos) {
            if (a.getDataHora().equalsIgnoreCase(dataHora) && a.getStatus().equalsIgnoreCase("Ativo")) {
                System.out.println("ERRO: Já existe um agendamento ativo para esse horário (" + dataHora + ")!");
                return;
            }
        }

        System.out.print("Serviço (ex: Cabelo, Barba, Completo): ");
        String servico = scanner.nextLine();

        System.out.print("Valor (R$): ");
        double valor = lerDouble();

        Agendamento novoAgendamento = new Agendamento(contadorAgendamentoId++, cliente, servico, dataHora, valor);
        agendamentos.add(novoAgendamento);
        System.out.println("Agendamento realizado com sucesso! ID: " + novoAgendamento.getId());
    }

    private static void listarAgendamentos() {
        System.out.println("\n--- Lista de Agendamentos ---");
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
            return;
        }
        for (Agendamento a : agendamentos) {
            System.out.println(a);
        }
    }

    private static void buscarAgendamento() {
        System.out.println("\n--- Buscar Agendamento ---");
        System.out.print("Digite o ID do agendamento ou nome do cliente: ");
        String busca = scanner.nextLine();

        boolean encontrado = false;
        for (Agendamento a : agendamentos) {
            if (String.valueOf(a.getId()).equals(busca) ||
               (a.getCliente() != null && a.getCliente().getNome().equalsIgnoreCase(busca))) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum agendamento encontrado com os critérios fornecidos.");
        }
    }

    private static void editarAgendamento() {
        System.out.println("\n--- Editar Agendamento ---");
        System.out.print("Digite o ID do agendamento a editar: ");
        int id = lerInt();
        Agendamento a = encontrarAgendamentoPorId(id);

        if (a == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        System.out.print("Nova Data/Hora (" + a.getDataHora() + "): ");
        String novaDataHora = scanner.nextLine();
        if (!novaDataHora.isBlank()) {
            // Validação de choque de horário na edição
            for (Agendamento ag : agendamentos) {
                if (ag.getId() != id && ag.getDataHora().equalsIgnoreCase(novaDataHora) && ag.getStatus().equalsIgnoreCase("Ativo")) {
                    System.out.println("ERRO: Já existe outro agendamento ativo para esse horário!");
                    return;
                }
            }
            a.setDataHora(novaDataHora);
        }

        System.out.print("Novo Serviço (" + a.getServico() + "): ");
        String novoServico = scanner.nextLine();
        if (!novoServico.isBlank()) a.setServico(novoServico);

        System.out.print("Novo Valor (" + a.getValor() + "): ");
        String valStr = scanner.nextLine();
        if (!valStr.isBlank()) {
            try {
                a.setValor(Double.parseDouble(valStr));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Mantido valor anterior.");
            }
        }

        System.out.println("Agendamento atualizado com sucesso!");
    }

    private static void cancelarAgendamento() {
        System.out.println("\n--- Cancelar Agendamento ---");
        System.out.print("Digite o ID do agendamento a cancelar: ");
        int id = lerInt();
        Agendamento a = encontrarAgendamentoPorId(id);

        if (a == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        if (a.getStatus().equalsIgnoreCase("Cancelado")) {
            System.out.println("Este agendamento já se encontra cancelado.");
            return;
        }

        a.setStatus("Cancelado");
        System.out.println("Agendamento cancelado com sucesso!");
    }

    // --- MÉTODOS AUXILIARES ---

    private static Cliente encontrarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    private static Agendamento encontrarAgendamentoPorId(int id) {
        for (Agendamento a : agendamentos) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    private static int lerInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
