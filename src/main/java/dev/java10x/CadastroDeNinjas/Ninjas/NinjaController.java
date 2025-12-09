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
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){
        NinjaModel ninjaCriado = ninjaService.criarNinja(ninja);
        return ninjaCriado;
    }

    //Mostrar ninja
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosOsNinjas(){
        return ninjaService.listarNinjas();
    }


    @GetMapping("/listar/{id}")
    public NinjaModel mostrarNinjaPorId(@PathVariable Long id){
        NinjaModel ninjaModel = ninjaService.listarNinjaPorId(id);
        return ninjaModel;
    }

    @PatchMapping("atualizar/{id}")
    public NinjaModel alterarNinjasPorId(@PathVariable Long id, @RequestBody NinjaModel ninja){
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
