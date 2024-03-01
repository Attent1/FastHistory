package br.com.fiap.fasthistory.model;

import java.util.Random;

public record Campeao(Long id, String nome, String funcao, String rota) {

    public Campeao(Long id, String nome, String funcao, String rota){
        var key = (id != null) ? id : Math.abs(new Random().nextLong());
        this.id = key;
        this.nome = nome;
        this.funcao = funcao;
        this.rota = rota;
    }

}
