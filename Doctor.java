package proyecto_consultorio_clinico;

public class Doctor {
    // Atributos correspondiente, solicitados en el enunciado del proyecto
    private String id;
    private String nombreCompleto;
    private String especialidad;
    // Constructor de la clase Doctor
    public Doctor(String id, String nombreCompleto, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }
    // Métodos getter y setter para los atributos de la clase Doctor
    public String getId() {
        return id;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    // Método toString para representar la información del doctor en formato de cadena
    @Override
    public String toString() {
        return id + " | " + nombreCompleto + " | " + especialidad;
    }
}