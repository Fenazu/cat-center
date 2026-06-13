package com.cat.catshelter.controller;

import com.cat.catshelter.model.Adocao;
import com.cat.catshelter.model.StatusAdocao;
import com.cat.catshelter.service.AdocaoService;
import com.cat.catshelter.service.AdotanteService;
import com.cat.catshelter.service.GatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdocaoController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/adocao/{id}")
    public String detalheAdocao(Model model, @PathVariable int id) {
        AdocaoService as = context.getBean(AdocaoService.class);
        model.addAttribute("adocao", as.obterAdocao(id));
        return "adocoes/detalhe";
    }

    @GetMapping("/adocoes")
    public String listarAdocoes(Model model) {
        AdocaoService as = context.getBean(AdocaoService.class);
        model.addAttribute("adocoes", as.obterTodasAdocoes());
        return "adocoes/lista";
    }

    @GetMapping("/adocao/nova")
    public String formNovaAdocao(Model model) {
        GatoService gs = context.getBean(GatoService.class);
        AdotanteService ados = context.getBean(AdotanteService.class);
        model.addAttribute("adocao", new Adocao());
        model.addAttribute("gatos", gs.obterGatosDisponiveis());
        model.addAttribute("adotantes", ados.obterTodosAdotantes());
        return "adocoes/form";
    }

    @PostMapping("/adocao/nova")
    public String salvarAdocao(@ModelAttribute Adocao adocao) {
        AdocaoService as = context.getBean(AdocaoService.class);
        as.inserirAdocao(adocao);
        return "redirect:/adocoes";
    }

    @GetMapping("/adocao/{id}/status")
    public String formAtualizarStatus(Model model, @PathVariable int id) {
        AdocaoService as = context.getBean(AdocaoService.class);
        model.addAttribute("adocao", as.obterAdocao(id));
        model.addAttribute("statusList", StatusAdocao.values());
        model.addAttribute("id", id);
        return "adocoes/formstatus";
    }

    @PostMapping("/adocao/{id}/status")
    public String atualizarStatus(@PathVariable int id, @RequestParam StatusAdocao status) {
        AdocaoService as = context.getBean(AdocaoService.class);
        as.atualizarStatus(id, status);
        return "redirect:/adocoes";
    }

    @PostMapping("/adocao/{id}/deletar")
    public String deletarAdocao(@PathVariable int id) {
        AdocaoService as = context.getBean(AdocaoService.class);
        as.deletarAdocao(id);
        return "redirect:/adocoes";
    }
}
