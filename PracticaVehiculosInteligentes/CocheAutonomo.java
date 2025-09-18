package PracticaVehiculosInteligentes;

public class CocheAutonomo extends VehiculosInteligentes{

	int nivelAutonomia;
	double velocicdadMaxima;
	String Matricula;
	int numeroPasajeros;
	String softwareVersion;
	
	public CocheAutonomo(int id, String modelo, boolean sensoresActivos, double bateriaNivel, String fechaActivacion,
			int nivelAutonomia, double velocicdadMaxima, String matricula, int numeroPasajeros,
			String softwareVersion) {
		super(id, modelo, sensoresActivos, bateriaNivel, fechaActivacion);
		this.nivelAutonomia = nivelAutonomia;
		this.velocicdadMaxima = velocicdadMaxima;
		Matricula = matricula;
		this.numeroPasajeros = numeroPasajeros;
		this.softwareVersion = softwareVersion;
	}

	public int getNivelAutonomia() {
		return nivelAutonomia;
	}

	public void setNivelAutonomia(int nivelAutonomia) {
		this.nivelAutonomia = nivelAutonomia;
	}

	public double getVelocicdadMaxima() {
		return velocicdadMaxima;
	}

	public void setVelocicdadMaxima(double velocicdadMaxima) {
		this.velocicdadMaxima = velocicdadMaxima;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public int getNumeroPasajeros() {
		return numeroPasajeros;
	}

	public void setNumeroPasajeros(int numeroPasajeros) {
		this.numeroPasajeros = numeroPasajeros;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	@Override
	public String toString() {
		return "CocheAutonomo [nivelAutonomia=" + nivelAutonomia + ", velocicdadMaxima=" + velocicdadMaxima
				+ ", Matricula=" + Matricula + ", numeroPasajeros=" + numeroPasajeros + ", softwareVersion="
				+ softwareVersion + ", id=" + id + ", modelo=" + modelo + ", sensoresActivos=" + sensoresActivos
				+ ", bateriaNivel=" + bateriaNivel + ", fechaActivacion=" + fechaActivacion + "]";
	}
	
}
