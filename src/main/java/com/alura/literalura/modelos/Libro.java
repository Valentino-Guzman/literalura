package com.alura.literalura.modelos;

import com.alura.literalura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;
    @Column(columnDefinition = "TEXT")
    private String resumen;
    @ElementCollection
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private Set<String> idioma;
    private Integer cantidadDescargas;

    public Libro() {}

    public Libro(String titulo, Set<Autor> autores, String resumen, Set<String> idioma, Integer cantidadDescargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.idioma = idioma;
        this.cantidadDescargas = cantidadDescargas;
    }

    public Set<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(Set<String> idioma) {
        this.idioma = idioma;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public String mostrarLibroFormateado() {
        String autoresFormateados = this.autores.stream()
                .map(a -> a.getNombre() +
                        " (" + a.getFechaNacimiento() +
                        " - " + a.getFechaMuerte() + ")")
                .collect(Collectors.joining(", "));

        String idiomasFormateados = String.join(", ", this.idioma);

        return """
            ----------------------------------------
            Título: %s
            Autor(es): %s
            Idioma: %s
            Descargas: %d

            Resumen:
            %s
            ----------------------------------------
            """.formatted(
                this.titulo,
                autoresFormateados,
                idiomasFormateados,
                this.cantidadDescargas,
                this.resumen
                        .replace("{\"", "")
                        .replace("\"}", "")
        );
    }


    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", resumen='" + resumen + '\'' +
                ", idioma='" + idioma + '\'' +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
