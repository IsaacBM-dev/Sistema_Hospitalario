package model;

public class Paciente {
    private String dni;
    private String nombres;
    private int edad;
    private String historiaClinica;
    private String resultadosAnalisis;

    public Paciente(String dni, String nombres, int edad) {
        this.dni = dni;
        this.nombres = nombres;
        this.edad = edad;
        this.historiaClinica = "Sin historial registrado.";
        this.resultadosAnalisis = "Sin análisis pendientes.";
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void agregarHistoriaClinica(String detalle) {
        if (this.historiaClinica.equals("Sin historial registrado.")) {
            this.historiaClinica = detalle;
        } else {
            this.historiaClinica += " | " + detalle;
        }
    }

    public void agregarAnalisis(String resultado) {
        if (this.resultadosAnalisis.equals("Sin análisis pendientes.")) {
            this.resultadosAnalisis = resultado;
        } else {
            this.resultadosAnalisis += " | " + resultado;
        }
    }

    public void mostrarResumen() {
        System.out.println("DNI: " + dni + " | Paciente: " + nombres + " | Edad: " + edad);
    }

    public void mostrarDetalleClinico() {
        System.out.println("\n--- Expediente Clínico de " + nombres + " ---");
        System.out.println("DNI: " + dni);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Historia Clínica: " + historiaClinica);
        System.out.println("Resultados de Análisis: " + resultadosAnalisis);
        System.out.println("-----------------------------------");
    }
}