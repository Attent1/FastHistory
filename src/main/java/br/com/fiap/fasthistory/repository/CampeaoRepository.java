package br.com.fiap.fasthistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fasthistory.model.Campeao;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {    

}
