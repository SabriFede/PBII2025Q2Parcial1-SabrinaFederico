package ar.edu.unlam.pb2.interfaz;

import java.time.LocalDate;
import java.util.Scanner;

import ar.edu.unlam.pb2.dominio.Biblioteca;
import ar.edu.unlam.pb2.dominio.Libro;
import ar.edu.unlam.pb2.dominio.Persona;
import ar.edu.unlam.pb2.dominio.Plan;
import ar.edu.unlam.pb2.dominio.PlanAdherente;
import ar.edu.unlam.pb2.dominio.PlanPleno;
import ar.edu.unlam.pb2.dominio.Prestamo;
import ar.edu.unlam.pb2.dominio.TipoDeLibro;

public class InterfazBiblioteca {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		Biblioteca biblioteca = new Biblioteca();

		OpcionesMenu opcionMenu = null;

		do {
			opcionMenu = ingresarOpcionDeMenuPrincipalValida();

			switch (opcionMenu) {
			case AGREGAR_SOCIO:
				agregarSocio(biblioteca);
				break;

			case AGREGAR_LIBRO:
				agregarLibro(biblioteca);
				break;

			case PRESTAR_LIBRO:
				prestarLibro(biblioteca);
				break;

			case DEVOLVER_LIBRO:
				devolverLibro(biblioteca);
				break;

			case MOSTRAR_LISTA_SOCIOS:
				mostrarListaSocios(biblioteca);
				break;

			case VER_CUOTA_A_PAGAR_DE_UN_SOCIO:
				verCuotaDeUnSocio(biblioteca);
				break;

			default:
				break;
			}

		} while (opcionMenu != OpcionesMenu.SALIR);

	}

	private static void agregarSocio(Biblioteca biblioteca) {
		String nombre;
		Integer dni;

		nombre = ingresarString("Nombre del socio: ");
		dni = ingresarEntero("DNI del socio: ");

		int opcion = 0;
		mostrarPorPantalla("--- Tipos de planes disponibles ---");
		do {
			mostrarPorPantalla("1- Adherente");
			mostrarPorPantalla("2- Pleno");
			opcion = ingresarEntero("elija un tipo de plan: ");
		} while (opcion < 1 || opcion > 2);

		switch (opcion) {
		case 1:
			Plan planAdherente = new PlanAdherente(1000.0);
			Persona socioAdherente = new Persona(nombre, dni, planAdherente);
			if (biblioteca.agregarSocio(socioAdherente)) {
				mostrarPorPantalla("Se agregó un nuevo socio adherente");
			} else {
				mostrarPorPantalla("Ya existe un socio con ese DNI");
			}
			break;
		case 2:
			Plan planPleno = new PlanPleno(2000.0);
			Persona socioPleno = new Persona(nombre, dni, planPleno);
			if (biblioteca.agregarSocio(socioPleno)) {
				mostrarPorPantalla("Se agregó un nuevo socio adherente");
			} else {
				mostrarPorPantalla("Ya existe un socio con ese DNI");
			}
			break;
		}
	}

	private static void agregarLibro(Biblioteca biblioteca) {
		String titulo;
		TipoDeLibro tipo;
		Integer stock;

		titulo = ingresarString("Titulo del libro: ");
		tipo = ingresarOpcionDeTipoDeLibroValida();
		stock = ingresarEntero("Stock del libro: ");
		/* TODO: revisar */
		Libro libro = new Libro(1, titulo, tipo, stock);
		biblioteca.agregarLibro(libro);
		mostrarPorPantalla("Libro agregado exitosamente");
	}

	private static void prestarLibro(Biblioteca biblioteca) {
		Libro libro;
		Persona socio;
		Integer dniDelSocio = 0;
		String nombreDelLibro = "";
		LocalDate fechaPrestamo;

		dniDelSocio = ingresarEntero("Ingrese dni del socio que realiza el prestamo: ");
		nombreDelLibro = ingresarString("Ingrese nombre del libro que el socio pide prestado: ");

		if (biblioteca.buscarSocioPorDni(dniDelSocio) != null
				&& biblioteca.buscarLibroPorNombre(nombreDelLibro) != null) {
			socio = biblioteca.buscarSocioPorDni(dniDelSocio);
			libro = biblioteca.buscarLibroPorNombre(nombreDelLibro);
			fechaPrestamo = ingresarFecha("Fecha en la que se realizo el prestamo: ");
			Prestamo prestamo = new Prestamo(socio, libro, fechaPrestamo);
			if (biblioteca.prestarLibro(prestamo)) {
				mostrarPorPantalla("Se realizo el prestamo exitosamente");
			} else {
				mostrarPorPantalla("No se pudo realizar el prestamo");
			}
		} else {
			mostrarPorPantalla("Socio o libro no existente");
		}
	}

	private static void devolverLibro(Biblioteca biblioteca) {
		Libro libro;
		Persona socio;
		Integer dniDelSocio = 0;
		String nombreDelLibro = "";
		LocalDate fechaDeDevolucion;

		dniDelSocio = ingresarEntero("Ingrese dni del socio que realiza el prestamo: ");
		nombreDelLibro = ingresarString("Ingrese nombre del libro que el socio pide prestado: ");

		if (biblioteca.buscarSocioPorDni(dniDelSocio) != null
				&& biblioteca.buscarLibroPorNombre(nombreDelLibro) != null) {
			socio = biblioteca.buscarSocioPorDni(dniDelSocio);
			libro = biblioteca.buscarLibroPorNombre(nombreDelLibro);
			Prestamo prestamo = biblioteca.buscarPrestamoPorSocioYLibro(socio, libro);

			if (prestamo != null) {
				if (biblioteca.devolverLibro(prestamo)) {
					mostrarPorPantalla("El libro fue devuelto");
				} else {
					mostrarPorPantalla(
							"La fecha de devolucion no puede ser anterior a la fecha en la que se presto el libro");
				}
			} else {
				mostrarPorPantalla("No se pudo encontrar ese prestamo");
			}

		} else {
			mostrarPorPantalla("Socio o libro no existente");
		}
	}

	private static void verCuotaDeUnSocio(Biblioteca biblioteca) {
		Persona socio;
		Integer dniDelSocio = 0;

		dniDelSocio = ingresarEntero("Ingrese dni del socio del cual se desea saber su cuota: ");

		if (biblioteca.buscarSocioPorDni(dniDelSocio) != null) {
			socio = biblioteca.buscarSocioPorDni(dniDelSocio);
			Double cuotaAPagar = biblioteca.calcularCuotaAPagarDeUnSocio(socio);
			mostrarPorPantalla("El monto a pagar para este socio es de: $" + cuotaAPagar);
		} else {
			mostrarPorPantalla("Socio o libro no existente");
		}
	}

	private static LocalDate ingresarFecha(String mensaje) {
		mostrarPorPantalla(mensaje);
		Integer anio = ingresarEntero("Ingrese anio: ");
		Integer mes = ingresarEntero("Ingrese mes: ");
		Integer dia = ingresarEntero("Ingrese dia: ");

		return LocalDate.of(anio, mes, dia);
	}

	private static void mostrarListaSocios(Biblioteca biblioteca) {
		if (biblioteca.getSocios().isEmpty()) {
			mostrarPorPantalla("Todavia no hay socios registrados");
		} else {
			for (Persona persona : biblioteca.getSocios()) {
				mostrarPorPantalla("Nombre: " + persona.getNombre() + "; DNI: " + persona.getDni() + "; Plan: "
						+ persona.getPlan());
			}
		}
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static OpcionesMenu ingresarOpcionDeMenuPrincipalValida() {
		mostrarMenuPrincipal();
		Integer opcionElegida = ingresarEntero("Ingrese la opcion deseada");
		if (opcionElegida > 0 && opcionElegida <= OpcionesMenu.values().length) {
			return OpcionesMenu.values()[opcionElegida - 1];
		} else {
			return null;
		}
	}

	private static void mostrarMenuPrincipal() {
		String menuPrincipal = "\n***** SISTEMA DE GESTION DE BIBLIOTECA *****\n";
		for (int i = 0; i < OpcionesMenu.values().length; i++) {
			menuPrincipal += (i + 1) + "- " + OpcionesMenu.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menuPrincipal);
	}

	private static Integer ingresarEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static TipoDeLibro ingresarOpcionDeTipoDeLibroValida() {
		Integer opcion = 0;
		do {
			mostrarMenuTipoDeLibro();
			opcion = ingresarEntero("tipo de libro: ");
		} while (opcion < 1 || opcion > TipoDeLibro.values().length);

		return TipoDeLibro.values()[opcion - 1];
	}

	private static void mostrarMenuTipoDeLibro() {
		String tiposDeLibro = "\nIngrese el genero del libro\n";
		for (int i = 0; i < TipoDeLibro.values().length; i++) {
			tiposDeLibro += (i + 1) + "- " + TipoDeLibro.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(tiposDeLibro);
	}
}