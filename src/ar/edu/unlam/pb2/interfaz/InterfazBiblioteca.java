package ar.edu.unlam.pb2.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb2.dominio.Biblioteca;
import ar.edu.unlam.pb2.dominio.Libro;
import ar.edu.unlam.pb2.dominio.Persona;
import ar.edu.unlam.pb2.dominio.Plan;
import ar.edu.unlam.pb2.dominio.PlanAdherente;
import ar.edu.unlam.pb2.dominio.PlanPleno;
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
				if (agregarSocio(biblioteca)) {
					mostrarPorPantalla("Socio nuevo agregado!");
				} else {
					mostrarPorPantalla("El socio no pudo ser agregado.");
				}
				break;

			case MOSTRAR_LISTA_SOCIOS:
				mostrarListaSocios(biblioteca);

			case AGREGAR_LIBRO:
				if (agregarLibro(biblioteca)) {
					mostrarPorPantalla("Libro agregado exitosamente!");
				} else {
					mostrarPorPantalla("No se pudo agregar el libro.");
				}

				break;

			case SALIR:

				break;

			}

		} while (opcionMenu != OpcionesMenu.SALIR);

	}

	private static void mostrarListaSocios(Biblioteca biblioteca) {
		for (Persona persona : biblioteca.getSocios()) {
			mostrarPorPantalla(
					"Nombre: " + persona.getNombre() + "; DNI: " + persona.getDni() + "; Plan: " + persona.getPlan());
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

	private static Boolean agregarLibro(Biblioteca biblioteca) {
		String tituloLibro = ingresarString("\nIngrese el titulo del Libro");
		TipoDeLibro tipo = ingresarOpcionDeTipoDeLibroValida();
		Integer stock = ingresarEntero("Ingrese el stock disponible");

		return biblioteca.agregarLibro(new Libro(tituloLibro, tipo, stock));

	}

	private static TipoDeLibro ingresarOpcionDeTipoDeLibroValida() {
		Integer opcionElegida;
		do {
			mostrarMenuTipoDeLibro();
			opcionElegida = ingresarEntero("");
			if (opcionElegida > 0 && opcionElegida <= TipoDeLibro.values().length) {
				return TipoDeLibro.values()[opcionElegida - 1];
			} else {
				return null;
			}
		} while (opcionElegida != null);

	}

	private static void mostrarMenuTipoDeLibro() {
		String tiposDeLibro = "\nIngrese el genero del libro\n";
		for (int i = 0; i < TipoDeLibro.values().length; i++) {
			tiposDeLibro += (i + 1) + "- " + TipoDeLibro.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(tiposDeLibro);
	}

	private static Boolean agregarSocio(Biblioteca biblioteca) {
		String nombre = ingresarString("Ingrese el nombre completo");
		Integer dni = ingresarEntero("Ingrese el nro de DNI");

		Plan plan = null;
		do {
			Integer tipoPlan = ingresarEntero(
					"Seleccione el tipo de plan: \n 1- Adherente ($2000/mes) \n 2- Pleno ($3000/mes)");
			if (tipoPlan == 1) {
				plan = new PlanAdherente(2000.00);
			} else if (tipoPlan == 2) {
				plan = new PlanPleno(3000.00);
			} else {
				mostrarPorPantalla("El plan ingresado no es correcto");
			}
		} while (plan == null);

		return biblioteca.agregarSocio(new Persona(nombre, dni, plan));
	}

}