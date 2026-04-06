package system;
import java.util.ArrayList;
import model.*;

public class SistemaHospital {
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Paciente> pacientes = new ArrayList<>();

    // Crear un administrador por defecto al iniciar
    public static void inicializarSistema() {
        usuarios.add(new Admin("admin", "1234"));
    }

    // Lógica de Login
    public static Usuario login(String user, String pass) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                return u;
            }
        }
        return null; // Credenciales incorrectas
    }

    // Gestión de Personal
    public static void registrarPersonal(Usuario u) {
        for (Usuario user : usuarios) {
            if (user.getUsername().equals(u.getUsername())) {
                System.out.println("Error: El nombre de usuario ya existe.");
                return;
            }
        }
        usuarios.add(u);
        System.out.println("Éxito: Personal registrado correctamente.");
    }

    public static void verPersonal() {
        System.out.println("--- Lista de Personal ---");
        for (Usuario u : usuarios) {
            String cargo = (u instanceof Admin) ? "Administrador" : 
                           (u instanceof Medico) ? "Médico" : "Recepcionista";
            System.out.println("Usuario: " + u.getUsername() + " | Cargo: " + cargo);
        }
    }

    // Gestión de Pacientes
    public static void registrarPaciente(Paciente p) {
        if (buscarPaciente(p.getDni()) != null) {
            System.out.println("Error: Ya existe un paciente con ese DNI.");
            return;
        }
        pacientes.add(p);
        System.out.println("Éxito: Paciente registrado correctamente.");
    }

    public static Paciente buscarPaciente(String dni) {
        for (Paciente p : pacientes) {
            if (p.getDni().equals(dni)) {
                return p;
            }
        }
        return null;
    }

    public static void verPacientesBasico() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        for (Paciente p : pacientes) {
            p.mostrarResumen();
        }
    }
}