package com.cat.catshelter.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

public class Adocao {

    private int id;
    private int idGato;
    private int idAdotante;
    private StatusAdocao status;
    private LocalDate data;
    private String nomeGato;
    private String nomeAdotante;

    // Inicio do form
    public Adocao() {
    }

    // Insert
    public Adocao(int idGato, int idAdotante, StatusAdocao status, LocalDate data) {
        this.idGato = idGato;
        this.idAdotante = idAdotante;
        this.status = status;
        this.data = data;
    }

    // Select
    public Adocao(int id, int idGato, int idAdotante, StatusAdocao status, LocalDate data) {
        this.id = id;
        this.idGato = idGato;
        this.idAdotante = idAdotante;
        this.status = status;
        this.data = data;
    }

    public static Adocao converterRegistro(Map<String, Object> registros) {
        Adocao a = new Adocao(
            (int) registros.get("id"),
            (int) registros.get("id_gato"),
            (int) registros.get("id_adotante"),
            StatusAdocao.valueOf((String) registros.get("status")),
            ((Date) registros.get("data")).toLocalDate()
        );
        if (registros.containsKey("nome_gato"))    a.setNomeGato((String) registros.get("nome_gato"));
        if (registros.containsKey("nome_adotante")) a.setNomeAdotante((String) registros.get("nome_adotante"));
        return a;
    }

    public int getId() { return id; }
    public int getIdGato() { return idGato; }
    public int getIdAdotante() { return idAdotante; }
    public StatusAdocao getStatus() { return status; }
    public LocalDate getData() { return data; }

    public void setId(int id) { this.id = id; }
    public void setIdGato(int idGato) { this.idGato = idGato; }
    public void setIdAdotante(int idAdotante) { this.idAdotante = idAdotante; }
    public void setStatus(StatusAdocao status) { this.status = status; }
    public void setData(LocalDate data) { this.data = data; }
    public void setNomeGato(String nomeGato) { this.nomeGato = nomeGato; }
    public void setNomeAdotante(String nomeAdotante) { this.nomeAdotante = nomeAdotante; }
    public String getNomeGato() { return nomeGato; }
    public String getNomeAdotante() { return nomeAdotante; }
}
