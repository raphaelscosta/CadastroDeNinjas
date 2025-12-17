package dev.java10x.CadastroDeNinjas.Missoes;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listar(){
        return "";
    }

    @GetMapping("/listar/{id}")
    public String listarPorId(@PathVariable Long id){
        return "";
    }

    @PostMapping("/criar")
    public String criarMissao(@RequestBody  MissoesDTO missoes){
        return "";
    }

    @PatchMapping("/atualizar/{id}")
    public String atualizarPorId(@PathVariable Long id, @RequestBody  MissoesDTO missoes){
        return "";
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPorId(@PathVariable Long id){

    }





}
