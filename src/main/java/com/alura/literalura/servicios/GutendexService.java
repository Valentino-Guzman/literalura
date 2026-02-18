package com.alura.literalura.servicios;

import com.alura.literalura.dto.LibreriaDTO;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GutendexService {
    private static final HttpClient client = HttpClient.newHttpClient();
    private final ConvierteDatos conversor;

    private String apiUrl = "https://gutendex.com/books/";

    public GutendexService(ConvierteDatos conversor) {
        this.conversor = conversor;
    }

    public LibreriaDTO consultarLibros(String busqueda) {
        URI direccion = URI.create(apiUrl + "?search=" + busqueda.replace(" ", "%20"));
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consultar su libro");
        }

        return conversor.obtenerDatos(response.body(), LibreriaDTO.class);
    }
}
