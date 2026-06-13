package com.cat.catshelter.service;

import com.cat.catshelter.model.Disponivel;
import com.cat.catshelter.model.Gato;
import com.cat.catshelter.repository.GatoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatoService {

    @Autowired
    GatoDAO gatoDAO;

    public void inserirGato(Gato gato) {
        gatoDAO.inserirGato(gato);
    }

    public Gato obterGato(int id) {
        return gatoDAO.obterGato(id);
    }

    public List<Gato> obterTodosGatos() {
        return gatoDAO.obterTodosGatos();
    }

    public List<Gato> obterGatosDisponiveis() {
        return gatoDAO.obterGatosDisponiveis();
    }

    public void atualizarGato(int id, Gato novo) {
        gatoDAO.atualizarGato(id, novo);
    }

    public void atualizarDisponibilidade(int id, Disponivel disponivel) {
        gatoDAO.atualizarDisponibilidade(id, disponivel);
    }

    public void deletarGato(int id) {
        gatoDAO.deletarGato(id);
    }
}
