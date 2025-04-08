package br.com.fiap.moviecatalog.service;

import br.com.fiap.moviecatalog.model.Filme;
import br.com.fiap.moviecatalog.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public List<Filme> findAll() {
        return repository.findAll();
    }

    public Filme findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    public Filme save(Filme filme) {
        return repository.save(filme);
    }

    public Filme update(Long id, Filme novoFilme) {
        Filme existente = findById(id);
        existente.setTitulo(novoFilme.getTitulo());
        existente.setDescricao(novoFilme.getDescricao());
        existente.setDataLancamento(novoFilme.getDataLancamento());
        existente.setNota(novoFilme.getNota());
        return repository.save(existente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
