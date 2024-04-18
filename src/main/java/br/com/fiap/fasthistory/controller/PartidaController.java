package br.com.fiap.fasthistory.controller;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fasthistory.DTOs.PartidaDTO;
import br.com.fiap.fasthistory.model.Partida;
import br.com.fiap.fasthistory.repository.PartidaRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("partida")
public class PartidaController {
    
    @Autowired
    PartidaRepository repository;

    // @GetMapping   
    // public List<Partida> listarTodos(){        
    //     return repository.findAll();
    // }

    @GetMapping   
    public List<PartidaDTO> listarTodos(){        
        return repository.findPartidasNomeCampeao();
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
