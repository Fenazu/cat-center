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
        validar(gato);
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
        validar(novo);
        gatoDAO.atualizarGato(id, novo);
    }

    private void validar(Gato g) {
        if (g.getNome() == null || g.getNome().isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        if (g.getCor() == null || g.getCor().isBlank()) throw new IllegalArgumentException("Cor é obrigatória");
        if (g.getTipoPelagem() == null || g.getTipoPelagem().isBlank()) throw new IllegalArgumentException("Tipo de pelagem é obrigatório");
        if (g.getSexo() == null) throw new IllegalArgumentException("Sexo é obrigatório");
        if (g.getFaixaEtaria() == null) throw new IllegalArgumentException("Faixa etária é obrigatória");
        if (g.getCastrado() == null) throw new IllegalArgumentException("Campo castrado é obrigatório");
    }

    public void atualizarDisponibilidade(int id, Disponivel disponivel) {
        gatoDAO.atualizarDisponibilidade(id, disponivel);
    }

    public void deletarGato(int id) {
        gatoDAO.deletarGato(id);
    }
}
