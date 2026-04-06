package model;

public class Recepcionista extends Usuario {

    public Recepcionista(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== MENÚ RECEPCIÓN ===");
        System.out.println("1. Registrar nuevo paciente");
        System.out.println("2. Ver lista de pacientes registrados");
        System.out.println("3. Cerrar sesión");
    }
}