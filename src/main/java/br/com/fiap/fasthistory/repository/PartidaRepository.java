package br.com.fiap.fasthistory.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fasthistory.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>{
        
    List<Partida> findByCampeaoNome(String campeao); 

}
