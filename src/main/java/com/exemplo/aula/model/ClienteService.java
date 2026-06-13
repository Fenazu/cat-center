package com.exemplo.aula.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteDAO dao;

    public void inserirCliente(Cliente cli) {
        dao.inserirCliente(cli);
    }
}
