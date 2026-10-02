import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoCSV {
    // Constantes para las rutas de los archivos CSV
    private static final String CARPETA_DB = "db";
    // Constantes para las rutas de los archivos CSV
    private static final String ARCHIVO_DOCTORES =
            CARPETA_DB + File.separator + "doctores.csv";
    private static final String ARCHIVO_PACIENTES =
            CARPETA_DB + File.separator + "pacientes.csv";
    private static final String ARCHIVO_CITAS =
            CARPETA_DB + File.separator + "citas.csv";

    public ArchivoCSV() {
        inicializarArchivos();
    }

    private void inicializarArchivos() {
        File carpeta = new File(CARPETA_DB);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        // Primero buscar si los archivos existen, si no existen, se crean
        crearArchivoSiNoExiste(ARCHIVO_DOCTORES);
        crearArchivoSiNoExiste(ARCHIVO_PACIENTES);
        crearArchivoSiNoExiste(ARCHIVO_CITAS);
    }

    private void crearArchivoSiNoExiste(String ruta) {
        File archivo = new File(ruta);
        // Igualmente se verifica si el archivo existe, si no existe, se crea
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println(
                        "No se pudo crear el archivo: " + ruta
                );
            }
        }
    }

    public void guardarDoctor(Doctor doctor) {
        // Se utiliza FileWriter para escribir en el archivo CSV correspondiente
        // Con el try-catch se maneja la excepción en caso de que ocurra un error al escribir en el archivo
        try (FileWriter writer =
                     new FileWriter(ARCHIVO_DOCTORES, true)) {
            writer.write(
                    doctor.getId() + ";" +
                    doctor.getNombreCompleto() + ";" +
                    doctor.getEspecialidad() +
                    System.lineSeparator()
            );
        } catch (IOException e) {
            System.out.println(
                    "Error al guardar el doctor: " +
                    e.getMessage()
            );
        }
    }

    public void guardarPaciente(Paciente paciente) {
        // Aquí otro manejo de excepción para guardar un paciente en el archivo CSV correspondiente
        try (FileWriter writer =
                     new FileWriter(ARCHIVO_PACIENTES, true)) {
            writer.write(
                    paciente.getId() + ";" +
                    paciente.getNombreCompleto() +
                    System.lineSeparator()
            );
        } catch (IOException e) {
            System.out.println(
                    "Error al guardar el paciente: " +
                    e.getMessage()
            );
        }
    }

    public void guardarCita(Cita cita) {
        // Manejo de excepción para guardar una cita en el archivo CSV correspondiente
        try (FileWriter writer =
                     new FileWriter(ARCHIVO_CITAS, true)) {
            writer.write(
                    cita.getId() + ";" +
                    cita.getFechaHora() + ";" +
                    cita.getMotivo() + ";" +
                    cita.getDoctor().getId() + ";" +
                    cita.getPaciente().getId() +
                    System.lineSeparator()
            );
        } catch (IOException e) {
            System.out.println(
                    "Error al guardar la cita: " +
                    e.getMessage()
            );
        }
    }

    public List<Doctor> cargarDoctores() {
        // Se crea una lista para almacenar los doctores cargados desde el archivo CSV
        List<Doctor> doctores = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(ARCHIVO_DOCTORES))) {
            String linea;
            // Se lee cada línea del archivo CSV y se separa por el delimitador ";"
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] datos = linea.split(";");
                if (datos.length == 3) {

                    doctores.add(
                            new Doctor(
                                    datos[0],
                                    datos[1],
                                    datos[2]
                            )
                    );
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "No se pudieron cargar los doctores."
            );
        }
        // Retorna la lista de doctores cargados desde el archivo CSV
        return doctores;
    }

    public List<Paciente> cargarPacientes() {
        // Lo mismo que en cargarDoctores, pero para pacientes
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(ARCHIVO_PACIENTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] datos = linea.split(";");
                if (datos.length == 2) {
                    pacientes.add(
                            new Paciente(
                                    datos[0],
                                    datos[1]
                            )
                    );
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "No se pudieron cargar los pacientes."
            );
        }
        return pacientes;
    }


    public List<Cita> cargarCitas(
            List<Doctor> doctores,
            List<Paciente> pacientes) {
        // Lo mismo que en cargarDoctores y cargarPacientes, pero para citas
        List<Cita> citas = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(ARCHIVO_CITAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] datos = linea.split(";");
                if (datos.length == 5) {
                    Doctor doctor = buscarDoctor(
                            doctores,
                            datos[3]
                    );
                    Paciente paciente = buscarPaciente(
                            pacientes,
                            datos[4]
                    );
                    if (doctor != null && paciente != null) {
                        citas.add(
                                new Cita(
                                        datos[0],
                                        datos[1],
                                        datos[2],
                                        doctor,
                                        paciente
                                )
                        );
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "No se pudieron cargar las citas."
            );
        }
        return citas;
    }

    private Doctor buscarDoctor(
            List<Doctor> doctores,
            String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    private Paciente buscarPaciente(
            List<Paciente> pacientes,
            String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
}