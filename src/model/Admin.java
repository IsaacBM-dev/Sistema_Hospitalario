package model;

public class Admin extends Usuario {

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== MENÚ ADMINISTRADOR ===");
        System.out.println("1. Registrar nuevo personal (Médico/Recepcionista)");
        System.out.println("2. Ver todo el personal");
        System.out.println("3. Cerrar sesión");
    }
}