package br.com.fiap.fasthistory.controller;

import java.util.ArrayList;
import java.util.List;

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

    List<Campeao> repository = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET , path = "/campeao")
    @ResponseBody
    public List<Campeao> listarCampeao(){        
        return repository;
    }

    @RequestMapping(method = RequestMethod.POST , path = "/campeao")
    @ResponseBody
    //@ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Campeao> create(@RequestBody Campeao campeao){        
        log.info("cadastrando campeão: {}", campeao);
        repository.add(campeao);
        return ResponseEntity.status(201).body(campeao);
    }

    //só fazendo para ter de exemplo
    @RequestMapping(method = RequestMethod.GET , path = "/campeao/{id}")
    @ResponseBody
    public ResponseEntity<Campeao> getCampeao(@PathVariable Long id){
        log.info("buscando campeão com id: {}", id);

        //stream
        var campeao = repository.stream()
                                                 .filter(c -> c.id()
                                                 .equals(id))
                                                 .findFirst();

        if (campeao.isEmpty()) { 
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(campeao.get());
    }
        
}
