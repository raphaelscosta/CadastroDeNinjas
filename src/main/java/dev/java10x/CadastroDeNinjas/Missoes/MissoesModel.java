package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nomeDaMissao;
    private String dificuldade;

    @OneToMany(mappedBy = "missao")
    private List<NinjaModel> ninjas;

    public MissoesModel(){

    }

    public MissoesModel(String nomeDaMissao, String dificuldade){
        this.nomeDaMissao = nomeDaMissao;
        this.dificuldade = dificuldade;
    }

    public String getNomeDaMissao(){
        return nomeDaMissao;
    }

    public void setNomeDaMissao(String nomeDaMissao){
        this.nomeDaMissao = nomeDaMissao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }


}
