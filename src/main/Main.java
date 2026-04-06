package main;
import java.util.Scanner;
import model.*;
import system.SistemaHospital;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaHospital.inicializarSistema(); // Carga el admin por defecto
        int opcion = -1;

        System.out.println("============================================");
        System.out.println(" BIENVENIDO A LA CLÍNICA IBEROAMERICANA");
        System.out.println("============================================");

        do {
            try {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Iniciar Sesión");
                System.out.println("0. Salir del Sistema");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                if (opcion == 1) {
                    System.out.print("Ingrese su usuario: ");
                    String user = sc.nextLine();

                    System.out.print("Ingrese su contraseña: ");
                    String pass = sc.nextLine();

                    Usuario usuarioLogeado = SistemaHospital.login(user, pass);

                    if (usuarioLogeado == null) {
                        System.out.println("Error: Usuario o contraseña incorrectos.");
                    } else {
                        System.out.println("\n¡Bienvenido, " + usuarioLogeado.getUsername() + "!");
                        ejecutarMenuRol(usuarioLogeado, sc);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Por favor, ingrese un número.");
                sc.nextLine(); // Limpiar el error del scanner
            }
        } while (opcion != 0);

        sc.close();
        System.out.println("Sistema apagado. ¡Hasta pronto!");
    }

    // Método para separar la lógica de los menús según el Rol
    private static void ejecutarMenuRol(Usuario u, Scanner sc) {
        int opcRol = -1;

        do {
            try {
                u.mostrarMenu();
                System.out.print("Ingresa opción: ");
                opcRol = sc.nextInt();
                sc.nextLine();

                if (u instanceof Admin) {
                    switch (opcRol) {
                        case 1: // Registrar personal
                            System.out.print("Nuevo nombre de usuario: ");
                            String nUser = sc.nextLine();
                            System.out.print("Contraseña: ");
                            String nPass = sc.nextLine();
                            System.out.print("Cargo (1=Médico | 2=Recepcionista): ");
                            int cargo = sc.nextInt();
                            sc.nextLine();

                            if (cargo == 1) {
                                SistemaHospital.registrarPersonal(new Medico(nUser, nPass));
                            } else if (cargo == 2) {
                                SistemaHospital.registrarPersonal(new Recepcionista(nUser, nPass));
                            } else {
                                System.out.println("Opción de cargo inválida.");
                            }
                            break;
                        case 2:
                            SistemaHospital.verPersonal();
                            break;
                    }
                } else if (u instanceof Recepcionista) {
                    switch (opcRol) {
                        case 1: // Registrar paciente
                            System.out.print("DNI del paciente: ");
                            String dni = sc.nextLine();
                            System.out.print("Nombres completos: ");
                            String nombres = sc.nextLine();
                            System.out.print("Edad: ");
                            int edad = sc.nextInt();
                            sc.nextLine();

                            SistemaHospital.registrarPaciente(new Paciente(dni, nombres, edad));
                            break;
                        case 2:
                            SistemaHospital.verPacientesBasico();
                            break;
                    }
                } else if (u instanceof Medico) {
                    String buscarDni;
                    Paciente pEncontrado;

                    switch (opcRol) {
                        case 1: // Ver detalle clínico
                            System.out.print("Ingrese DNI del paciente a buscar: ");
                            buscarDni = sc.nextLine();
                            pEncontrado = SistemaHospital.buscarPaciente(buscarDni);
                            if (pEncontrado != null) pEncontrado.mostrarDetalleClinico();
                            else System.out.println("Error: Paciente no encontrado.");
                            break;
                        case 2: // Agregar historia clínica
                            System.out.print("Ingrese DNI del paciente: ");
                            buscarDni = sc.nextLine();
                            pEncontrado = SistemaHospital.buscarPaciente(buscarDni);
                            if (pEncontrado != null) {
                                System.out.print("Escriba la nota clínica: ");
                                String nota = sc.nextLine();
                                pEncontrado.agregarHistoriaClinica(nota);
                                System.out.println("Historia actualizada.");
                            } else {
                                System.out.println("Error: Paciente no encontrado.");
                            }
                            break;
                        case 3: // Agregar resultados
                            System.out.print("Ingrese DNI del paciente: ");
                            buscarDni = sc.nextLine();
                            pEncontrado = SistemaHospital.buscarPaciente(buscarDni);
                            if (pEncontrado != null) {
                                System.out.print("Escriba el resultado del análisis: ");
                                String analisis = sc.nextLine();
                                pEncontrado.agregarAnalisis(analisis);
                                System.out.println("Análisis agregado.");
                            } else {
                                System.out.println("Error: Paciente no encontrado.");
                            }
                            break;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: Entrada no válida en el menú.");
                sc.nextLine();
            }
        } while (opcRol != (u instanceof Medico ? 4 : 3)); // 4 es salir para medico, 3 para los demás
    }
}
