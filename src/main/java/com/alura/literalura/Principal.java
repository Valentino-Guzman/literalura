package com.alura.literalura;

import com.alura.literalura.modelos.Autor;
import com.alura.literalura.modelos.Libro;
import com.alura.literalura.repositorio.AutorRepositorio;
import com.alura.literalura.repositorio.LibroRepositorio;
import com.alura.literalura.servicios.GutendexService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Principal {
    int opcion;
    private GutendexService servicio;
    private LibroRepositorio libroRepositorio;
    private AutorRepositorio autorRepositorio;
    private List<Libro> libros;
    private List<Autor> autores;
    private List<Autor> autoresVivos;
    private List<Libro> librosPorIdioma;
    private Scanner teclado = new Scanner(System.in);

    public Principal(GutendexService gutendexService, LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
        this.servicio = gutendexService;
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void mostrarMenu() {
        while (opcion != 6) {
            String menu = """
                    ==============================
                    LITERALURA - MENÚ PRINCIPAL
                    ==============================
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                    6 - Salir
                    """;
            System.out.println(menu);
            System.out.print("Ingrese una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    consultarLibroPorTitulo();
                    break;
                case 2:
                    mostrarUltimosLibros();
                    break;
                case 3:
                    mostrarUltimosAutores();
                    break;
                case 4:
                    mostrarAutoresVivos();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta intente de nuevo...");
            }
        }
    }

    private void consultarLibroPorTitulo() {
        int repetir;
        do {
            teclado.nextLine();
            System.out.print("Ingrese el nombre del libro a buscar: ");
            String busqueda = teclado.nextLine();
            var respuesta = servicio.consultarLibros(busqueda);

            if (respuesta.libros().isEmpty()) {
                System.out.println("No se encontraron resultados con: " + busqueda);
            } else {
                var libro = respuesta.libros().get(0);

                if (libroRepositorio.findByTitulo(libro.titulo()).isPresent()) {
                    System.out.println("Libro o autor ya guardado en base de datos.");
                } else {
                    System.out.println("\n-------------LIBRO ENCONTRADO---------------");
                    System.out.println("Título: " + libro.titulo());
                    System.out.print("Autor(es): ");
                    libro.autores().forEach(a ->
                            System.out.print(a.nombre() + " ")
                    );
                    System.out.println("\nIdioma: " + libro.idioma());
                    System.out.println("Descargas: " + libro.cantidadDescargas());
                    System.out.println("----------------------------------------------\n");
                    Set<Autor> autoresGuardar = libro.autores().stream()
                            .map(a -> {
                                Autor autor = new Autor(
                                        a.nombre(),
                                        a.fechaNacimiento(),
                                        a.fechaMuerte()
                                );
                                return autorRepositorio.save(autor);
                            })
                            .collect(Collectors.toSet());
                    String resumen = (libro.resumen() != null && !libro.resumen().isEmpty())
                            ? libro.resumen().get(0)
                            : "Sin resumen disponible";
                    Set<String> idiomas = new HashSet<>(libro.idioma());

                    Libro libroGuardar = new Libro(libro.titulo(), autoresGuardar, resumen, idiomas, libro.cantidadDescargas());
                    libroRepositorio.save(libroGuardar);
                }
            }
            System.out.println("Desea realizar otra busqueda?");
            System.out.print("[1-SI][2-NO]: ");
            repetir = teclado.nextInt();
        } while (repetir == 1);
    }
    private void mostrarUltimosLibros() {
        System.out.println("*********** ULTIMOS LIBROS REGISTRADOS ***************");
        libros = libroRepositorio.findTop5ConAutores();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(l ->
                    System.out.println(l.mostrarLibroFormateado())
            );
        }
        System.out.println("**********************************************");
    }
    private void mostrarUltimosAutores() {
        System.out.println("*********** ULTIMOS AUTORES REGISTRADOS ***************");

        autores = autorRepositorio.findAutores();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(a ->
                    System.out.println(a.mostrarAutorFormateado()));
        }
        System.out.println("**********************************************");
    }
    private void mostrarAutoresVivos() {
        System.out.print("Ingrese una fecha para buscar autores vivos en determinado año: ");
        int anio = teclado.nextInt();
        autoresVivos = autorRepositorio.findAutoresVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autoresVivos.forEach(av ->
                    System.out.println(av.mostrarAutorFormateado()));
        }
    }
    private void buscarLibroPorIdioma() {
        System.out.print("Ingrese un idioma para la busqueda [en], [fr], [fi]: " );
        String idioma = teclado.nextLine();
        librosPorIdioma = libroRepositorio.findByIdiomaConAutores(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros con ese idioma.");
        } else {
            librosPorIdioma.forEach(l ->
                    System.out.println(l.mostrarLibroFormateado())
            );
        }
    }
}
