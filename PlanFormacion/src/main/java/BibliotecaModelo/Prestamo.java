package BibliotecaModelo;

public class Prestamo {

    // Atributos de prestamo dichos en el enunciado de la práctica2.1

    private int idPrestamo;
    private String isbnLibro;    // Clave foránea a Libro
    private int idUsuario;     // Clave foránea a Usuario
    private String fechaPrestamo; // ¡Cambiado a String!

    // Constructor para préstamos (con ID) (Para hacer las consultas)

    public Prestamo(int idPrestamo, String isbnLibro, int idUsuario, String fechaPrestamo) {
        this.idPrestamo = idPrestamo;
        this.isbnLibro = isbnLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
    }

    // Constructor para nuevos préstamos (sin ID) (Para registrar nuevos prestamos)

    public Prestamo(String isbnLibro, int idUsuario, String fechaPrestamo) {
        this.isbnLibro = isbnLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
    }

//----------------------------------------------------------------------------------------------------
    // Getters y Setters de los atributos de los prestamos
//----------------------------------------------------------------------------------------------------

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaPrestamo() { // ¡Cambiado el tipo de retorno!
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) { // ¡Cambiado el tipo de parámetro!
        this.fechaPrestamo = fechaPrestamo;
    }

//----------------------------------------------------------------------------------------------------
    // Overrride para que se muestre el objeto como tal
//----------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Prestamo [ID=" + idPrestamo + ", ISBN Libro='" + isbnLibro + ", ID Usuario=" + idUsuario + ", Fecha='" + fechaPrestamo + "']";
    }
}
