package br.com.fiap.fasthistory.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.fasthistory.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>{
    
    @Query("SELECT a, b.nome FROM Partida a JOIN Campeao b ON a.idCampeao = b.id")
    List<Partida> findPartidasNomeCampeao();

}
