package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Penalizacion {
	private Integer id;
	private Prestamo prestamo;
	private LocalDate fechaPenalizacion;
	private LocalDate fechaCancelacion;
	private final Double PENALIZACION_POR_DIA = 100.00;
	private Long cantidadDiasPenalizacion;

	public Penalizacion(Integer id, Prestamo prestamo, Long cantidadDiasPenalizacion) {

		this.id = id;
		this.prestamo = prestamo;
		this.fechaPenalizacion = LocalDate.now();
		this.cantidadDiasPenalizacion = cantidadDiasPenalizacion;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public LocalDate getFechaPenalizacion() {
		return fechaPenalizacion;
	}

	public void setFechaPenalizacion(LocalDate fechaPenalizacion) {
		this.fechaPenalizacion = fechaPenalizacion;
	}

	public Double getMontoPenalizacion() {
		return cantidadDiasPenalizacion * PENALIZACION_POR_DIA;
	}

	public Long getCantidadDiasPenalizacion() {
		return cantidadDiasPenalizacion;
	}

	public void setCantidadDiasPenalizacion(Long cantidadDiasPenalizacion) {
		this.cantidadDiasPenalizacion = cantidadDiasPenalizacion;
	}

	public int getId() {
		return id;
	}

	public LocalDate getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(LocalDate fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
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
		Penalizacion other = (Penalizacion) obj;
		return Objects.equals(id, other.id);
	}
}
