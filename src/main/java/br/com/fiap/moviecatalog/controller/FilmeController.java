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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/filmes")
@Tag(name = "Filmes", description = "API para gerenciamento do catálogo de filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos os filmes", description = "Retorna uma lista com todos os filmes cadastrados no catálogo")
    @ApiResponse(responseCode = "200", description = "Lista de filmes recuperada com sucesso")
    public List<FilmeResponse> findAll() {
        return service.findAll().stream().map(f -> {
            FilmeResponse response = FilmeMapper.toResponse(f);
            Link selfLink = WebMvcLinkBuilder.linkTo(FilmeController.class).slash(f.getId()).withSelfRel();
            response.add(selfLink);
            return response;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme específico baseado no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme encontrado",
                    content = @Content(schema = @Schema(implementation = FilmeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<FilmeResponse> findById(
            @Parameter(description = "ID do filme", required = true, example = "1")
            @PathVariable Long id) {
        Filme filme = service.findById(id);
        FilmeResponse response = FilmeMapper.toResponse(filme);
        response.add(WebMvcLinkBuilder.linkTo(FilmeController.class).slash(id).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo filme", description = "Adiciona um novo filme ao catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<FilmeResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do filme a ser cadastrado", required = true)
            @RequestBody @Valid FilmeRequest request) {
        Filme filme = FilmeMapper.toEntity(request);
        Filme saved = service.save(filme);
        FilmeResponse response = FilmeMapper.toResponse(saved);
        response.add(WebMvcLinkBuilder.linkTo(FilmeController.class).slash(saved.getId()).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme existente no catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<FilmeResponse> update(
            @Parameter(description = "ID do filme a ser atualizado", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Novos dados do filme", required = true)
            @RequestBody @Valid FilmeRequest request) {
        Filme filme = FilmeMapper.toEntity(request);
        Filme updated = service.update(id, filme);
        FilmeResponse response = FilmeMapper.toResponse(updated);
        response.add(WebMvcLinkBuilder.linkTo(FilmeController.class).slash(updated.getId()).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Excluir filme", description = "Remove um filme do catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do filme a ser excluído", required = true, example = "1")
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}