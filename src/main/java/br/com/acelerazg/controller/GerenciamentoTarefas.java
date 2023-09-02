package br.com.acelerazg.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import br.com.acelerazg.model.Tarefa;

public class GerenciamentoTarefas {

    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    public void setTarefas(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public void setTarefas(ArrayList<Tarefa> listaTarefas){
        listaTarefas.forEach( (item) -> {
            tarefas.add(item);
        });
    }

    public ArrayList<Tarefa> getTarefas() {

        return tarefas;
    }

    public void ajustarAlarme(){
        tarefas.forEach( (item) -> {
            if(item.getAlarme() == true){
                tempoAlarme(item);
                System.out.println(item.getNome() + " PRAZO: " + item.getPrazoConclusao());
            }
        });
    }

    public void tempoAlarme(Tarefa tarefa){

        int anos = (tarefa.getDataTermino().getYear() > LocalDate.now().getYear()) ? tarefa.getDataTermino().getYear() - LocalDate.now().getYear() : 0;

        int meses = tarefa.getDataTermino().getMonthValue() - LocalDate.now().getMonthValue();
        int dias = tarefa.getDataTermino().getDayOfMonth() - LocalDate.now().getDayOfMonth();
        int horas = tarefa.getHoraConclusao().getHour() - LocalTime.now().getHour();
        int minutos = tarefa.getHoraConclusao().getMinute() - LocalTime.now().getMinute();

        if (minutos < 0) {
            minutos += 60;
            horas--;
        }

        if (horas < 0) {
            horas += 24;
            dias--;
        }

        if (dias < 0) {
            meses--;
            dias += LocalDate.now().minusMonths(1).lengthOfMonth();
        }

        if (meses < 0) {
            meses += 12;
            anos--;
        }

        String s = anos + " anos, " + meses + " meses, " + dias + " dias, " + horas + " horas, e " + minutos + " minutos.";
        tarefa.setPrazoConclusao(s);
    }

    public ArrayList<Tarefa> getTarefasComPrazo(){
        ArrayList<Tarefa> tarefasComPrazo = new ArrayList<>();

        tarefas.forEach((item) -> {
            if(item.getAlarme() == true){
                tarefasComPrazo.add(item);
            }
        });

        return tarefasComPrazo;
    }

    public ArrayList<Tarefa> getTarefasVencimentoHoje(){
        ArrayList<Tarefa> tarefasVencemHoje = new ArrayList<>();

        getTarefasComPrazo().forEach((item) -> {
            if(item.getDataTermino().equals(LocalDate.now())){
                tarefasVencemHoje.add(item);
            }
        });


        return tarefasVencemHoje;
    }

    public ArrayList<Tarefa> getTarefasOrdenadasPrioridade(){
      this.tarefas.sort((t1, t2) -> Integer.compare(t1.getPrioridade(), t2.getPrioridade()));

      return this.tarefas;
    }

    public ArrayList<Tarefa> getTarefasOrdenadasCategoria(){
        this.tarefas.sort((t1, t2) -> t1.getCategoria().getCategoria().compareTo(t2.getCategoria().getCategoria()));

        return this.tarefas;
    }

    public ArrayList<Tarefa> getTarefasOrdenadasStatus(){
        this.tarefas.sort((t1, t2) -> t1.getStatus().compareTo(t2.getStatus()));

        return this.tarefas;
    }

    public ArrayList<Tarefa> getTarefasOrdenadasData(){
        this.tarefas.sort((t1, t2) -> t1.getDataTermino().compareTo(t2.getDataTermino()));

        return this.tarefas;
    }
}
