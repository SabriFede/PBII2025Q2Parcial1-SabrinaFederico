package ar.edu.unlam.pb2.interfaz;

public enum OpcionesMenu {
	AGREGAR_SOCIO("Agregar un nuevo Socio"), AGREGAR_LIBRO("Agregar nuevo libro"), PRESTAR_LIBRO("Prestar un libro"), DEVOLVER_LIBRO("Devolver un libro"),
	MOSTRAR_LISTA_SOCIOS("Mostrar lista de socios"), VER_CUOTA_A_PAGAR_DE_UN_SOCIO("Ver monto a pagar por un determinado socio"), SALIR("Salir");

	private String descripcion;

	private OpcionesMenu(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
