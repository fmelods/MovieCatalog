package br.com.fiap.moviecatalog.controller;

import br.com.fiap.moviecatalog.dto.FilmeRequest;
import br.com.fiap.moviecatalog.dto.FilmeResponse;
import br.com.fiap.moviecatalog.mapper.FilmeMapper;
import br.com.fiap.moviecatalog.model.Filme;
import br.com.fiap.moviecatalog.service.FilmeService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public List<FilmeResponse> findAll() {
        return service.findAll().stream().map(f -> {
            FilmeResponse response = FilmeMapper.toResponse(f);
            Link selfLink = WebMvcLinkBuilder.linkTo(FilmeController.class).slash(f.getId()).withSelfRel();
            response.add(selfLink);
            return response;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<FilmeResponse> findById(@PathVariable Long id) {
        Filme filme = service.findById(id);
        FilmeResponse response = FilmeMapper.toResponse(filme);
        response.add(WebMvcLinkBuilder.linkTo(FilmeController.class).slash(id).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FilmeResponse> create(@RequestBody @Valid FilmeRequest request) {
        Filme filme = FilmeMapper.toEntity(request);
        Filme saved = service.save(filme);
        FilmeResponse response = FilmeMapper.toResponse(saved);
        response.add(WebMvcLinkBuilder.linkTo(FilmeController.class).slash(saved.getId()).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<FilmeResponse> update(@PathVariable Long id, @RequestBody @Valid FilmeRequest request) {
        Filme filme = FilmeMapper.toEntity(request);
        Filme updated = service.update(id, filme);
        FilmeResponse response = FilmeMapper.toResponse(updated);
        response.add(WebMvcLinkBuilder.linkTo(FilmeController.class).slash(updated.getId()).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
