package br.com.fiap.fasthistory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data //Get Set HashCode Construtor
@Entity 

public class Campeao {    
    @Id
    private Long id;    
    private String nome;    
    private String funcao;    
    private String rota;

}
