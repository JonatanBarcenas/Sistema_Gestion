package PublImpacto.SistemaGestion.model;

public class Usuario {
	
	private int id;
    private String nombre;
    private String email;
    private String password;
    private int rolId;

    // Constructor
    public Usuario(int id, String nombre, String email, String password, int rolId) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rolId = rolId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

}
