package com.alura.literalura.dto;

import com.alura.literalura.modelos.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record LibroDTO(
        Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<AutorDTO> autores,
        @JsonAlias("summaries") List<String> resumen,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer cantidadDescargas
) {
}
