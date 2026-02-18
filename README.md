# Literalura

**Literalura** es una aplicación de consola desarrollada con Spring Boot que permite buscar, consultar y gestionar información sobre libros y autores, integrando datos desde la API pública Gutendex y almacenándolos localmente en una base de datos para consultas posteriores.

---

## Tabla de Contenidos

- [Descripción](#descripción)  
- [Características](#características)  
- [Tecnologías](#tecnologías)  
- [Arquitectura y Diseño](#arquitectura-y-diseño)  
- [Instalación](#instalación)  
- [Uso](#uso)  

---

## Descripción

Literalura facilita la consulta interactiva de libros y autores a través de una interfaz de línea de comandos, integrándose con la API Gutendex para obtener datos en tiempo real. Los resultados relevantes se almacenan localmente, optimizando la gestión de información y evitando duplicados.

---

## Características principales

- Búsqueda de libros por título con integración a API externa.  
- Registro y listado de libros y autores almacenados en base de datos.  
- Consulta de autores vivos en un año específico.  
- Filtrado de libros por idioma.  
- Persistencia inteligente que evita duplicados.  
- Interfaz de usuario basada en consola con menú interactivo.  

---

## Tecnologías

- **Java 17+**  
- **Spring Boot** para configuración y gestión del ciclo de vida de la aplicación.  
- **Spring Data JPA** para acceso y persistencia de datos.  
- **H2 / PostgreSQL / MySQL** (configurable) como sistema gestor de base de datos.  
- **Gutendex API** para obtención de datos literarios.  
- **Maven / Gradle** como herramienta de construcción y gestión de dependencias.

---

## Arquitectura y Diseño

La aplicación sigue el patrón MVC simplificado para aplicaciones de consola:

- **Modelo:** Entidades `Libro` y `Autor` que representan datos persistentes.  
- **Repositorio:** Interfaces `LibroRepositorio` y `AutorRepositorio` que extienden `JpaRepository` para manejo de base de datos.  
- **Servicio:** `GutendexService` que encapsula la lógica de integración con la API externa.  
- **Controlador / Vista:** Clase `Principal` que gestiona la interacción con el usuario mediante un menú en consola y coordina los servicios y repositorios.

---

## Instalación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/tu_usuario/literalura.git
   cd literalura
2. Configurar la base de datos en src/main/resources/application.properties.
3. Construir el proyecto con Gradle o Maven:
4. Ejecutar la aplicación

## Uso

Al iniciar, la aplicación muestra un menú interactivo en consola. Ejemplo:
==============================
LITERALURA - MENÚ PRINCIPAL
==============================
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en determinado año
5 - Listar libros por idioma
6 - Salir

Ingrese una opción:
• Selecciona la opción deseada escribiendo el número correspondiente.
• Sigue las instrucciones para ingresar datos como títulos, años o idiomas.
• Los datos consultados se almacenan automáticamente en la base de datos.

