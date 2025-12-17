package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setDificuldade(missoesDTO.getDificuldade());
        missoesModel.setNomeDaMissao(missoesDTO.getNomeDaMissao());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO dto = new MissoesDTO();
        dto.setId(missoesModel.getId());
        dto.setNomeDaMissao(missoesModel.getNomeDaMissao());
        dto.setDificuldade(missoesModel.getDificuldade());
        dto.setNinjas(missoesModel.getNinjas());

        return dto;
    }
}
