package ar.edu.unlam.pb2.dominio;

public class Libro {

	private String titulo;
	private TipoDeLibro tipo;
	private Integer stock;

	public Libro(String titulo, TipoDeLibro tipo, Integer stock) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.stock = stock;
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

}
