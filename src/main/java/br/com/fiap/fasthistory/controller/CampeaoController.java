package br.com.fiap.fasthistory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CampeaoController {
    
    @RequestMapping(method = RequestMethod.GET , path = "/campeao", produces = "application/json")
    @ResponseBody
    public String listarCampeao(){
        return "Lista de campeões";
    } 

}
