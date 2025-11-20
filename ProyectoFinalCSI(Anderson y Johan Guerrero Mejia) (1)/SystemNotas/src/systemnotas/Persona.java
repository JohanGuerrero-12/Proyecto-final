package systemnotas;

/**
 *
 * @author 57322
 */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private String nickname;
    private String rol;
    private String password;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String nickname, String rol, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.rol = rol;
        this.password = password;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    
    
}
