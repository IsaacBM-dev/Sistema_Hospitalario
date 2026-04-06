package model;

public class Medico extends Usuario {

    public Medico(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== MENÚ MÉDICO ===");
        System.out.println("1. Buscar paciente y ver detalles");
        System.out.println("2. Agregar historia clínica");
        System.out.println("3. Agregar resultados de análisis");
        System.out.println("4. Cerrar sesión");
    }
}