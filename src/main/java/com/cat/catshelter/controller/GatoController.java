package com.cat.catshelter.controller;

import com.cat.catshelter.model.*;
import com.cat.catshelter.service.GatoService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Controller
public class GatoController {

    @Autowired
    private GatoService gatoService;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @Value("${cloudinary.cloud-name:}")
    private String cloudName;

    @Value("${cloudinary.api-key:}")
    private String apiKey;

    @Value("${cloudinary.api-secret:}")
    private String apiSecret;

    private String salvarFoto(MultipartFile arquivo, String fotoAtual) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) return fotoAtual;

        if (!cloudName.isEmpty()) {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
            ));
            Map result = cloudinary.uploader().upload(arquivo.getBytes(),
                ObjectUtils.asMap("folder", "catshelter"));
            return result.get("secure_url").toString();
        }

        Path dir = Paths.get(uploadDir);
        Files.createDirectories(dir);
        String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
        Files.copy(arquivo.getInputStream(), dir.resolve(nomeArquivo), StandardCopyOption.REPLACE_EXISTING);
        return nomeArquivo;
    }

    @GetMapping("/gato/{id}")
    public String detalheGato(Model model, @PathVariable int id) {
        model.addAttribute("gato", gatoService.obterGato(id));
        return "gatos/detalhe";
    }

    @GetMapping("/gatos")
    public String listarGatos(Model model) {
        model.addAttribute("gatos", gatoService.obterTodosGatos());
        return "gatos/lista";
    }

    @GetMapping("/gato/novo")
    public String formNovoGato(Model model) {
        model.addAttribute("gato", new Gato());
        model.addAttribute("sexos", Sexo.values());
        model.addAttribute("faixasEtarias", FaixaEtaria.values());
        model.addAttribute("castrados", Castrado.values());
        return "gatos/form";
    }

    @PostMapping("/gato/novo")
    public String salvarGato(@ModelAttribute Gato gato,
                             @RequestParam("arquivo") MultipartFile arquivo) throws IOException {
        gato.setDisponivel(Disponivel.SIM);
        gato.setFoto(salvarFoto(arquivo, null));
        gatoService.inserirGato(gato);
        return "redirect:/gatos";
    }

    @GetMapping("/gato/{id}/editar")
    public String formEditarGato(Model model, @PathVariable int id) {
        model.addAttribute("gato", gatoService.obterGato(id));
        model.addAttribute("sexos", Sexo.values());
        model.addAttribute("faixasEtarias", FaixaEtaria.values());
        model.addAttribute("castrados", Castrado.values());
        model.addAttribute("disponiveis", Disponivel.values());
        model.addAttribute("id", id);
        return "gatos/form";
    }

    @PostMapping("/gato/{id}/editar")
    public String atualizarGato(@ModelAttribute Gato gato,
                                @RequestParam("arquivo") MultipartFile arquivo,
                                @PathVariable int id) throws IOException {
        Gato atual = gatoService.obterGato(id);
        gato.setFoto(salvarFoto(arquivo, atual.getFoto()));
        gatoService.atualizarGato(id, gato);
        return "redirect:/gatos";
    }

    @PostMapping("/gato/{id}/deletar")
    public String deletarGato(@PathVariable int id) {
        gatoService.deletarGato(id);
        return "redirect:/gatos";
    }
}
