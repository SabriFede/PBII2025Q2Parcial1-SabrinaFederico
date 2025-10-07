package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Biblioteca {

	private Set<Libro> libros;
	private Set<Persona> socios;
	private List<Prestamo> prestamos;

	private Set<Penalizacion> penalizaciones;

	public Biblioteca() {
		this.libros = new HashSet<>();
		this.socios = new HashSet<>();
		this.prestamos = new ArrayList<>();
		this.penalizaciones = new HashSet<Penalizacion>();
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}

	public Boolean agregarLibro(Libro libro) {
		return this.libros.add(libro);
	}

	public Boolean agregarSocio(Persona persona) {
		return this.socios.add(persona);
	}

	public Set<Persona> getSocios() {
		return socios;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Persona buscarSocioPorDni(Integer dni) {
		for (Persona p : this.socios) {
			if (p.getDni().equals(dni)) {
				return p;
			}
		}
		return null;
	}

	public Libro buscarLibroPorNombre(String nombre) {
		for (Libro l : this.libros) {
			if (l.getTitulo().equalsIgnoreCase(nombre)) {
				return l;
			}
		}
		return null;
	}

	public Prestamo buscarPrestamoPorSocioYLibro(Persona socio, Libro libro) {
		for (Prestamo p : this.prestamos) {
			if (p.getSocio().equals(socio) && p.getLibro().equals(libro)) {
				return p;
			}
		}
		return null;
	}

	public Boolean prestarLibro(Prestamo p) {
		for (Penalizacion penalizacion : penalizaciones) {
			if (p.getSocio().equals(penalizacion.getPrestamo().getSocio())
					&& penalizacion.getFechaCancelacion() == null) {
				return false;
			}
		}

		if (p.getLibro().getStock() != 0
				&& p.getSocio().getLibros().size() < p.getSocio().getPlan().getCantidadMaximaDeLibrosSimultaneos()) {
			p.getSocio().getLibros().add(p.getLibro());
			p.getLibro().setStock(p.getLibro().getStock() - 1);
			p.getSocio().setContadorPrestamos(p.getSocio().getContadorPrestamos() + 1);
			return this.prestamos.add(p);
		}
		return false;
	}

	public Boolean devolverLibro(Prestamo prestamo) {
		LocalDate fechaDeDevolucion = LocalDate.now();
		if (fechaDeDevolucion.isBefore(prestamo.getFechaDePrestamo())) {
			return false;
		}

		if (fechaDeDevolucion.isAfter(prestamo.getFechaDeDevolucion())) {
			Long diasDeRetraso = ChronoUnit.DAYS.between(prestamo.getFechaDeDevolucion(), fechaDeDevolucion);

			Integer nuevoId = 0;

			for (Penalizacion penalizacion : penalizaciones) {
				if (nuevoId < penalizacion.getId()) {
					nuevoId = penalizacion.getId();
				}
			}

			penalizaciones.add(new Penalizacion(nuevoId + 1, prestamo, diasDeRetraso));
		}

		prestamo.getSocio().getLibros().remove(prestamo.getLibro());
		prestamo.getLibro().setStock(prestamo.getLibro().getStock() + 1);
		return this.prestamos.remove(prestamo);
	}

	public Double calcularCuotaAPagarDeUnSocio(Persona socio) {
		return socio.getPlan().calcularCuota(socio) + socio.getMontoPagarPenalizacion();
	}
}
