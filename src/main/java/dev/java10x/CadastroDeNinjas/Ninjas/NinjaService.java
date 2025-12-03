package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaService extends JpaRepository<NinjaModel,Long> {
}
