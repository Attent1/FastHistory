package br.com.fiap.fasthistory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Get Set HashCode Construtor
@Entity 
@Builder
@NoArgsConstructor
@AllArgsConstructor 
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
