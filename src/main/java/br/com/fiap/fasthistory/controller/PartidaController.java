package br.com.fiap.fasthistory.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND; 
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fasthistory.model.Partida;
import br.com.fiap.fasthistory.repository.PartidaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("partida")
@Slf4j
public class PartidaController {
    
    @Autowired
    PartidaRepository repository;
     
    @GetMapping   
    public List<Partida> listarTodos(@RequestParam(required = false) String campeao){        
        log.info("buscar: " + campeao);
        if (campeao != null) {
            return repository.findByCampeaoNome(campeao);
        }
        return repository.findAll();
    }
    
    @PostMapping
    @ResponseStatus(CREATED)
    public Partida create(@RequestBody @Valid Partida partida) {        
        Float kda;
        kda = (partida.getKill() + partida.getAssist()) / partida.getDeath();         
        partida.setKda(kda);
        return repository.save(partida);   
    }

    @GetMapping("{id}")
    public ResponseEntity<Partida> get(@PathVariable Long id){                
        return repository
                    .findById(id)
                    .map(ResponseEntity::ok) 
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void apagar(@PathVariable Long id){
        log.info("Deletando partida com id: {}", id);
        verificarSeExistePartida(id);
        repository.deleteById(id);        
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    public Partida editar(@PathVariable Long id, @RequestBody Partida partida){
        log.info("Atualizando partida com id: {}", id);        
        verificarSeExistePartida(id);                  
        Float kda;
        kda = (partida.getKill() + partida.getAssist()) / partida.getDeath();         
        partida.setKda(kda);
        partida.setId(id);    
        return repository.save(partida);
    }

    private void verificarSeExistePartida(Long id) {
        repository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Partida não encontrada"));
    }
    
}
