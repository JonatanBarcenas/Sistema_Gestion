package PublImpacto.SistemaGestion.model;

public class Prioridad {
	private int id;
    private String nivel;
    private int valor;

    public Prioridad(int id, String nivel, int valor) {
        this.id = id;
        this.nivel = nivel;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return nivel;  // Mostrar el nombre en el JComboBox
    }
}
