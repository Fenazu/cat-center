package com.exemplo.aula.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ClienteDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirCliente(Cliente cli) {
        String sql = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
        Object[] obj = new Object[2];
        obj[0] = cli.getNome();
        obj[1] = cli.getCpf();
        jdbc.update(sql, obj);
    }
}
