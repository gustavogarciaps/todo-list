package br.com.acelerazg.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa {

    private static int idClasse = 0;
    private int idObjeto;
    private String nome;
    private String descricao;
    private int prioridade;
    private Status status;
    private Categoria categoria;
    private LocalDate dataTermino;
    private Boolean alarme;
    private LocalTime horaConclusao;
    private String prazoConclusao;

    public Tarefa(){};
    public Tarefa(String titulo, String descricao, int prioridade, Status status, Categoria categoria, LocalDate dataTermino, Boolean concluido, LocalTime horaConclusao) {

        this.idObjeto = ++idClasse;
        this.nome = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.categoria = categoria;
        this.dataTermino = dataTermino;
        this.alarme = concluido;
        this.horaConclusao = horaConclusao;
        this.prazoConclusao = null;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Boolean getAlarme() {
        return alarme;
    }

    public void setAlarme(Boolean alarme) {
        this.alarme = alarme;
    }

    public LocalTime getHoraConclusao() {
        return horaConclusao;
    }

    public void setHoraConclusao(LocalTime horaConclusao) {
        this.horaConclusao = horaConclusao;
    }

    public String getPrazoConclusao() {
        return prazoConclusao;
    }

    public void setPrazoConclusao(String prazoConclusao) {
        this.prazoConclusao = prazoConclusao;
    }

    @Override
    public String toString() {
        return
                "ID:" + this.idObjeto +
                "\tNOME:" + this.nome +
                "\tCATEGORIA:" + this.categoria +
                "\tPRIORIDADE:" + this.prioridade +
                "\tDATA TÉRMINO: " + this.dataTermino.getDayOfMonth() + "/" + this.dataTermino.getMonthValue() + "/" + this.dataTermino.getYear() +
                "\t" + (this.alarme == true ? "\tPRAZO (HH/MM):" + this.getHoraConclusao(): "") +
                "\tSTATUS:" + this.status;
    }

    public String toFileString() {
        return "ID:" + this.idObjeto +
                "\tNOME:" + this.nome +
                "\tCATEGORIA:" + this.categoria +
                "\tPRIORIDADE:" + this.prioridade +
                "\tDATA TÉRMINO:" + this.dataTermino.getDayOfMonth() + "/" + this.dataTermino.getMonthValue() + "/" + this.dataTermino.getYear() +
                "\t" + (this.alarme == true ? "\tPRAZO (HH/MM):" + this.getHoraConclusao(): "") +
                "\tSTATUS:" + this.status;
    }

}
