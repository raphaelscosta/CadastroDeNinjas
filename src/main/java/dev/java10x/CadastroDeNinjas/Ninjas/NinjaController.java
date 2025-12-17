package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensgem de boas vindas para quem acessar a rota")
    public String boasVindas(){
        return "Essa é a minha primeira rota";
    }

    //AdicionarNinja
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO ninjaCriado = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja" + ninjaCriado.getNome() + " foi criado com sucesso");
    }

    //Mostrar ninja
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> mostrarTodosOsNinjas(){
        List<NinjaDTO> resultado = ninjaService.listarNinjas();
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<?> mostrarNinjaPorId(@PathVariable Long id){
         NinjaDTO resultado = ninjaService.listarNinjaPorId(id);
         if(resultado == null){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
         }
         return ResponseEntity.ok(resultado);

    }

    @PatchMapping("atualizar/{id}")
    public ResponseEntity<?> alterarNinjasPorId(
            @Parameter(description = "Usuario envia id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario envia os dados a serem atualizados no corpo da requisição")
            @RequestBody NinjaDTO ninja){

        NinjaDTO resultado = ninjaService.alterarNinjasPorId(id,ninja);
        if(resultado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja de ID" + id + "não existe");
        }
        return ResponseEntity.ok(resultado);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
       try{
           ninjaService.deletarNinja(id);
           return ResponseEntity.ok("Ninja deletado com sucesso");

       }
       catch (EntityNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }


}
