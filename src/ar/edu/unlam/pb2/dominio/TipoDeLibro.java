package ar.edu.unlam.pb2.dominio;

public enum TipoDeLibro {
	NOVELA("Novela"), COMIC("Comic"), ENCICLOPEDIA("Enciclopedia");

	private String descripcion;

	private TipoDeLibro(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}