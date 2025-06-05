package AgendaContactos;

public class Contacto {
    private String nombre;
    private String telefono;
    private String email;
    private String ciudad;
    private String grupo;

//----------------------------------------------------------------------------------------------------------------------
    // Constructor
//----------------------------------------------------------------------------------------------------------------------

    public Contacto(String nombre, String telefono, String email, String ciudad, String grupo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.ciudad = ciudad;
        this.grupo = grupo;
    }

//----------------------------------------------------------------------------------------------------------------------
    // Getters (para leer los datos)
//----------------------------------------------------------------------------------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

