package br.com.fiap.fasthistory.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    
}
