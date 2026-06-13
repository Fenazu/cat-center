package com.cat.catshelter.model;

import java.util.Map;

public class Adotante {

    private int id;
    private String nome;
    private String cpf;
    private String cep;
    private String telefone;

    // Inicio do form
    public Adotante() {
    }

    // Insert
    public Adotante(String nome, String cpf, String cep, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.telefone = telefone;
    }

    // Select
    public Adotante(int id, String nome, String cpf, String cep, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.telefone = telefone;
    }

    public static Adotante converterRegistro(Map<String, Object> registros) {
        int id = (int) registros.get("id");
        String nome = (String) registros.get("nome");
        String cpf = (String) registros.get("cpf");
        String cep = (String) registros.get("cep");
        String telefone = (String) registros.get("telefone");
        return new Adotante(id, nome, cpf, cep, telefone);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCep() { return cep; }
    public String getTelefone() { return telefone; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setCep(String cep) { this.cep = cep; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
