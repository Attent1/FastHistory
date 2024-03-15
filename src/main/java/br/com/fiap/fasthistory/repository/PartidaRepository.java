package br.com.fiap.fasthistory.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.fasthistory.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>{
    
}
