package ar.edu.unlam.pb2.interfaz;

import java.time.LocalDate;
import java.util.Scanner;

import ar.edu.unlam.pb2.dominio.Biblioteca;
import ar.edu.unlam.pb2.dominio.Libro;
import ar.edu.unlam.pb2.dominio.Penalizacion;
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

			case REGISTRAR_UN_PAGO:
				registrarPago(biblioteca);
				break;

			default:
				break;
			}

		} while (opcionMenu != OpcionesMenu.SALIR);

	}

	private static void registrarPago(Biblioteca biblioteca) {

		Integer dni = ingresarEntero("Ingrese el DNI del socio");
		for (Penalizacion penalizacion : biblioteca.buscarPenalizacionesPorDni(dni)) {
			if (penalizacion.getFechaCancelacion() == null) {
				mostrarPorPantalla("ID de la penalizacion" + penalizacion.getId() + " Monto a pagar: $"
						+ penalizacion.getMontoPenalizacion());
			}
		}

		Integer idAPagar = ingresarEntero("Ingrese el ID de la penalizacion a pagar");
		Penalizacion penalizacionAPagar = biblioteca.buscarPenalizacionPorId(idAPagar);

		if (biblioteca.registrarPago(penalizacionAPagar)) {
			mostrarPorPantalla("El pago fue registrado correctamente");
		} else {
			mostrarPorPantalla("No se pudo registrar el pago");
		}

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
		Integer id;
		String titulo;
		TipoDeLibro tipo;
		Integer stock;

		id = ingresarEntero("Ingrese el ID del libro: ");
		titulo = ingresarString("Titulo del libro: ");
		tipo = ingresarOpcionDeTipoDeLibroValida();
		stock = ingresarEntero("Stock del libro: ");

		Libro libro = new Libro(id, titulo, tipo, stock);
		if (biblioteca.agregarLibro(libro)) {
			mostrarPorPantalla("Libro agregado exitosamente");
		} else {
			mostrarPorPantalla("No se pudo agregar el libro");
		}

	}

	private static void prestarLibro(Biblioteca biblioteca) {
		Libro libro;
		Persona socio;
		Integer dniDelSocio;
		Integer idLibro;
		LocalDate fechaPrestamo;

		dniDelSocio = ingresarEntero("Ingrese dni del socio que solicita el prestamo: ");
		idLibro = ingresarEntero("Ingrese el ID del libro a retirar");

		socio = biblioteca.buscarSocioPorDni(dniDelSocio);
		libro = biblioteca.buscarLibroPorId(idLibro);

		if (socio != null && libro != null) {
			fechaPrestamo = ingresarFecha("Fecha en la que se realizo el prestamo: ");
			Prestamo prestamo = new Prestamo(socio, libro, fechaPrestamo);
			if (biblioteca.prestarLibro(prestamo)) {
				mostrarPorPantalla("Se realizo el prestamo exitosamente");
			} else {
				mostrarPorPantalla("No se pudo realizar el prestamo. Revisar penalizaciones del socio.");
			}
		} else {
			mostrarPorPantalla("Socio o libro no existente");
		}
	}

	private static void devolverLibro(Biblioteca biblioteca) {
		Libro libro;
		Persona socio;
		Integer dniDelSocio;
		String nombreDelLibro;

		dniDelSocio = ingresarEntero("Ingrese dni del socio que realiza la devolucion: ");
		nombreDelLibro = ingresarString("Ingrese nombre del libro que se devuelve: ");

		socio = biblioteca.buscarSocioPorDni(dniDelSocio);
		libro = biblioteca.buscarLibroPorNombre(nombreDelLibro);

		if (socio != null && libro != null) {

			Prestamo prestamo = biblioteca.buscarPrestamoPorSocioYLibro(socio, libro);

			if (prestamo != null) {
				if (biblioteca.devolverLibro(prestamo)) {
					mostrarPorPantalla("El libro fue devuelto");
				} else {
					mostrarPorPantalla("No se pudo realizar la devolucion");
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
		Integer dniDelSocio;

		dniDelSocio = ingresarEntero("Ingrese dni del socio del cual se desea saber su cuota: ");

		socio = biblioteca.buscarSocioPorDni(dniDelSocio);

		if (socio != null) {

			Double cuotaAPagar = biblioteca.getCuotaAPagarDeUnSocio(socio);
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