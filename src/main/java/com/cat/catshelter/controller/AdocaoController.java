package com.cat.catshelter.controller;

import com.cat.catshelter.model.Adocao;
import com.cat.catshelter.model.StatusAdocao;
import com.cat.catshelter.service.AdocaoService;
import com.cat.catshelter.service.AdotanteService;
import com.cat.catshelter.service.GatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;

    @Autowired
    private GatoService gatoService;

    @Autowired
    private AdotanteService adotanteService;

    @GetMapping("/adocao/{id}")
    public String detalheAdocao(Model model, @PathVariable int id) {
        model.addAttribute("adocao", adocaoService.obterAdocao(id));
        return "adocoes/detalhe";
    }

    @GetMapping("/adocoes")
    public String listarAdocoes(Model model) {
        model.addAttribute("adocoes", adocaoService.obterTodasAdocoes());
        return "adocoes/lista";
    }

    @GetMapping("/adocao/nova")
    public String formNovaAdocao(Model model) {
        model.addAttribute("adocao", new Adocao());
        model.addAttribute("gatos", gatoService.obterGatosDisponiveis());
        model.addAttribute("adotantes", adotanteService.obterTodosAdotantes());
        return "adocoes/form";
    }

    @PostMapping("/adocao/nova")
    public String salvarAdocao(@ModelAttribute Adocao adocao) {
        adocao.setData(java.time.LocalDate.now());
        adocaoService.inserirAdocao(adocao);
        return "redirect:/adocoes";
    }

    @GetMapping("/adocao/{id}/status")
    public String formAtualizarStatus(Model model, @PathVariable int id) {
        model.addAttribute("adocao", adocaoService.obterAdocao(id));
        model.addAttribute("statusList", StatusAdocao.values());
        model.addAttribute("id", id);
        return "adocoes/formstatus";
    }

    @PostMapping("/adocao/{id}/status")
    public String atualizarStatus(@PathVariable int id, @RequestParam StatusAdocao status) {
        adocaoService.atualizarStatus(id, status);
        return "redirect:/adocoes";
    }

    @PostMapping("/adocao/{id}/deletar")
    public String deletarAdocao(@PathVariable int id) {
        adocaoService.deletarAdocao(id);
        return "redirect:/adocoes";
    }
}
