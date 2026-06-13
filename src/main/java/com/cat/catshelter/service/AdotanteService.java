package com.cat.catshelter.service;

import com.cat.catshelter.model.Adotante;
import com.cat.catshelter.repository.AdotanteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdotanteService {

    @Autowired
    AdotanteDAO adotanteDAO;

    public void inserirAdotante(Adotante adotante) {
        adotanteDAO.inserirAdotante(adotante);
    }

    public int inserirRetornandoId(Adotante adotante) {
        return adotanteDAO.inserirRetornandoId(adotante);
    }

    public Adotante obterAdotante(int id) {
        return adotanteDAO.obterAdotante(id);
    }

    public List<Adotante> obterTodosAdotantes() {
        return adotanteDAO.obterTodosAdotantes();
    }

    public void atualizarAdotante(int id, Adotante novo) {
        adotanteDAO.atualizarAdotante(id, novo);
    }

    public void deletarAdotante(int id) {
        adotanteDAO.deletarAdotante(id);
    }
}
