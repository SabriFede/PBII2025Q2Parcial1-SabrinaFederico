package ar.edu.unlam.pb2.dominio;

public class PlanAdherente extends Plan implements ICalculable {

	public PlanAdherente(Double precioBase) {
		super(precioBase);
		this.setCantidadMaximaDeLibrosSimultaneos(1);
	}

	@Override
	public String toString() {
		return "Adherente; Precio Base: $" + getPrecioBase();
	}

	@Override
	public Double calcularCuota(Persona p) {
		if(p.getContadorPrestamos() >= 3) {
			return this.getPrecioBase() * 0.95;
		}
		return this.getPrecioBase();
	}
}