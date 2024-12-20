package com.cadastro.cadastroServidor.service;

import com.cadastro.cadastroServidor.model.entity.Lotacao;
import com.cadastro.cadastroServidor.model.dto.LotacaoDto;
import com.cadastro.cadastroServidor.repository.LotacaoRepository;
import com.cadastro.cadastroServidor.repository.ServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LotacaoService {
    private final LotacaoRepository lotacaoRepository;
    private final ServidorRepository servidorRepository;

    @Autowired
    public LotacaoService(LotacaoRepository lotacaoRepository, ServidorRepository servidorRepository ) {
        this.lotacaoRepository = lotacaoRepository;
        this.servidorRepository = servidorRepository;
    }

    //USAR O CREATE DO SERVIDORSERVICE COMO EXEMPLO
           // pedir como paramentro apenas nomeLotação
    public LotacaoDto create(LotacaoDto lotacaoDto){
        Lotacao lotacao = new Lotacao();
        lotacao.setDataCadastro(new Date());
        // converter para dto
        lotacao.setDescricao(lotacaoDto.getDescricao());
        //cade o save
        Lotacao lotacao1 = lotacaoRepository.save(lotacao);
        //colocar o retorno do save em uma variavel
        return new LotacaoDto(lotacao1.getId(),lotacao1.getDescricao());
        //colocar o retorno do save em uma variavel

    }

    public List<LotacaoDto> findAll(){
        List<Lotacao> lotacao = lotacaoRepository.findAll();
        List<LotacaoDto> lotacaoDto = new ArrayList<>();

        for ( Lotacao lotacao1 : lotacao){
            lotacaoDto.add(new LotacaoDto(
                    lotacao1.getId(),
                    lotacao1.getDescricao()));
        }
        return lotacaoDto;
    }

    public LotacaoDto findById(Long id) {
        Optional<Lotacao> lotacao = lotacaoRepository.findById(id);

        if (lotacao.isPresent()) {
            return new LotacaoDto(
                    lotacao.get().getId(),
                    lotacao.get().getDescricao());
        }

        throw new RuntimeException("A lotação não existe");
    }

    public LotacaoDto update(LotacaoDto lotacaoDto){
        //Verificar se ela existe com o FindById
        LotacaoDto lotacaoDto1 = findById(lotacaoDto.getId());
        Lotacao lotacao = new Lotacao();
        lotacao.setId(lotacaoDto.getId());
            if (lotacaoDto.getDescricao() != null){
                lotacao.setId(lotacaoDto.getId());
                throw new RuntimeException("Erro ao atualizar Lotacao");
            }

        Lotacao lotacaoAtualizada = lotacaoRepository.save(lotacao);
        return new LotacaoDto(
                lotacaoAtualizada.getDescricao());
    }

    public void delete(Long id){
        findById(id);
        if (servidorRepository.existsByLotacaoId(id)){
            throw new RuntimeException("Existe servidor cadastrado com essa lotacao!");
        }
        lotacaoRepository.deleteById(id);
    }
}
