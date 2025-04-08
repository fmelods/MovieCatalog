package br.com.fiap.moviecatalog.repository;

import br.com.fiap.moviecatalog.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {}
