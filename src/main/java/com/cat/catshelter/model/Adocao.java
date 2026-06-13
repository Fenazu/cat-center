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
        int id = (int) registros.get("id");
        int idGato = (int) registros.get("id_gato");
        int idAdotante = (int) registros.get("id_adotante");
        StatusAdocao status = StatusAdocao.valueOf((String) registros.get("status"));
        LocalDate data = ((Date) registros.get("data")).toLocalDate();
        return new Adocao(id, idGato, idAdotante, status, data);
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
}
