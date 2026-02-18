    package com.alura.literalura.modelos;

    import com.alura.literalura.dto.LibroDTO;
    import com.fasterxml.jackson.annotation.JsonAlias;
    import jakarta.persistence.Entity;

    import java.util.List;

    public class Libreria {
        private Integer contador;
        private List<LibroDTO> libros;
    }
