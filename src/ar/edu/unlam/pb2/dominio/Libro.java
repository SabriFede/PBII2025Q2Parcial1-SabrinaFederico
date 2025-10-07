package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Libro {

	private Integer id;
	private String titulo;
	private TipoDeLibro tipo;
	private Integer stock;

	public Libro(Integer id, String titulo, TipoDeLibro tipo, Integer stock) {
		this.id = id;
		this.titulo = titulo;
		this.tipo = tipo;
		this.stock = stock;
	}

	
	public Integer getId() {
		return id;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoDeLibro getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeLibro tipo) {
		this.tipo = tipo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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
		Libro other = (Libro) obj;
		return Objects.equals(id, other.id);
	}
}
