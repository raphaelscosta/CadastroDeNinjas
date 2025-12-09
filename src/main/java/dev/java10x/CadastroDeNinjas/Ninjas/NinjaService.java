package dev.java10x.CadastroDeNinjas.Ninjas;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();

    }

    public NinjaModel listarNinjaPorId(Long id){
        Optional<NinjaModel> ninjaById = ninjaRepository.findById(id);
        return ninjaById.orElse(null);
    }

    public NinjaModel criarNinja(NinjaModel ninja){
        NinjaModel ninjaCriado = ninjaRepository.save(ninja);
        return ninjaCriado;
    }


    public void deletarNinja(Long id) {

        boolean exists = ninjaRepository.existsById(id);
        if(exists){
            ninjaRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Id não encontrado");
        }

    }

    public NinjaModel alterarNinjasPorId(Long id, NinjaModel ninjaAtualizado){
        if(ninjaRepository.existsById(id)){
            ninjaAtualizado.setId(id);
            return ninjaRepository.save(ninjaAtualizado);
        }

        return null;
    }
}
