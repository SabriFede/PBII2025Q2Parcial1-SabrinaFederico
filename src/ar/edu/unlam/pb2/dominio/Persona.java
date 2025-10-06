package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Persona {

	private String nombre;
	private Integer dni;
	private Plan plan;
	private List<Libro> libros;

	public Persona(String nombre, Integer dni, Plan plan) {
		this.nombre = nombre;
		this.dni = dni;
		this.plan = plan;
		this.libros = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}
}
