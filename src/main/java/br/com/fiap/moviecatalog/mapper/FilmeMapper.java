package br.com.fiap.moviecatalog.mapper;

import br.com.fiap.moviecatalog.dto.FilmeRequest;
import br.com.fiap.moviecatalog.dto.FilmeResponse;
import br.com.fiap.moviecatalog.model.Filme;

public class FilmeMapper {

    public static Filme toEntity(FilmeRequest request) {
        Filme filme = new Filme();
        filme.setTitulo(request.getTitulo());
        filme.setDescricao(request.getDescricao());
        filme.setDataLancamento(request.getDataLancamento());
        filme.setNota(request.getNota());
        return filme;
    }

    public static FilmeResponse toResponse(Filme filme) {
        FilmeResponse response = new FilmeResponse();
        response.setId(filme.getId());
        response.setTitulo(filme.getTitulo());
        response.setDescricao(filme.getDescricao());
        response.setDataLancamento(filme.getDataLancamento());
        response.setNota(filme.getNota());
        return response;
    }
}
