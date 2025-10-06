package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Prestamo {

	private Persona socio;
	private Libro libro;
	private LocalDate fechaDePrestamo;
	private LocalDate fechaDeDevolucion;

	public Prestamo(Persona socio, Libro libro, LocalDate fechaDePrestamo) {
		this.socio = socio;
		this.libro = libro;
		this.fechaDePrestamo = fechaDePrestamo;
		this.fechaDeDevolucion = calcularFechaDevolucionLimite();
	}

	public Persona getSocio() {
		return socio;
	}

	public void setSocio(Persona socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public LocalDate getFechaDePrestamo() {
		return fechaDePrestamo;
	}

	public void setFechaDePrestamo(LocalDate fechaDePrestamo) {
		this.fechaDePrestamo = fechaDePrestamo;
	}

	public LocalDate getFechaDeDevolucion() {
		return fechaDeDevolucion;
	}

	public void setFechaDeDevolucion(LocalDate fechaDeDevolucion) {
		this.fechaDeDevolucion = fechaDeDevolucion;
	}

	public LocalDate calcularFechaDevolucionLimite() {
		
		switch (this.libro.getTipo()) {
		case NOVELA:
			return this.fechaDePrestamo.plusMonths(1);
		case COMIC:
			return this.fechaDePrestamo.plusWeeks(2);
		case ENCICLOPEDIA:
			return this.fechaDePrestamo.plusYears(1);
			default:
				return null;
		}
	}

}
