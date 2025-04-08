// FilmeResponse.java
package br.com.fiap.moviecatalog.dto;

import org.springframework.hateoas.RepresentationModel;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Representação de um filme no catálogo")
public class FilmeResponse extends RepresentationModel<FilmeResponse> {

    @Schema(description = "ID único do filme", example = "1")
    private Long id;

    @Schema(description = "Título do filme", example = "Avatar")
    private String titulo;

    @Schema(description = "Descrição/sinopse do filme", example = "Um fuzileiro naval paraplégico enviado a Pandora...")
    private String descricao;

    @Schema(description = "Data de lançamento do filme", example = "2009-12-18")
    private LocalDate dataLancamento;

    @Schema(description = "Nota do filme (0-10)", example = "7.5")
    private Double nota;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}