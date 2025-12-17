package dev.java10x.CadastroDeNinjas.Ninjas;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> listaDeNinjas = ninjaRepository.findAll();
        return listaDeNinjas.stream().map(ninjaMapper::map).collect(Collectors.toList());


    }

    public NinjaDTO listarNinjaPorId(Long id){
        Optional<NinjaModel> ninjaById = ninjaRepository.findById(id);
        return ninjaById.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        ninjaModel = ninjaRepository.save(ninjaModel);
        return ninjaMapper.map(ninjaModel);
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

    public NinjaDTO alterarNinjasPorId(Long id, NinjaDTO ninjaDTO){

        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninjaBD = ninjaExistente.get();
            if (ninjaDTO.getNome() != null) {
                ninjaBD.setNome(ninjaDTO.getNome());
            }
            if (ninjaDTO.getEmail() != null) {
                ninjaBD.setEmail(ninjaDTO.getEmail());
            }

            if (ninjaDTO.getIdade() == 0) {
                ninjaBD.setIdade(ninjaDTO.getIdade());
            }

            if (ninjaDTO.getImgUrl() != null) {
                ninjaBD.setImgUrl(ninjaDTO.getImgUrl());
            }

            if (ninjaDTO.getRank() != null) {
                ninjaBD.setRank(ninjaDTO.getRank());
            }

            if (ninjaDTO.getMissoes() != null) {
                ninjaBD.setMissoes(ninjaDTO.getMissoes());
            }

            ninjaBD.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaBD);
            return ninjaMapper.map(ninjaBD);
        }

        return null;
    }
}
