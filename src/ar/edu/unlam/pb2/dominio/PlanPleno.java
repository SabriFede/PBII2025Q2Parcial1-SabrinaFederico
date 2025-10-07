package ar.edu.unlam.pb2.dominio;

public class PlanPleno extends Plan implements ICalculable{

	public PlanPleno(Double precioBase) {
		super(precioBase);
		this.setCantidadMaximaDeLibrosSimultaneos(2);
	}
	
	@Override
	public String toString() {
		return "Pleno; Precio Base: $" + getPrecioBase();
	}

	@Override
	public Double calcularCuota(Persona p) {
		if(p.getContadorPrestamos() >= 5) {
			return this.getPrecioBase() * 0.85;
		}
		return this.getPrecioBase();
	}
}
