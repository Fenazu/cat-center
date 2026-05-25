package com.exemplo.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.exemplo.aula.model.Cliente;
import com.exemplo.aula.model.ClienteService;

@Controller
public class ClienteController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/cliente")
    public String formCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formcliente";
    }

    @PostMapping("/cliente")
    public String cadastrarCliente(@ModelAttribute Cliente cli, Model model) {
        ClienteService cs = context.getBean(ClienteService.class);
        cs.inserirCliente(cli);
        return "sucesso";
    }
}
