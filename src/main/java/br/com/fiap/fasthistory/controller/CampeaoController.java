package br.com.fiap.fasthistory.controller;

//importa o atributo static CREATED da classe HttpStatus
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fasthistory.model.Campeao;
import br.com.fiap.fasthistory.repository.CampeaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("campeao")
@Slf4j
@CacheConfig(cacheNames = "campeoes")
@Tag(name = "campeoes", description = "Endpoint relacionados com campeões")
public class CampeaoController {
    
    @Autowired // CDI Injeção de Dependência
    CampeaoRepository repository;
 
    @GetMapping   
    @Cacheable
    @Operation(summary = "Lista todas os campeões cadastrados no sistema.", 
    description = "Endpoint que retorna um array de objetos do tipo campeao com todas as campeões do usuário atual")
    public List<Campeao> listarTodos(){        
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação do campeao"),
            @ApiResponse(responseCode = "201", description = "Campeão cadastrado com sucesso")
    })
    public Campeao cadastrar(@RequestBody @Valid Campeao vobjCampeao){        
        log.info("cadastrando campeão: {}", vobjCampeao);
        return repository.save(vobjCampeao);        
    }
    
    @GetMapping("/nome/{nomeCampeao}")   
    public String getIdCampeao(@PathVariable String nomeCampeao){                
        return repository.getIdCampeao(nomeCampeao);
    }

    @GetMapping("{id}")
    public ResponseEntity<Campeao> get(@PathVariable Long id){
        log.info("Buscando campeão com id: {}", id);
        
        return repository
                    .findById(id)
                    .map(ResponseEntity::ok) //rerence method 
                    .orElse(ResponseEntity.notFound().build());
                        //.map(c -> ResponseEntity.ok(c)) convertendo o resultado do findById 'c' 
                        //para oq esta dentro do paramentro da função map

        ////Código antes de refatorar                         
        // var campeao = repository.findById(id);        

        // if (campeao.isEmpty()) 
        //     return ResponseEntity.notFound().build();
                              
        // return ResponseEntity.ok(campeao.get());
    }

    // private Optional<Campeao> getCampeaoPorId(Long id) {
    //     var campeao = campeoes
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();                
    //     return campeao;
    // }
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void apagar(@PathVariable Long id){
        log.info("Deletando campeão com id: {}", id);
        verificarSeExisteCampeao(id);
        repository.deleteById(id);        
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @CacheEvict(allEntries = true)
    public Campeao editar(@PathVariable Long id, @RequestBody Campeao campeao){
        log.info("Atualizando campeão com id: {}", id);
        
        verificarSeExisteCampeao(id);                  
        campeao.setId(id); 

        ////Código antes de refatorar 
        // BeanUtils.copyProperties(campeao, campeaoAtualizado);

        // campeaoAtualizado.setId(id);
        // campeaoAtualizado.setNome(campeao.getNome());
        // campeaoAtualizado.setFuncao(campeao.getFuncao());
        // campeaoAtualizado.setRota(campeao.getRota());
        
        return repository.save(campeao);
    }

    private void verificarSeExisteCampeao(Long id) {
        repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Campeão não encontrado"));
    }
        
}
