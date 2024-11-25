package com.cadastro.cadastroServidor.service;

import com.cadastro.cadastroServidor.model.dto.LotacaoDto;
import com.cadastro.cadastroServidor.model.dto.ServidorDto;
import com.cadastro.cadastroServidor.model.entity.Lotacao;
import com.cadastro.cadastroServidor.model.entity.Servidor;
import com.cadastro.cadastroServidor.repository.ServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServidorService {

    private ServidorRepository servidorRepository;

    private LotacaoService lotacaoService;

    @Autowired
    public ServidorService(ServidorRepository servidorRepository,LotacaoService lotacaoService) {
        this.servidorRepository = servidorRepository;
        this.lotacaoService = lotacaoService;
    }


    public ServidorDto create(ServidorDto servidorDto){
        // objeto lotação -> colocar em uma variavel
        LotacaoDto lotacaoServidor = lotacaoService.findById(servidorDto.getLotacao());
        Servidor servidor = new Servidor();
        servidor.setData(new Date());
        servidor.setNome(servidorDto.getNome());
        servidor.setMatricula(servidorDto.getMatricula());
        // lotacao do findById
        servidor.setLotacao(new Lotacao(servidorDto.getLotacao()));
        Servidor servidor1 = servidorRepository.save(servidor);
        return new ServidorDto(servidor1.getId(), servidorDto.getNome(), servidorDto.getMatricula(), servidorDto.getLotacao());

    }

    public List<ServidorDto> findAll(){
        List<Servidor> servidorList = servidorRepository.findAll();
        List<ServidorDto> servidorDto = new ArrayList<>();

        for ( Servidor servidor1 : servidorList){
            servidorDto.add(new ServidorDto(
                    servidor1.getId(),
                    servidor1.getNome(),
                    servidor1.getMatricula(),
                    servidor1.getLotacao().getId()));
        }

        return servidorDto;
    }

    public ServidorDto findById(Long id) {
        Optional<Servidor> servidor = servidorRepository.findById(id);

        if (servidor.isPresent()) {
            Servidor servidor1 = servidor.get();

            return new ServidorDto(
                    servidor1.getId(),
                    servidor1.getNome(),
                    servidor1.getMatricula(),
                    servidor1.getLotacao().getId()
            );
        }
        throw new RuntimeException("A Servidor não existe");
    }

    public ServidorDto update(ServidorDto servidorDto){

        ServidorDto servidorDto1 = findById(servidorDto.getIdServidor());

        Servidor servidor= new Servidor();
        servidor.setId(servidorDto.getIdServidor());

            if (servidorDto.getNome() != null) {
                servidor.setNome(servidorDto.getNome());
            }

            if (servidorDto.getLotacao() != null ){
            LotacaoDto servidorLotacao = lotacaoService.findById(servidorDto.getLotacao());
                if (servidorLotacao != null && servidorLotacao.getId() != servidorDto1.getLotacao()){
                servidor.setLotacao(new Lotacao(servidorDto1.getLotacao()));
            } else {
                    throw new RuntimeException("Não foi possivel atualizar servidor.");
                }
        }

        Servidor servidorAtualizado = servidorRepository.save(servidor);

        return new ServidorDto(
                servidorAtualizado.getId(),
                servidorAtualizado.getMatricula(),
                servidorAtualizado.getNome(),
                servidorAtualizado.getId());

    }

    public void delete(Long id) {
        findById(id);
        servidorRepository.deleteById(id);
    }
}
