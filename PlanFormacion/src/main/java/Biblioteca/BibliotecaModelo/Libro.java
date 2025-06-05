package Biblioteca.BibliotecaModelo;

public class Libro {

    // Atributos de libro dichos en el enunciado de la práctica2.1

    private String isbn;
    private String titulo;
    private String categoria;

    // Constructor
    public Libro(String isbn, String titulo, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
    }

//----------------------------------------------------------------------------------------------------
    // Getters y Setters de los atributos de libro
//----------------------------------------------------------------------------------------------------

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

//----------------------------------------------------------------------------------------------------
    // Overrride para que se muestre el objeto como tal
//----------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Libro [ISBN=" + isbn + ", Titulo='" + titulo + "', Categoria='" + categoria + "']";
    }
}