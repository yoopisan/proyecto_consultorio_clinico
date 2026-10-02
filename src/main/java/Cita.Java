public class Cita {
    // Atributos correspondiente, solicitados en el enunciado del proyecto
    private String id;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;
    // Constructor de la clase Cita
    public Cita(String id, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }
    // Métodos getter y setter para los atributos de la clase Cita
    public String getId() {
        return id;
    }
    public String getFechaHora() {
        return fechaHora;
    }
    public String getMotivo() {
        return motivo;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    @Override
    public String toString() {
        return id + " | " +
                fechaHora + " | " +
                motivo + " | Doctor: " +
                doctor.getNombreCompleto() +
                " | Paciente: " +
                paciente.getNombreCompleto();
    }
}