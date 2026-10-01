package proyecto_consultorio_clinico;

public class Paciente {
    // Atributos correspondiente, solicitados en el enunciado del proyecto
    private String id;
    private String nombreCompleto;
    // Constructor de la clase Paciente
    public Paciente(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }
    // Métodos getter y setter para los atributos de la clase Paciente
    public String getId() {
        return id;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    @Override
    public String toString() {
        return id + " | " + nombreCompleto;
    }
}