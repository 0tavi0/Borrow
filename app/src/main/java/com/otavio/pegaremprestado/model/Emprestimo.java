package com.otavio.pegaremprestado.model;

/**
 * Created by OtavioAugusto on 25/04/18.
 */

public class Emprestimo {
    private String nome;
    private String objeto;
    private long qtdDias;
    private int status;
    private int IDJobs;



    public Emprestimo(String nome, String objeto, long qtdDias, int status, int IDJobs ) {
        this.nome = nome;
        this.objeto = objeto;
        this.qtdDias = qtdDias;
        this.status = status;
        this.IDJobs = IDJobs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public long getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(long qtdDias) {
        this.qtdDias = qtdDias;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIDJobs() {
        return IDJobs;
    }

    public void setIDJobs(int IDJobs) {
        this.IDJobs = IDJobs;
    }
}
