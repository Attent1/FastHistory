package br.com.fiap.fasthistory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.fasthistory.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long>{
        
    Page<Partida> findByCampeaoNomeIgnoreCase(String campeao, Pageable pageable);

    //value = "SELECT * FROM Partida", nativeQuery = true
    // @Query("SELECT p FROM Partida p WHERE p.campeao.nome = :campeao AND MONTH(p.dataInclusao) = :mes")
    // Page<Partida> findByCampeaoNomeAndMes(@Param("campeao") String campeao, @Param("mes") Integer mes, Pageable pageable); 
    
    // //
    // @Query("SELECT p FROM Partida p WHERE MONTH(p.dataInclusao) = :mes")
    // Page<Partida> findByMes(@Param("mes") Integer mes, Pageable pageable);

    // @Query("SELECT p FROM Partida p ORDER BY p.id LIMIT ?2 OFFSET ?1")
    // List<Partida> findAllPageable(int offset, int size);
}
