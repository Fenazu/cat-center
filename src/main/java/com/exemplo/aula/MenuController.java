package com.exemplo.aula;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MenuController {

    @GetMapping("/") //Rota principal do sistema
    public String index() {
        return "index";
    }

    

}
