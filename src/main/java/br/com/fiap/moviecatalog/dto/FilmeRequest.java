package br.com.fiap.moviecatalog.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class FilmeRequest {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @Size(max = 500)
    private String descricao;

    @NotNull
    private LocalDate dataLancamento;

    @Min(0)
    @Max(10)
    private Double nota;

    public @NotBlank(message = "O título é obrigatório") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "O título é obrigatório") String titulo) {
        this.titulo = titulo;
    }

    public @Size(max = 500) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Size(max = 500) String descricao) {
        this.descricao = descricao;
    }

    public @NotNull LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(@NotNull LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public @Min(0) @Max(10) Double getNota() {
        return nota;
    }

    public void setNota(@Min(0) @Max(10) Double nota) {
        this.nota = nota;
    }
}
