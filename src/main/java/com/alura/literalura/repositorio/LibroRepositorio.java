package com.alura.literalura.repositorio;

import com.alura.literalura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT DISTINCT l FROM Libro l LEFT JOIN FETCH l.autores LEFT JOIN FETCH l.idioma ORDER BY l.id DESC")
    List<Libro> findTop5ConAutores();

    @Query("""
            SELECT DISTINCT l
            FROM Libro l
            JOIN FETCH l.autores
            LEFT JOIN FETCH l.idioma
            WHERE :idioma MEMBER OF l.idioma
           """)
    List<Libro> findByIdiomaConAutores(@Param("idioma") String idioma);

}
