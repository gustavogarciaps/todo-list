package model;

import controller.GerenciamentoTarefas;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class TarefaTeste {

    private static GerenciamentoTarefas tarefas;

    @BeforeAll
    public void instanciarTarefa(){
        tarefas = new GerenciamentoTarefas();
    }

    @Test
    public void criarTarefaComAlarme(){

        Tarefa tarefa = new Tarefa();

        tarefa.setNome("Comprar Leite");
        tarefa.setDescricao("Comprar Leite no Mercado");
        tarefa.setPrioridade(1);
        tarefa.setStatus(null);
        tarefa.setCategoria(null);
        tarefa.setDataTermino(LocalDate.of(2023,9,15));
        tarefa.setAlarme(true);
        tarefa.setHoraConclusao(LocalTime.of(15,20));

        LocalTime expect = LocalTime.of(15,20);
        LocalTime result = tarefa.getHoraConclusao();

        System.out.println("Expect: " + expect);
        System.out.println("Result: " + result);

    }

    @Test
    public void adicionarNoGerenciador(){

        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Comprar Leite");

        tarefas.setTarefas(tarefa);
        System.out.println("Tamanho da Lista: " + tarefas.getTarefas().size());
    }

    @Test
    public void consultarPrazoVencimento(){

        Tarefa tarefa = new Tarefa();

        tarefa.setDataTermino(LocalDate.of(2023,9,15));
        tarefa.setAlarme(true);
        tarefa.setHoraConclusao(LocalTime.of(15,20));

        tarefas.setTarefas(tarefa);

        tarefas.ajustarAlarme();

        tarefas.getTarefas().forEach(
                (item) -> {
                    System.out.println(item);
                }
        );

    }

    @Test
    public void consultarSomenteTarefasComPrazo(){
        Tarefa tarefaComPrazo = new Tarefa();

        tarefaComPrazo.setNome("Comprar Arroz");
        tarefaComPrazo.setDataTermino(LocalDate.of(2023,9,15));
        tarefaComPrazo.setAlarme(true);
        tarefaComPrazo.setHoraConclusao(LocalTime.of(15,20));

        tarefas.setTarefas(tarefaComPrazo);

        Tarefa tarefaSemPrazo =  new Tarefa();

        tarefaSemPrazo.setNome("Comprar Leite");
        tarefaSemPrazo.setAlarme(false);

        tarefas.setTarefas(tarefaSemPrazo);
        tarefas.setTarefas(tarefaSemPrazo);

        String expect = "Comprar Arroz";

        System.out.println("Expect: " + expect);
        System.out.print("Result: ");

        tarefas.getTarefasComPrazo().forEach( (item) -> {
            System.out.println( item.getNome());
        });
    }

    @Test
    public void consultarSomenteTarefasQueVencemHoje(){
        Tarefa tarefaVencemHoje = new Tarefa();

        tarefaVencemHoje.setNome("Comprar Arroz");
        tarefaVencemHoje.setDataTermino(LocalDate.now());
        tarefaVencemHoje.setAlarme(true);
        tarefaVencemHoje.setHoraConclusao(LocalTime.of(23,0));

        tarefas.setTarefas(tarefaVencemHoje);

        Tarefa tarefaSemPrazo =  new Tarefa();

        tarefaSemPrazo.setNome("Comprar Leite");
        tarefaSemPrazo.setAlarme(false);

        tarefas.setTarefas(tarefaSemPrazo);
        tarefas.setTarefas(tarefaSemPrazo);

        String expect = "Comprar Arroz";

        System.out.println("Expect: " + expect);
        System.out.print("Result: ");

        tarefas.getTarefasVencimentoHoje().forEach( (item) -> {
            System.out.println( item.getNome());
        });
    }

}
