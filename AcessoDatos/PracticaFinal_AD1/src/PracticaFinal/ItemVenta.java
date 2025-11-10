package PracticaFinal;

import java.io.Serializable;

public class ItemVenta implements Serializable {
    
	private int codigoProducto;
    private int unidades;
    private float precioUnitario;
	
    public ItemVenta(int codigoProducto, int unidades, float precioUnitario) {
		super();
		this.codigoProducto = codigoProducto;
		this.unidades = unidades;
		this.precioUnitario = precioUnitario;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
    
    public float getSubtotal() {
        return unidades * precioUnitario;
    }
 
}
