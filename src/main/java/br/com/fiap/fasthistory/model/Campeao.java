package br.com.fiap.fasthistory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data //Get Set HashCode Construtor
@Entity 

public class Campeao {    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    @Column(name = "nome", unique=true)
    private String nome;    
    private String funcao;    
    private String rota;

}
