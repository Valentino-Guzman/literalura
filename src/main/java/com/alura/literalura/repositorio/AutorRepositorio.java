package com.alura.literalura.repositorio;

import com.alura.literalura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a")
    List<Autor> findAutores();

    @Query("SELECT a FROM Autor a " +
            "WHERE a.fechaNacimiento <= :anio " +
            "AND (a.fechaMuerte IS NULL OR a.fechaMuerte >= :anio)")
    List<Autor> findAutoresVivosEnAnio(int anio);
}
