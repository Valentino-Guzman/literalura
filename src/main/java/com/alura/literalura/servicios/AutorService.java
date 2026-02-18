package com.alura.literalura.servicios;

import com.alura.literalura.repositorio.AutorRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepositorio autorRepositorio;

    public AutorService(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }
}
