package ar.edu.unlam.pb2.interfaz;

public enum OpcionesMenu {
AGREGAR_SOCIO("Agregar un nuevo Socio"), MOSTRAR_LISTA_SOCIOS ("Listar los socios"), AGREGAR_LIBRO("Agregar nuevo Titulo"), SALIR ("Salir");

private String descripcion;

private OpcionesMenu(String descripcion) {
	this.descripcion = descripcion;
}

public String getDescripcion() {
	return this.descripcion;
}

}
