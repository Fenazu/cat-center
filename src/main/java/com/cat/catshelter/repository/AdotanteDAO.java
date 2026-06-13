package com.cat.catshelter.repository;

import com.cat.catshelter.model.Adotante;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AdotanteDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirAdotante(Adotante adotante) {
        String sql = "INSERT INTO adotantes (nome, cpf, cep, telefone) VALUES (?, ?, ?, ?)";
        Object[] obj = new Object[4];
        obj[0] = adotante.getNome();
        obj[1] = adotante.getCpf();
        obj[2] = adotante.getCep();
        obj[3] = adotante.getTelefone();
        jdbc.update(sql, obj);
    }

    public int inserirRetornandoId(Adotante adotante) {
        String sql = "INSERT INTO adotantes (nome, cpf, cep, telefone) VALUES (?, ?, ?, ?) RETURNING id";
        return jdbc.queryForObject(sql, Integer.class,
                adotante.getNome(), adotante.getCpf(), adotante.getCep(), adotante.getTelefone());
    }

    public Adotante obterAdotante(int id) {
        String sql = "SELECT * FROM adotantes WHERE id = ?";
        return Adotante.converterRegistro((Map<String, Object>) jdbc.queryForMap(sql, id));
    }

    public ArrayList<Adotante> obterTodosAdotantes() {
        String sql = "SELECT * FROM adotantes ORDER BY nome";
        ArrayList<Map<String, Object>> listaRegistros = (ArrayList<Map<String, Object>>) jdbc.queryForList(sql);
        ArrayList<Adotante> aux = new ArrayList<>();

        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Adotante.converterRegistro(registro));
        }
        return aux;
    }

    public void atualizarAdotante(int id, Adotante novo) {
        String sql = "UPDATE adotantes SET nome = ?, cpf = ?, cep = ?, telefone = ? WHERE id = ?";
        Object[] obj = new Object[5];
        obj[0] = novo.getNome();
        obj[1] = novo.getCpf();
        obj[2] = novo.getCep();
        obj[3] = novo.getTelefone();
        obj[4] = id;
        jdbc.update(sql, obj);
    }

    public void deletarAdotante(int id) {
        List<Integer> idGatos = jdbc.queryForList("SELECT id_gato FROM adocoes WHERE id_adotante = ?", Integer.class, id);
        jdbc.update("DELETE FROM adocoes WHERE id_adotante = ?", id);
        for (Integer idGato : idGatos) {
            jdbc.update("UPDATE gatos SET disponivel = 'SIM' WHERE id = ?", idGato);
        }
        jdbc.update("DELETE FROM adotantes WHERE id = ?", id);
    }
}
