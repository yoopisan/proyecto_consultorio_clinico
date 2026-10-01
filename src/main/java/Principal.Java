package proyecto_consultorio_clinico;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    // Atributos correspondientes, solicitados en el enunciado del proyecto
    // Hice un scanner para capturar la entrada del usuario y listas para almacenar los doctores, pacientes y citas
    private static Scanner scanner = new Scanner(System.in);
    // Esta es una lista de objetos de tipo Doctor, que se utiliza para almacenar los doctores registrados en el sistema.
    private static List<Doctor> doctores = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Cita> citas = new ArrayList<>();
    // Esta es una instancia de la clase ArchivoCSV, que se utiliza para cargar y guardar los datos de doctores, pacientes y citas en archivos CSV.
    private static ArchivoCSV archivoCSV;

    public static void main(String[] args) {
        // Primero que nada se crea una instancia de la clase ArchivoCSV para manejar la carga y guardado de datos en archivos CSV
        archivoCSV = new ArchivoCSV();
        // Luego se llama a la función cargarInformacion() para cargar los datos de doctores, pacientes y citas desde los archivos CSV correspondientes
        cargarInformacion();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");
            try {
                switch (opcion) {
                    case 1:
                        registrarDoctor();
                        break;
                    case 2:
                        registrarPaciente();
                        break;
                    case 3:
                        crearCita();
                        break;
                    case 4:
                        consultarDoctores();
                        break;
                    case 5:
                        consultarPacientes();
                        break;
                    case 6:
                        consultarCitas();
                        break;
                    case 7:
                        System.out.println(
                                "Programa finalizado."
                        );
                        break;
                    default:
                        System.out.println(
                                "Opción no válida."
                        );
                }
            } catch (Exception e) {
                System.out.println(
                        "Ocurrió un error: " +
                        e.getMessage()
                );
                System.out.println(
                        "El programa continuará ejecutándose."
                );
            }
        } while (opcion != 7);
        scanner.close();
    }

    private static void mostrarMenu() {
        // Mostrar el menú principal del sistema de citas médicas
        System.out.println();
        System.out.println("=================================");
        System.out.println(" SISTEMA DE CITAS MÉDICAS");
        System.out.println("=================================");
        System.out.println("1. Registrar doctor");
        System.out.println("2. Registrar paciente");
        System.out.println("3. Crear cita");
        System.out.println("4. Consultar doctores");
        System.out.println("5. Consultar pacientes");
        System.out.println("6. Consultar citas");
        System.out.println("7. Salir");
        System.out.println("=================================");
    }

    private static void cargarInformacion() {
        // Cargar la información de doctores, pacientes y citas desde los archivos CSV
        doctores = archivoCSV.cargarDoctores();
        pacientes = archivoCSV.cargarPacientes();
        citas = archivoCSV.cargarCitas(
                doctores,
                pacientes
        );
        System.out.println(
                "Información cargada correctamente."
        );
    }

    private static void registrarDoctor() {
        // Registra un nuevo doctor en el sistema
        System.out.println();
        System.out.println("=== REGISTRAR DOCTOR ===");
        String id = leerTexto(
                "Identificador: "
        );
        // Verifica si ya existe un doctor con el mismo identificador
        if (buscarDoctor(id) != null) {
            System.out.println(
                    "Ya existe un doctor con ese identificador."
            );
            return;
        }
        // Solicita al usuario el nombre completo y la especialidad del doctor
        String nombre = leerTexto(
                "Nombre completo: "
        );
        // Solicita al usuario la especialidad del doctor
        String especialidad = leerTexto(
                "Especialidad: "
        );
        // Crea un nuevo objeto Doctor con el identificador, nombre y especialidad proporcionados
        Doctor doctor = new Doctor(
                id,
                nombre,
                especialidad
        );
        // Agrega el doctor a la lista de doctores registrados
        doctores.add(doctor);
        // Se guarda el doctor en el archivo CSV correspondiente
        archivoCSV.guardarDoctor(doctor);
        System.out.println(
                "Doctor registrado correctamente."
        );
    }

    private static void registrarPaciente() {
        // Registra un nuevo paciente en el sistema
        System.out.println();
        System.out.println("=== REGISTRAR PACIENTE ===");
        // Solicita al usuario el identificador y nombre completo del paciente
        String id = leerTexto(
                "Identificador: "
        );
        // Verifica si ya existe un paciente con el mismo identificador
        if (buscarPaciente(id) != null) {

            System.out.println(
                    "Ya existe un paciente con ese identificador."
            );

            return;
        }
        // Solicita al usuario el nombre completo del paciente
        String nombre = leerTexto(
                "Nombre completo: "
        );
        // Crea un nuevo objeto Paciente con el identificador y nombre proporcionados
        Paciente paciente = new Paciente(
                id,
                nombre
        );
        // Agregar el paciente a la lista de pacientes registrados
        pacientes.add(paciente);
        // Se guarda el paciente en el archivo CSV correspondiente
        archivoCSV.guardarPaciente(paciente);
        // Imprime un mensaje indicando que el paciente ha sido registrado correctamente
        System.out.println(
                "Paciente registrado correctamente."
        );
    }

    private static void crearCita() {
        // Crea una nueva cita médica en el sistema
        System.out.println();
        System.out.println("=== CREAR CITA ===");
        // Solicita al usuario el identificador de la cita
        String id = leerTexto(
                "Identificador de la cita: "
        );
        // Verifica si ya existe una cita con el mismo identificador
        if (buscarCita(id) != null) {
            System.out.println(
                    "Ya existe una cita con ese identificador."
            );
            return;
        }
        // Solicita al usuario el identificador del doctor para la cita
        String idDoctor = leerTexto(
                "Identificador del doctor: "
        );
        // Busca el doctor correspondiente al identificador proporcionado
        Doctor doctor = buscarDoctor(idDoctor);
        // Verifica si el doctor existe en la lista de doctores registrados
        if (doctor == null) {
            System.out.println(
                    "No existe un doctor con ese identificador."
            );
            return;
        }
        // Solicita al usuario el identificador del paciente para la cita
        String idPaciente = leerTexto(
                "Identificador del paciente: "
        );
        // Busca el paciente correspondiente al identificador proporcionado
        Paciente paciente = buscarPaciente(idPaciente);
        // Verifica si el paciente existe en la lista de pacientes registrados
        if (paciente == null) {
            System.out.println(
                    "No existe un paciente con ese identificador."
            );
            return;
        }
        // Solicita al usuario la fecha y hora de la cita
        String fechaHora = leerTexto(
                "Fecha y hora: "
        );
        // Solicita al usuario el motivo de la cita
        String motivo = leerTexto(
                "Motivo de la cita: "
        );
        // Crea un nuevo objeto Cita con el identificador, fecha y hora, motivo, doctor y paciente proporcionados
        Cita cita = new Cita(
                id,
                fechaHora,
                motivo,
                doctor,
                paciente
        );
        // Por último, agrega la cita a la lista de citas registradas
        citas.add(cita);
        // Se guarda la cita en el archivo CSV correspondiente
        archivoCSV.guardarCita(cita);
        System.out.println(
                "Cita registrada correctamente."
        );
    }

    private static void consultarDoctores() {
        // Consultar y mostrar la lista de doctores registrados en el sistema
        System.out.println();
        System.out.println("=== DOCTORES ===");
        if (doctores.isEmpty()) {
            System.out.println(
                    "No hay doctores registrados."
            );
            return;
        }
        for (Doctor doctor : doctores) {
            System.out.println(doctor);
        }
    }

    private static void consultarPacientes() {
        // Consultar y mostrar la lista de pacientes registrados en el sistema
        System.out.println();
        System.out.println("=== PACIENTES ===");
        if (pacientes.isEmpty()) {
            System.out.println(
                    "No hay pacientes registrados."
            );
            return;
        }
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    private static void consultarCitas() {
        // Consultar y mostrar la lista de citas registradas en el sistema
        System.out.println();
        System.out.println("=== CITAS ===");
        // Verifica si la lista de citas está vacía
        if (citas.isEmpty()) {
            System.out.println(
                    "No hay citas registradas."
            );
            return;
        }
        // Recorre la lista de citas y muestra la información de cada cita en la consola
        for (Cita cita : citas) {
            System.out.println(cita);
        }
    }

    private static Doctor buscarDoctor(String id) {
        // Busca un doctor en la lista de doctores por su identificador
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    private static Paciente buscarPaciente(String id) {
        // Busca un paciente en la lista de pacientes por su identificador
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    private static Cita buscarCita(String id) {
        // Busca una cita en la lista de citas por su identificador
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    private static String leerTexto(String mensaje) {
        // Leer una línea de texto desde la entrada del usuario y eliminar espacios en blanco al inicio y al final
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        // Leer un número entero desde la entrada del usuario
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = Integer.parseInt(
                        scanner.nextLine()
                );
                return numero;
            } catch (NumberFormatException e) {
                System.out.println(
                        "Introduce un número válido."
                );
            }
        }
    }
}