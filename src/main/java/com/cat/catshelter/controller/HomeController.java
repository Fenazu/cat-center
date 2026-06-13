package com.cat.catshelter.controller;

import com.cat.catshelter.model.Adotante;
import com.cat.catshelter.service.AdocaoService;
import com.cat.catshelter.service.AdotanteService;
import com.cat.catshelter.service.GatoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index(Model model) {
        GatoService gs = context.getBean(GatoService.class);
        List<com.cat.catshelter.model.Gato> disponiveis = gs.obterGatosDisponiveis();
        model.addAttribute("gatos", disponiveis.size() > 3 ? disponiveis.subList(0, 3) : disponiveis);
        return "index";
    }

    @GetMapping("/adotar")
    public String listarAdotar(Model model) {
        GatoService gs = context.getBean(GatoService.class);
        model.addAttribute("gatos", gs.obterGatosDisponiveis());
        return "adotar-lista";
    }

    @GetMapping("/adotar/{idGato}")
    public String formAdotar(@PathVariable int idGato, Model model) {
        GatoService gs = context.getBean(GatoService.class);
        model.addAttribute("gato", gs.obterGato(idGato));
        model.addAttribute("adotante", new Adotante());
        return "adotar";
    }

    @PostMapping("/adotar/{idGato}")
    public String adotar(@PathVariable int idGato, @ModelAttribute Adotante adotante) {
        AdotanteService adotanteService = context.getBean(AdotanteService.class);
        AdocaoService adocaoService = context.getBean(AdocaoService.class);

        int idAdotante = adotanteService.inserirRetornandoId(adotante);
        adocaoService.iniciarAdocao(idGato, idAdotante, LocalDate.now());

        return "redirect:/sucesso";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}
