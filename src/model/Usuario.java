package model;

	public class Usuario {
	    private String username;
	    private String password;

	    public Usuario(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    // Método que será sobreescrito por los hijos
	    public void mostrarMenu() {
	        System.out.println("Menú base");
	    }
	}