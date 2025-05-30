package BibliotecaModelo;

public class Usuario {

    // Atributos del usuario que se han estado utilizando para la práctica 2.1 y 2.2

    private int idUsuario;
    private String nombre;
    private String telefono; // Incluimos el teléfono

    // Constructor para usuarios existentes (con ID) (Para hacer las consultas)
    public Usuario(int idUsuario, String nombre, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Constructor para nuevos usuarios (sin ID) (Para registrar nuevos usuarios)
    public Usuario(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

//----------------------------------------------------------------------------------------------------
    // Getters y Setters de los atributos del usuario
//----------------------------------------------------------------------------------------------------

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

//----------------------------------------------------------------------------------------------------
    // Overrride para que se muestre el objeto como tal
//----------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Usuario [ID=" + idUsuario + ", Nombre='" + nombre + "', Telefono='" + telefono + "']";
    }
}