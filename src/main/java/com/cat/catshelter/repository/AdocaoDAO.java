package com.cat.catshelter.repository;

import com.cat.catshelter.model.Adocao;
import com.cat.catshelter.model.StatusAdocao;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AdocaoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirAdocao(Adocao adocao) {
        String sql = "INSERT INTO adocoes (id_gato, id_adotante, status, data) VALUES (?, ?, ?, ?)";
        Object[] obj = new Object[4];
        obj[0] = adocao.getIdGato();
        obj[1] = adocao.getIdAdotante();
        obj[2] = adocao.getStatus().name();
        obj[3] = Date.valueOf(adocao.getData());
        jdbc.update(sql, obj);
    }

    public Adocao obterAdocao(int id) {
        String sql = "SELECT a.*, g.nome AS nome_gato, ad.nome AS nome_adotante " +
                     "FROM adocoes a " +
                     "JOIN gatos g ON g.id = a.id_gato " +
                     "JOIN adotantes ad ON ad.id = a.id_adotante " +
                     "WHERE a.id = ?";
        return Adocao.converterRegistro((Map<String, Object>) jdbc.queryForMap(sql, id));
    }

    public ArrayList<Adocao> obterTodasAdocoes() {
        String sql = "SELECT a.*, g.nome AS nome_gato, ad.nome AS nome_adotante " +
                     "FROM adocoes a " +
                     "JOIN gatos g ON g.id = a.id_gato " +
                     "JOIN adotantes ad ON ad.id = a.id_adotante " +
                     "ORDER BY a.data DESC";
        ArrayList<Map<String, Object>> listaRegistros = (ArrayList<Map<String, Object>>) jdbc.queryForList(sql);
        ArrayList<Adocao> aux = new ArrayList<>();

        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Adocao.converterRegistro(registro));
        }
        return aux;
    }

    public void atualizarStatus(int id, StatusAdocao status) {
        String sql = "UPDATE adocoes SET status = ? WHERE id = ?";
        Object[] obj = new Object[2];
        obj[0] = status.name();
        obj[1] = id;
        jdbc.update(sql, obj);
    }

    public void deletarAdocao(int id) {
        List<Integer> ids = jdbc.queryForList("SELECT id_gato FROM adocoes WHERE id = ?", Integer.class, id);
        jdbc.update("DELETE FROM adocoes WHERE id = ?", id);
        if (!ids.isEmpty()) {
            jdbc.update("UPDATE gatos SET disponivel = 'SIM' WHERE id = ?", ids.get(0));
        }
    }
}
