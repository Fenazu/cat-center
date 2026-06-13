package com.cat.catshelter.service;

import com.cat.catshelter.model.Adocao;
import com.cat.catshelter.model.Disponivel;
import com.cat.catshelter.model.StatusAdocao;
import com.cat.catshelter.repository.AdocaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    AdocaoDAO adocaoDAO;

    @Autowired
    GatoService gatoService;

    public void inserirAdocao(Adocao adocao) {
        adocao.setStatus(StatusAdocao.PENDENTE);
        adocaoDAO.inserirAdocao(adocao);
        gatoService.atualizarDisponibilidade(adocao.getIdGato(), Disponivel.NAO);
    }

    public void iniciarAdocao(int idGato, int idAdotante, java.time.LocalDate data) {
        Adocao adocao = new Adocao(idGato, idAdotante, StatusAdocao.PENDENTE, data);
        adocaoDAO.inserirAdocao(adocao);
        gatoService.atualizarDisponibilidade(idGato, Disponivel.NAO);
    }

    public Adocao obterAdocao(int id) {
        return adocaoDAO.obterAdocao(id);
    }

    public List<Adocao> obterTodasAdocoes() {
        return adocaoDAO.obterTodasAdocoes();
    }

    public void atualizarStatus(int id, StatusAdocao status) {
        if (status == StatusAdocao.CANCELADA) {
            Adocao adocao = adocaoDAO.obterAdocao(id);
            if (adocao != null) {
                gatoService.atualizarDisponibilidade(adocao.getIdGato(), Disponivel.SIM);
            }
        }
        adocaoDAO.atualizarStatus(id, status);
    }

    public void deletarAdocao(int id) {
        adocaoDAO.deletarAdocao(id);
    }
}
