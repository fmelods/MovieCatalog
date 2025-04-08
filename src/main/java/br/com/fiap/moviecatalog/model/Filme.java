package br.com.fiap.moviecatalog.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(
        name = "Filme",
        description = "Entidade principal do sistema que representa um filme no catálogo",
        example = "{\"id\": 1, \"titulo\": \"Interestelar\", \"descricao\": \"Exploração espacial...\", \"dataLancamento\": \"2014-11-06\", \"nota\": 8.6}"
)
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID único gerado automaticamente pelo banco de dados",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Título oficial do filme (máx. 100 caracteres)",
            example = "Matrix",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 100
    )
    private String titulo;

    @Schema(
            description = "Sinopse completa do filme (máx. 500 caracteres)",
            example = "Um hacker descobre a verdade sobre a realidade...",
            maxLength = 500
    )
    private String descricao;

    @Schema(
            description = "Data de lançamento no formato ISO (YYYY-MM-DD)",
            example = "1999-05-21",
            type = "string",
            format = "date",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDate dataLancamento;

    @Schema(
            description = "Nota média de avaliação (0.0 = ruim, 10.0 = excelente)",
            example = "9.7",
            minimum = "0.0",
            maximum = "10.0"
    )
    private Double nota;

    // --- Getters e Setters (MANTIDOS ORIGINAIS) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
}