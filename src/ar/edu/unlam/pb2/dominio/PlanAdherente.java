package ar.edu.unlam.pb2.dominio;

public class PlanAdherente extends Plan {

	public PlanAdherente(Double precioBase) {
		super(precioBase);
		this.setCantidadMaximaDeLibrosSimultaneos(1);
	}

	@Override
	public String toString() {
		return "Adherente; Precio Base: $" + getPrecioBase();
	}
}