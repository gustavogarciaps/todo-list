package view;

import controller.GerenciamentoTarefas;
import model.Categoria;
import model.Status;
import model.Tarefa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

    private static GerenciamentoTarefas tarefas = new GerenciamentoTarefas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main (String [] args){
        exibirMenu();
    }

    public static void exibirMenu(){

        int opcao = 0;

        while (true) {

            System.out.println("TODO List - Opções");
            System.out.println("1 - Lista de Tarefas");
            System.out.println("2 - Criar nova Tarefa");
            System.out.println("3 - Tarefas que Vencem HOJE");
            System.out.println("4 - Finalizar aplicação");

            System.out.print("Número da opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Opção inválida");
            }

            switch (opcao) {
                case 1 -> {
                    try {
                        exibirTarefas();
                    } catch (Exception e) {
                        System.out.println("Erro ao listar tarefas. Tente novamente.");
                    }
                }
                case 2 -> {
                    try {
                        criarTarefa();
                    } catch (Exception e) {
                        System.out.println("Erro ao criar tarefas. Tente novamente.");
                        scanner.nextLine();
                    }
                }
                case 3 -> {
                    System.out.println("Tarefas com vencimento HOJE");
                    tarefas.getTarefasVencimentoHoje().forEach( (item) -> {
                        System.out.println( "TAREFA: " + item.getNome() +
                                "\t VENCE HOJE, ÀS: " + item.getHoraConclusao() + " HORAS.");
                    });
                }
                case 4 -> {
                    tarefas.saveTarefas();
                    return;
                }
                default -> {
                    System.out.println("Opção inválida");
                }
            }
        }

    }

    public static void exibirTarefas() {

        if(tarefas.getTarefas().isEmpty()){
            System.out.println("********** Não há Tarefas criadas.");
            return;
        }

        int opcao = 0;

        while (true) {
            System.out.println("Ordenar por:");
            System.out.println("1 - Prioridade");
            System.out.println("2 - Categoria");
            System.out.println("3 - Status");
            System.out.println("4 - Data");
            System.out.println("Funções:");
            System.out.println("5 - Excluir Tarefa");
            System.out.println("6 - Retornar");

            System.out.print("Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Opção inválida");
            }

            switch (opcao) {
                case 1 -> {
                    try {
                        tarefas.getTarefasOrdenadasPrioridade().forEach( (item) -> {
                            System.out.println("Tarefa: " + item);
                        });
                    } catch (Exception e) {
                        System.out.println("Erro ao ordenar tarefas.");
                    }
                }
                case 2 -> {
                    try {
                        tarefas.getTarefasOrdenadasCategoria().forEach( (item) -> {
                            System.out.println("Tarefa: " + item);
                        });
                    } catch (Exception e) {
                        System.out.println("Erro ao ordenar tarefas.");
                    }
                }
                case 3 -> {
                    try {
                        tarefas.getTarefasOrdenadasStatus().forEach( (item) -> {
                            System.out.println("Tarefa: " + item);
                        });
                    } catch (Exception e) {
                        System.out.println("Erro ao ordenar tarefas.");
                    }
                }
                case 4 -> {
                    try {
                        tarefas.getTarefasOrdenadasData().forEach( (item) -> {
                            System.out.println("Tarefa: " + item);
                        });
                    } catch (Exception e) {
                        System.out.println("Erro ao ordenar tarefas.");
                    }
                }
                case 5 -> {
                    System.out.println("Digite o número da Tarefa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        excluirTarefas(id);
                    } catch (Exception e) {
                        System.out.println("Erro ao excluir tarefa.");
                    }
                }
                case 6 -> {
                    return;
                }
                default -> {
                    System.out.println("Opção inválida");
                }
            }

        }

    }
    public static void criarTarefa() {

        System.out.println("Criar uma nova tarefa:");

        System.out.print("Nome: ");
        String titulo = scanner.nextLine().toUpperCase();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().toUpperCase();

        System.out.print("Prioridade (1 - 5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Condições:");
        for (int i = 0; i < Status.values().length; i++) {
            System.out.println(i + ". " + Status.values()[i]);
        }
        System.out.print("Escolha a condição (digite o número) (0 -" + (Status.values().length - 1) + "): ");

        int escolhaCondicao = scanner.nextInt();
        Status status = Status.values()[escolhaCondicao];
        scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().toUpperCase();

        System.out.print("Data do Término (dd/mm/yyyy) : ");
        String dataStr = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Alarme (true/false): ");
        boolean alarme = scanner.nextBoolean();
        scanner.nextLine();

        LocalTime hora = null;

        if(alarme == true){
            System.out.print("Hora de Conclusão (HH:MM): ");
            String horaStr = scanner.nextLine();

            hora = LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm"));

        }

        tarefas.setTarefas(new Tarefa(titulo, descricao, prioridade, status, new Categoria(categoria), data, alarme, hora));

        System.out.println("\nTarefa criada com sucesso");
    }

    public static void excluirTarefas(int id) {

        tarefas.getTarefas().forEach((item) -> {
            if(item.getIdObjeto() == id){
                System.out.println("Deseja remover essa tarefa? " + item);
                System.out.print("sim/nao: ");
                String opcao = scanner.nextLine().toUpperCase();

                if(opcao.contains("S")){
                    tarefas.getTarefas().remove(item);
                    System.out.println("Tarefa removida");
                } else {
                    System.out.println("Erro ao excluir tarefa.");
                    return;
                }
            }
        });
    }

}
