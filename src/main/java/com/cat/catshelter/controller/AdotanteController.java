package com.cat.catshelter.controller;

import com.cat.catshelter.model.Adotante;
import com.cat.catshelter.service.AdotanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdotanteController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/adotante/{id}")
    public String detalheAdotante(Model model, @PathVariable int id) {
        AdotanteService as = context.getBean(AdotanteService.class);
        model.addAttribute("adotante", as.obterAdotante(id));
        return "adotantes/detalhe";
    }

    @GetMapping("/adotantes")
    public String listarAdotantes(Model model) {
        AdotanteService as = context.getBean(AdotanteService.class);
        model.addAttribute("adotantes", as.obterTodosAdotantes());
        return "adotantes/lista";
    }

    @GetMapping("/adotante/novo")
    public String formNovoAdotante(Model model) {
        model.addAttribute("adotante", new Adotante());
        return "adotantes/form";
    }

    @PostMapping("/adotante/novo")
    public String salvarAdotante(@ModelAttribute Adotante adotante) {
        AdotanteService as = context.getBean(AdotanteService.class);
        as.inserirAdotante(adotante);
        return "redirect:/adotantes";
    }

    @GetMapping("/adotante/{id}/editar")
    public String formEditarAdotante(Model model, @PathVariable int id) {
        AdotanteService as = context.getBean(AdotanteService.class);
        model.addAttribute("adotante", as.obterAdotante(id));
        model.addAttribute("id", id);
        return "adotantes/form";
    }

    @PostMapping("/adotante/{id}/editar")
    public String atualizarAdotante(@ModelAttribute Adotante adotante, @PathVariable int id) {
        AdotanteService as = context.getBean(AdotanteService.class);
        as.atualizarAdotante(id, adotante);
        return "redirect:/adotantes";
    }

    @PostMapping("/adotante/{id}/deletar")
    public String deletarAdotante(@PathVariable int id) {
        AdotanteService as = context.getBean(AdotanteService.class);
        as.deletarAdotante(id);
        return "redirect:/adotantes";
    }
}
