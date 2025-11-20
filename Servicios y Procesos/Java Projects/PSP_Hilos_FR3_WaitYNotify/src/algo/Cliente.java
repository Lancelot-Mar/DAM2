package algo;

import java.util.Date;

public class Cliente {
	
	private static int contador = 0; //Static se comparte en todas las instancias de cliente
	
	private String nombre;
	private String ape1;
	private String ape2;
	private String tlf;
	private String dni;
	private Date f_nac;
	private boolean isActive;
	
	public Cliente(String nombre, String ape1, String ape2, String tlf, String dni, Date f_nac, boolean isActive) {
		super();
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.tlf = tlf;
		this.dni = dni;
		this.f_nac = f_nac;
		this.isActive = isActive;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Cliente.contador = contador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe1() {
		return ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getF_nac() {
		return f_nac;
	}

	public void setF_nac(Date f_nac) {
		this.f_nac = f_nac;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", tlf=" + tlf + ", dni=" + dni
				+ ", f_nac=" + f_nac + ", isActive=" + isActive + "]";
	}
	
	public float calcularSueldo() {
		Math.PI;
	}
	
}
