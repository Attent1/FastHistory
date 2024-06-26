package br.com.fiap.fasthistory.config;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.fasthistory.model.Campeao;
import br.com.fiap.fasthistory.model.Partida;
import br.com.fiap.fasthistory.repository.CampeaoRepository;
import br.com.fiap.fasthistory.repository.PartidaRepository;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    CampeaoRepository campeaoRepository;
    
    @Autowired
    PartidaRepository partidaRepository;

    @Override
    public void run(String... args) throws Exception {     
        campeaoRepository.saveAll(
            List.of(
                Campeao.builder().id(1L).nome("Zed").funcao("Assassino").rota("Mid").build(),
                Campeao.builder().id(2L).nome("Jinx").funcao("Atirador").rota("Ad Carry").build(),
                Campeao.builder().id(3L).nome("Camille").funcao("Lutador").rota("Top").build()
            )

        );
        partidaRepository.saveAll(
            List.of(
                    Partida.builder()
                    .id(1l)
                    .kill(8f)
                    .assist(4f)
                    .death(3f)
                    .kda(3f)
                    .campeao(campeaoRepository.findById(1l).get())
                    .dataInclusao(LocalDate.now().minusDays(15))
                    .resultado("VITÓRIA").build(),
                    Partida.builder()
                    .id(2l)
                    .kill(11f)
                    .assist(4f)
                    .death(6f)
                    .kda(3f)
                    .campeao(campeaoRepository.findById(2l).get())
                    .dataInclusao(LocalDate.now().minusDays(30))
                    .resultado("DERROTA").build(),
                    Partida.builder()
                    .id(3l)
                    .kill(5f)
                    .assist(3f)
                    .death(7f)
                    .kda(3f)
                    .dataInclusao(LocalDate.now().minusDays(45))
                    .campeao(campeaoRepository.findById(3l).get())
                    .resultado("DERROTA").build()
            )
        );

    }
    
}
