// FilmeRequest.java
package br.com.fiap.moviecatalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Dados para criar ou atualizar um filme")
public class FilmeRequest {

    @Schema(description = "Título do filme", example = "Avatar", required = true)
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @Schema(description = "Descrição/sinopse do filme", example = "Um fuzileiro naval paraplégico enviado a Pandora...", maxLength = 500)
    @Size(max = 500)
    private String descricao;

    @Schema(description = "Data de lançamento do filme", example = "2009-12-18", required = true)
    @NotNull
    private LocalDate dataLancamento;

    @Schema(description = "Nota do filme (0-10)", example = "7.5", minimum = "0", maximum = "10")
    @Min(0)
    @Max(10)
    private Double nota;

    // Getters e Setters
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