package br.com.fiap.fasthistory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CampeaoController {
    
    @RequestMapping(method = RequestMethod.GET , path = "/campeao", produces = "application/json")
    @ResponseBody
    public String[] listarCampeao(){
        String[] campeoes = new String[3];
            campeoes[0] = "Jinx";
            campeoes[1] = "Zed";
            campeoes[2] = "Sylas";
            return campeoes;
    }

}
