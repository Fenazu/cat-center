package com.cat.catshelter.controller;

import com.cat.catshelter.model.Adotante;
import com.cat.catshelter.service.AdocaoService;
import com.cat.catshelter.service.AdotanteService;
import com.cat.catshelter.service.GatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private GatoService gatoService;

    @Autowired
    private AdotanteService adotanteService;

    @Autowired
    private AdocaoService adocaoService;

    @GetMapping("/")
    public String index(Model model) {
        List<com.cat.catshelter.model.Gato> disponiveis = gatoService.obterGatosDisponiveis();
        model.addAttribute("gatos", disponiveis.size() > 3 ? disponiveis.subList(0, 3) : disponiveis);
        model.addAttribute("totalGatos", gatoService.obterTodosGatos().size());
        return "index";
    }

    @GetMapping("/adotar")
    public String listarAdotar(Model model) {
        model.addAttribute("gatos", gatoService.obterGatosDisponiveis());
        return "adotar-lista";
    }

    @GetMapping("/adotar/{idGato}")
    public String formAdotar(@PathVariable int idGato, Model model) {
        model.addAttribute("gato", gatoService.obterGato(idGato));
        model.addAttribute("adotante", new Adotante());
        return "adotar";
    }

    @PostMapping("/adotar/{idGato}")
    public String adotar(@PathVariable int idGato, @ModelAttribute Adotante adotante) {
        int idAdotante = adotanteService.inserirRetornandoId(adotante);
        adocaoService.iniciarAdocao(idGato, idAdotante, LocalDate.now());
        return "redirect:/sucesso";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}
