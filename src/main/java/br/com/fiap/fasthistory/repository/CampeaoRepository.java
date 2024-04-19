package br.com.fiap.fasthistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.fasthistory.model.Campeao;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {    

    @Query("SELECT c.id FROM Campeao c WHERE c.nome = :nomeCampeao")
    String getIdCampeao(String nomeCampeao); 
}
