package br.com.fiap.fasthistory.DTOs;

import lombok.Data;

@Data
public class PartidaDTO {
    private Long id;
    private Long idCampeao;
    private float kill;
    private float death;
    private float assist;
    private float kda;
    private String resultado;
    private String nomeCampeao;

    public PartidaDTO(Long id, Long idCampeao, float kill, float death, float assist, float kda, String resultado, String nomeCampeao) {
        this.id = id;
        this.idCampeao = idCampeao;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
        this.kda = kda;
        this.resultado = resultado;
        this.nomeCampeao = nomeCampeao;
    }
}
