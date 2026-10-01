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

    public static void main(String[] args) {
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
                        System.out.println(
                                "En desarrollo..."
                        );
                        //crearCita();
                        break;
                    case 4:
                        consultarDoctores();
                        break;
                    case 5:
                        consultarPacientes();
                        break;
                    case 6:
                        System.out.println(
                                "En desarrollo..."
                        );
                        //consultarCitas();
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
        // archivoCSV.guardarDoctor(doctor);
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
        // archivoCSV.guardarPaciente(paciente);
        // Imprime un mensaje indicando que el paciente ha sido registrado correctamente
        System.out.println(
                "Paciente registrado correctamente."
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