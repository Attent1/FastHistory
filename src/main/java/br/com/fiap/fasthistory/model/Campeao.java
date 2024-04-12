package br.com.fiap.fasthistory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data //Get Set HashCode Construtor
@Entity 

public class Campeao {    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    @NotBlank
    @Column(name = "nome", unique=true)
    private String nome;    
    @NotBlank
    private String funcao;    
    private String rota;

}
