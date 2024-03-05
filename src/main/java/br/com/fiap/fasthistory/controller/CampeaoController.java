package br.com.fiap.fasthistory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fiap.fasthistory.model.Campeao;

@Controller
public class CampeaoController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    List<Campeao> campeoes = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET , path = "/campeao")
    @ResponseBody
    public List<Campeao> listarTodos(){        
        return campeoes;
    }

    @RequestMapping(method = RequestMethod.POST , path = "/campeao")
    @ResponseBody    
    public ResponseEntity<Campeao> cadastrar(@RequestBody Campeao vobjCampeao){        
        log.info("cadastrando campeão: {}", vobjCampeao);
        campeoes.add(vobjCampeao);
        return ResponseEntity.status(201).body(vobjCampeao);
    }
    
    private Optional<Campeao> getCampeaoPorId(Long id) {
        var campeao = campeoes
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();                
        return campeao;
    }
    
    @RequestMapping(method = RequestMethod.DELETE , path = "/campeao/{id}")
    @ResponseBody
    public ResponseEntity<Object> apagar(@PathVariable Long id){
        log.info("Deletando campeão com id: {}", id);
        
        var campeao = getCampeaoPorId(id);        

        if (campeao.isEmpty()) 
            return ResponseEntity.notFound().build();
              
        campeoes.remove(campeao.get());        

        log.info("Campeão de {} id deletado", id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT , path = "/campeao/{id}")
    @ResponseBody
    public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody Campeao vobjCampeao){
        log.info("Atualizando campeão com id: {}", id);
        
        var campeao = getCampeaoPorId(id);        

        if (campeao.isEmpty()) 
            return ResponseEntity.notFound().build();
              
        var campeaoAtualizado = new Campeao(id, vobjCampeao.nome(), vobjCampeao.funcao(), vobjCampeao.rota());

        campeoes.remove(campeao.get());
        campeoes.add(campeaoAtualizado);

        log.info("Campeão de {} id atualizado", id);

        return ResponseEntity.ok().body(campeaoAtualizado);
    }
        
}
