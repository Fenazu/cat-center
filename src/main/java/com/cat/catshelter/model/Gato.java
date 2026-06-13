package com.cat.catshelter.model;

import java.util.Map;

public class Gato {

    private int id;
    private String nome;
    private Sexo sexo;
    private FaixaEtaria faixaEtaria;
    private String cor;
    private String tipoPelagem;
    private String comorbidades;
    private Castrado castrado;
    private Disponivel disponivel;
    private String foto;

    // Inicio do form
    public Gato() {
    }

    // Insert
    public Gato(String nome, Sexo sexo, FaixaEtaria faixaEtaria, String cor,
                String tipoPelagem, String comorbidades, Castrado castrado, Disponivel disponivel, String foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.faixaEtaria = faixaEtaria;
        this.cor = cor;
        this.tipoPelagem = tipoPelagem;
        this.comorbidades = comorbidades;
        this.castrado = castrado;
        this.disponivel = disponivel;
        this.foto = foto;
    }

    // Select
    public Gato(int id, String nome, Sexo sexo, FaixaEtaria faixaEtaria, String cor,
                String tipoPelagem, String comorbidades, Castrado castrado, Disponivel disponivel, String foto) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.faixaEtaria = faixaEtaria;
        this.cor = cor;
        this.tipoPelagem = tipoPelagem;
        this.comorbidades = comorbidades;
        this.castrado = castrado;
        this.disponivel = disponivel;
        this.foto = foto;
    }

    public static Gato converterRegistro(Map<String, Object> registros) {
        int id = (int) registros.get("id");
        String nome = (String) registros.get("nome");
        Sexo sexo = Sexo.valueOf((String) registros.get("sexo"));
        FaixaEtaria faixaEtaria = FaixaEtaria.valueOf((String) registros.get("faixa_etaria"));
        String cor = (String) registros.get("cor");
        String tipoPelagem = (String) registros.get("tipo_pelagem");
        String comorbidades = (String) registros.get("comorbidades");
        Castrado castrado = Castrado.valueOf((String) registros.get("castrado"));
        Disponivel disponivel = Disponivel.valueOf((String) registros.get("disponivel"));
        String foto = (String) registros.get("foto");
        return new Gato(id, nome, sexo, faixaEtaria, cor, tipoPelagem, comorbidades, castrado, disponivel, foto);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public Sexo getSexo() { return sexo; }
    public FaixaEtaria getFaixaEtaria() { return faixaEtaria; }
    public String getCor() { return cor; }
    public String getTipoPelagem() { return tipoPelagem; }
    public String getComorbidades() { return comorbidades; }
    public Castrado getCastrado() { return castrado; }
    public Disponivel getDisponivel() { return disponivel; }
    public String getFoto() { return foto; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }
    public void setFaixaEtaria(FaixaEtaria faixaEtaria) { this.faixaEtaria = faixaEtaria; }
    public void setCor(String cor) { this.cor = cor; }
    public void setTipoPelagem(String tipoPelagem) { this.tipoPelagem = tipoPelagem; }
    public void setComorbidades(String comorbidades) { this.comorbidades = comorbidades; }
    public void setCastrado(Castrado castrado) { this.castrado = castrado; }
    public void setDisponivel(Disponivel disponivel) { this.disponivel = disponivel; }
    public void setFoto(String foto) { this.foto = foto; }
}
