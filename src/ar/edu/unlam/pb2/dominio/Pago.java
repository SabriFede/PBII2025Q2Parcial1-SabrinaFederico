package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Pago implements ICancelable {

	private Integer id;
	private Penalizacion penalizacion;
	private LocalDate fechaPago;

	public Pago(Integer id, Penalizacion penalizacion, LocalDate fechaPago) {
		this.id = id;
		this.penalizacion = penalizacion;
		this.fechaPago = fechaPago;
	}

	public Integer getId() {
		return id;
	}

	public Penalizacion getPenalizacion() {
		return penalizacion;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	@Override
	public Double monto() {
		// Comentario para generar conflicto
		return penalizacion.getMontoPenalizacion();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pago other = (Pago) obj;
		return Objects.equals(id, other.id);
	}

}
