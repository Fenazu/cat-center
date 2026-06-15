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
        validar(adotante);
        adotanteDAO.inserirAdotante(adotante);
    }

    public int inserirRetornandoId(Adotante adotante) {
        validar(adotante);
        return adotanteDAO.inserirRetornandoId(adotante);
    }

    public Adotante obterAdotante(int id) {
        return adotanteDAO.obterAdotante(id);
    }

    public List<Adotante> obterTodosAdotantes() {
        return adotanteDAO.obterTodosAdotantes();
    }

    public void atualizarAdotante(int id, Adotante novo) {
        validar(novo);
        adotanteDAO.atualizarAdotante(id, novo);
    }

    private void validar(Adotante a) {
        if (a.getNome() == null || a.getNome().isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        if (a.getCpf() == null || a.getCpf().isBlank()) throw new IllegalArgumentException("CPF é obrigatório");
        if (a.getTelefone() == null || a.getTelefone().isBlank()) throw new IllegalArgumentException("Telefone é obrigatório");
        if (a.getCep() == null || a.getCep().isBlank()) throw new IllegalArgumentException("CEP é obrigatório");
    }

    public void deletarAdotante(int id) {
        adotanteDAO.deletarAdotante(id);
    }
}
