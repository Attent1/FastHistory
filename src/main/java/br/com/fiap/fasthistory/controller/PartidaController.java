package br.com.fiap.fasthistory.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fasthistory.model.Partida;
import br.com.fiap.fasthistory.repository.PartidaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("partida")
@Slf4j
public class PartidaController {

    record TotalPorCampeao(String campeao, Float kda, int win, Float winRate) {
    }

    @Autowired
    PartidaRepository repository;

    @GetMapping
    public Page<Partida> listarTodos(@RequestParam(required = false) String campeao,
            @PageableDefault(size = 5, sort = "campeao.nome", direction = Direction.ASC) Pageable pageable) {

        if (campeao != null) {
            return repository.findByCampeaoNomeIgnoreCase(campeao, pageable);
        }

        return repository.findAll(pageable);
    }

    // @GetMapping("todos-kda-campeao")
    // public List<TotalPorCampeao> getKdaPorCampeao() {

    // var partidas = repository.findAll();

    // Map<String, Long> partidasPorCampeao = partidas.stream()
    // .collect(Collectors.groupingBy(
    // p -> p.getCampeao().getNome(),
    // Collectors.counting()
    // ));

    // Map<String, Double> collect = partidas.stream()
    // .collect(Collectors.groupingBy(
    // p -> p.getCampeao().getNome(),
    // Collectors.summingDouble(p -> ((double)(p.getKill() + p.getAssist())) /
    // p.getDeath()) // Correção para float
    // ));

    // return collect
    // .entrySet()
    // .stream()
    // .map(e -> new TotalPorCampeao(e.getKey(), e.getValue().floatValue())) //
    // Converter para float
    // .collect(Collectors.toList());
    // }

    @GetMapping("todos-kda-campeao")
    public List<TotalPorCampeao> getKdaPorCampeao() {

        var partidas = repository.findAll();

        Map<String, Long> partidasPorCampeao = partidas.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCampeao().getNome(),
                        Collectors.counting()));

        Map<String, Double> kdaPorCampeao = partidas.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCampeao().getNome(),
                        Collectors.summingDouble(p -> ((double) (p.getKill() + p.getAssist())) / p.getDeath())));

        Map<String, Long> vitoriasPorCampeao = partidas.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCampeao().getNome(),
                        Collectors.summingLong(p -> p.getResultado().equalsIgnoreCase("VITÓRIA") ? 1L : 0L)));

        return partidasPorCampeao.entrySet().stream()
                .map(entry -> {
                    float winRate = (float) vitoriasPorCampeao.getOrDefault(entry.getKey(), 0L) / entry.getValue();
                    return new TotalPorCampeao(
                            entry.getKey(),
                            kdaPorCampeao.get(entry.getKey()).floatValue(),
                            vitoriasPorCampeao.get(entry.getKey()).intValue(),
                            winRate * 100);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Partida create(@RequestBody @Valid Partida partida) {
        Float kda;
        kda = (partida.getKill() + partida.getAssist()) / partida.getDeath();
        partida.setKda(kda);
        return repository.save(partida);
    }

    @GetMapping("{id}")
    public ResponseEntity<Partida> get(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void apagar(@PathVariable Long id) {
        log.info("Deletando partida com id: {}", id);
        verificarSeExistePartida(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    public Partida editar(@PathVariable Long id, @RequestBody Partida partida) {
        log.info("Atualizando partida com id: {}", id);
        verificarSeExistePartida(id);
        System.out.println(partida.getDataInclusao());
        Float kda;
        kda = (partida.getKill() + partida.getAssist()) / partida.getDeath();
        partida.setKda(kda);
        partida.setId(id);
        return repository.save(partida);
    }

    private void verificarSeExistePartida(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Partida não encontrada"));
    }

}
