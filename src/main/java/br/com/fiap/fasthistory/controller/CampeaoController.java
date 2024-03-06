package br.com.fiap.fasthistory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fasthistory.model.Campeao;

@RestController
@RequestMapping("campeao")
public class CampeaoController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    List<Campeao> campeoes = new ArrayList<>();

    @GetMapping   
    public List<Campeao> listarTodos(){        
        return campeoes;
    }

    @PostMapping
    public ResponseEntity<Campeao> cadastrar(@RequestBody Campeao vobjCampeao){        
        log.info("cadastrando campeão: {}", vobjCampeao);
        campeoes.add(vobjCampeao);
        return ResponseEntity.status(HttpStatus.CREATED).body(vobjCampeao);
    }
    
    private Optional<Campeao> getCampeaoPorId(Long id) {
        var campeao = campeoes
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();                
        return campeao;
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> apagar(@PathVariable Long id){
        log.info("Deletando campeão com id: {}", id);
        
        var campeao = getCampeaoPorId(id);        

        if (campeao.isEmpty()) 
            return ResponseEntity.notFound().build();
              
        campeoes.remove(campeao.get());        

        log.info("Campeão de {} id deletado", id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody Campeao vobjCampeao){
        log.info("Atualizando campeão com id: {}", id);
        
        var campeao = getCampeaoPorId(id);        

        if (campeao.isEmpty()) 
            return ResponseEntity.notFound().build();
              
        var campeaoAtualizado = new Campeao(id, vobjCampeao.nome(), vobjCampeao.funcao(), vobjCampeao.rota());

        campeoes.remove(campeao.get());
        campeoes.add(campeaoAtualizado);

        log.info("Campeão de {} id atualizado", id);

        return ResponseEntity.ok(campeaoAtualizado);
    }
        
}
