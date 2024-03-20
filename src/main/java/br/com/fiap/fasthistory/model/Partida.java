package br.com.fiap.fasthistory.model;

import br.com.fiap.fasthistory.validation.Resultado;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Partida {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idCampeao;
    
    @Min(0)
    private float kill;
    @Min(0)
    private float death;
    @Min(0)
    private float assist;

    private float kda;

    @NotBlank(message = "{partida.resultado.notblank}") @Size(min = 3, max = 7)    
    @Resultado() 
    private String resultado; // VITÓRIA | DERROTA

}






