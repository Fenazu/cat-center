package com.cat.catshelter.repository;

import com.cat.catshelter.model.Disponivel;
import com.cat.catshelter.model.Gato;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class GatoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirGato(Gato gato) {
        String sql = "INSERT INTO gatos (nome, sexo, faixa_etaria, cor, tipo_pelagem, comorbidades, castrado, disponivel, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = new Object[9];
        obj[0] = gato.getNome();
        obj[1] = gato.getSexo().name();
        obj[2] = gato.getFaixaEtaria().name();
        obj[3] = gato.getCor();
        obj[4] = gato.getTipoPelagem();
        obj[5] = gato.getComorbidades();
        obj[6] = gato.getCastrado().name();
        obj[7] = gato.getDisponivel().name();
        obj[8] = gato.getFoto();
        jdbc.update(sql, obj);
    }

    public Gato obterGato(int id) {
        String sql = "SELECT * FROM gatos WHERE id = ?";
        return Gato.converterRegistro((Map<String, Object>) jdbc.queryForMap(sql, id));
    }

    public ArrayList<Gato> obterTodosGatos() {
        String sql = "SELECT * FROM gatos ORDER BY nome";
        ArrayList<Map<String, Object>> listaRegistros = (ArrayList<Map<String, Object>>) jdbc.queryForList(sql);
        ArrayList<Gato> aux = new ArrayList();

        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Gato.converterRegistro((Map) registro));
        }
        return aux;
    }

    public ArrayList<Gato> obterGatosDisponiveis() {
        String sql = "SELECT * FROM gatos WHERE disponivel = 'SIM' ORDER BY nome";
        ArrayList<Map<String, Object>> listaRegistros = (ArrayList<Map<String, Object>>) jdbc.queryForList(sql);
        ArrayList<Gato> aux = new ArrayList();

        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Gato.converterRegistro((Map) registro));
        }
        return aux;
    }

    public void atualizarGato(int id, Gato novo) {
        String sql = "UPDATE gatos SET nome = ?, sexo = ?, faixa_etaria = ?, cor = ?, tipo_pelagem = ?, comorbidades = ?, castrado = ?, disponivel = ?, foto = ? WHERE id = ?";
        Object[] obj = new Object[10];
        obj[0] = novo.getNome();
        obj[1] = novo.getSexo().name();
        obj[2] = novo.getFaixaEtaria().name();
        obj[3] = novo.getCor();
        obj[4] = novo.getTipoPelagem();
        obj[5] = novo.getComorbidades();
        obj[6] = novo.getCastrado().name();
        obj[7] = novo.getDisponivel().name();
        obj[8] = novo.getFoto();
        obj[9] = id;
        jdbc.update(sql, obj);
    }

    public void atualizarDisponibilidade(int id, Disponivel disponivel) {
        String sql = "UPDATE gatos SET disponivel = ? WHERE id = ?";
        Object[] obj = new Object[2];
        obj[0] = disponivel.name();
        obj[1] = id;
        jdbc.update(sql, obj);
    }

    public void deletarGato(int id) {
        String sql = "DELETE FROM gatos WHERE id = ?";
        jdbc.update(sql, id);
    }
}
