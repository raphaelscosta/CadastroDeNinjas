package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService){
        this.ninjaService = ninjaService;
    }

    @GetMapping("/monaru")
    public String boasVindas(){
        return "Bem vindo, monaru";
    }

    //AdicionarNinja
    @PostMapping("/criar")
    public String criarNinja(){

        return "Ninja criado";
    }

    //Mostrar ninja
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosOsNinjas(){
        return ninjaService.listarNinjas();
    }


    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorId(){
        return "Ninja por id";
    }

    @PutMapping("/alterarId")
    public String alterarNinjasPorId(){
        return "Ninja por id";
    }

    @DeleteMapping("/deletarId")
    public String deletetarNinjaPorId(){
        return "NinjaDeletado";
    }
}
