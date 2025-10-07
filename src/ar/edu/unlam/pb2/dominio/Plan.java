package ar.edu.unlam.pb2.dominio;

public abstract class Plan implements ICalculable {
	private Double precioBase;
	private Integer cantidadMaximaDeLibrosSimultaneos;

	public Plan(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Integer getCantidadMaximaDeLibrosSimultaneos() {
		return cantidadMaximaDeLibrosSimultaneos;
	}

	public void setCantidadMaximaDeLibrosSimultaneos(Integer cantidadMaximaDeLibrosSimultaneos) {
		this.cantidadMaximaDeLibrosSimultaneos = cantidadMaximaDeLibrosSimultaneos;
	}
}
