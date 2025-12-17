package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService service){
        ninjaService = service;
    }

    //Mostrar ninja
    @GetMapping("/listar")
    public String mostrarTodosOsNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas",ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinja(id);
        return "redirect:/ninjas/ui/listar";
    }


    @GetMapping("/listar/{id}")
    public String listarNinjaPorId(@PathVariable Long id, Model model){
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        model.addAttribute("ninja",ninja);
        return "detalhesNinja";
    }

    @GetMapping("/alterar/{id}")
    public String abrirFormularioAlteracao(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        model.addAttribute("ninja", ninja);
        return "atualizarNinja"; // Nome do arquivo HTML acima
    }

    // POST: Para receber o clique do botão "Salvar Alterações"
    @PostMapping("/alterar")
    public String alterarNinja(@ModelAttribute("ninja") NinjaDTO ninja) {
        ninjaService.alterarNinjasPorId(ninja.getId(), ninja);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "cadastroNinja";
    }

    // POST: Recebe os dados
    @PostMapping("/cadastro")
    public String salvarNinja(@ModelAttribute("ninja") NinjaDTO ninja) {
        ninjaService.criarNinja(ninja);

        // <--- 2. CORREÇÃO AQUI: O redirect também precisa do "/ui"
        return "redirect:/ninjas/ui/listar";
    }

}
