package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record LibreriaDTO(
        @JsonAlias("count") Integer contador,
        @JsonAlias("results") List<LibroDTO> libros
) {
}
