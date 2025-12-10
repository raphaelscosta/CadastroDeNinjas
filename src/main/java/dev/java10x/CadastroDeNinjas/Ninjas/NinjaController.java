package dev.java10x.CadastroDeNinjas.Ninjas;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.util.RouteMatcher;
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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.criarNinja(ninja);
    }

    //Mostrar ninja
    @GetMapping("/listar")
    public List<NinjaDTO> mostrarTodosOsNinjas(){
        return ninjaService.listarNinjas();
    }


    @GetMapping("/listar/{id}")
    public NinjaDTO mostrarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjaPorId(id);

    }

    @PatchMapping("atualizar/{id}")
    public NinjaDTO alterarNinjasPorId(@PathVariable Long id, @RequestBody NinjaDTO ninja){
        return ninjaService.alterarNinjasPorId(id,ninja);

    }

    @DeleteMapping("/deletar/{id}")
    public String deletetarNinjaPorId(@PathVariable Long id){
       try{
           ninjaService.deletarNinja(id);
           return "Ninja deletado com sucesso";
       }
       catch (EntityNotFoundException e){
           System.out.println(e.getMessage());
           return e.getMessage();
       }
    }


}
