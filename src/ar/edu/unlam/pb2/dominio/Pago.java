package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Pago implements ICancelable {

	private Penalizacion penalizacion;
	private LocalDate fechaPago;

	public Pago(Penalizacion penalizacion, LocalDate fechaPago) {
		this.penalizacion = penalizacion;
		this.fechaPago = fechaPago;
	}

	public Penalizacion getPenalizacion() {
		return penalizacion;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	@Override
	public Double monto() {
		return penalizacion.getMontoPenalizacion();
	}

}
