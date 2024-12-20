package com.cadastro.cadastroServidor.controller;

import com.cadastro.cadastroServidor.model.entity.Lotacao;
import com.cadastro.cadastroServidor.model.dto.LotacaoDto;
import com.cadastro.cadastroServidor.service.LotacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lotacao")
public class LotacaoController {

    private LotacaoService lotacaoService;

    public LotacaoController(LotacaoService lotacaoService) {
        this.lotacaoService = lotacaoService;
    }

    // pedir como paramentro apenas nomeLotação
    @PostMapping
    public LotacaoDto create(@Valid @RequestBody LotacaoDto lotacaoDto){
        return lotacaoService.create(lotacaoDto);
    }

    @GetMapping
    List<LotacaoDto> findAll(){
        return lotacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lotacaoService.findById(id));
    }

    @PutMapping
    public LotacaoDto update (@RequestBody LotacaoDto lotacaoDto){
        return lotacaoService.update(lotacaoDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        lotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
