package PublImpacto.SistemaGestion.model;

public class Cliente {

	 private int id;
	    private String nombre;
	    private String email;
	    private String telefono;
	    private String empresa;

	    public Cliente(int id, String nombre, String email, String telefono, String empresa) {
	        this.id = id;
	        this.nombre = nombre;
	        this.email = email;
	        this.telefono = telefono;
	        this.empresa = empresa;
	    }

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

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getEmpresa() {
	        return empresa;
	    }

	    public void setEmpresa(String empresa) {
	        this.empresa = empresa;
	    }
	    
	    @Override
	    public String toString() {
	        return nombre;  // Mostrar el nombre en el JComboBox
	    }
}
