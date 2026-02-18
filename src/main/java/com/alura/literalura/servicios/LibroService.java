package com.alura.literalura.servicios;

import com.alura.literalura.repositorio.LibroRepositorio;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    private final LibroRepositorio libroRepositorio;

    public LibroService(LibroRepositorio libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }
}
