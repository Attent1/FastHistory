package br.com.fiap.fasthistory.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.fasthistory.DTOs.PartidaDTO;
import br.com.fiap.fasthistory.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>{
    
    @Query("SELECT new br.com.fiap.fasthistory.DTOs.PartidaDTO(p.id, p.idCampeao, p.kill, p.death, p.assist, p.kda, p.resultado, c.nome) " +
           "FROM Partida p JOIN Campeao c ON p.idCampeao = c.id")
    List<PartidaDTO> findPartidasNomeCampeao();

    List<Partida> findByCampeaoNome(String campeao); 

}
