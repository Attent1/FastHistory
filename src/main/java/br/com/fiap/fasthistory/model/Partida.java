package br.com.fiap.fasthistory.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.fasthistory.controller.PartidaController;
import br.com.fiap.fasthistory.validation.Resultado;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@EqualsAndHashCode(callSuper = false)
public class Partida extends EntityModel<Partida> {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
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

    @JsonFormat(pattern="dd/MM/yyyy")
    @Builder.Default
    private LocalDate dataInclusao = LocalDate.now();

    @ManyToOne
    private Campeao campeao;

    public EntityModel<Partida> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(PartidaController.class).get(id)).withSelfRel(),
                linkTo(methodOn(PartidaController.class).apagar(id)).withRel("delete"),
                linkTo(methodOn(PartidaController.class).listarTodos(null, null)).withRel("contents")
            );
    }    
    
}






