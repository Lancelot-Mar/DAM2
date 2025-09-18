package PracticaVehiculosInteligentes;

public class RoboRepartidor extends VehiculosInteligentes{

	private String tipoCarga;
	double capacidadLitros;
	String zonaOperacion;
	boolean resistenteAgua;
	int numeroRuedas;
	
	public RoboRepartidor(int id, String modelo, boolean sensoresActivos, double bateriaNivel, String fechaActivacion,
			String tipoCarga, double capacidadLitros, String zonaOperacion, boolean resistenteAgua, int numeroRuedas) {
		super(id, modelo, sensoresActivos, bateriaNivel, fechaActivacion);
		this.tipoCarga = tipoCarga;
		this.capacidadLitros = capacidadLitros;
		this.zonaOperacion = zonaOperacion;
		this.resistenteAgua = resistenteAgua;
		this.numeroRuedas = numeroRuedas;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public double getCapacidadLitros() {
		return capacidadLitros;
	}

	public void setCapacidadLitros(double capacidadLitros) {
		this.capacidadLitros = capacidadLitros;
	}

	public String getZonaOperacion() {
		return zonaOperacion;
	}

	public void setZonaOperacion(String zonaOperacion) {
		this.zonaOperacion = zonaOperacion;
	}

	public boolean isResistenteAgua() {
		return resistenteAgua;
	}

	public void setResistenteAgua(boolean resistenteAgua) {
		this.resistenteAgua = resistenteAgua;
	}

	public int getNumeroRuedas() {
		return numeroRuedas;
	}

	public void setNumeroRuedas(int numeroRuedas) {
		this.numeroRuedas = numeroRuedas;
	}

	@Override
	public String toString() {
		return "RoboRepartidor [tipoCarga=" + tipoCarga + ", capacidadLitros=" + capacidadLitros + ", zonaOperacion="
				+ zonaOperacion + ", resistenteAgua=" + resistenteAgua + ", numeroRuedas=" + numeroRuedas + ", id=" + id
				+ ", modelo=" + modelo + ", sensoresActivos=" + sensoresActivos + ", bateriaNivel=" + bateriaNivel
				+ ", fechaActivacion=" + fechaActivacion + "]";
	}

}
