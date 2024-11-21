package com.cadastro.cadastroServidor.model.dto;

import com.cadastro.cadastroServidor.model.entity.Lotacao;

import java.util.Date;

public class ServidorDto {

    private Long idServidor;
    private String nome;
    private String matricula;
    private Long idLotacao;


    public ServidorDto() {
    }

    public ServidorDto(Long idServidor, String nome, String matricula, Long lotacao) {
        this.idServidor = idServidor;
        this.nome = nome;
        this.matricula = matricula;
        this.idLotacao = lotacao;
    }

    public Long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Long idServidor) {
        this.idServidor = idServidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getLotacao() {
        return idLotacao;
    }

    public void setLotacao(Long lotacao) {
        this.idLotacao = lotacao;
    }
}
