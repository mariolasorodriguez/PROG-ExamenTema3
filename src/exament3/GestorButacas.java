package exament3;

import java.util.Arrays;

import java.util.Scanner;

public class GestorButacas {
	
	public final static int TOTAL_BUTACAS = 60;
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {		
		// Definimos la matriz para las butacas
		final int FILAS = 6;
		final int COLUMNAS = 10;
		// DEFINIDAS COMO CONSTANTES
		char[][] sala = new char[FILAS][COLUMNAS];
		
		// Rellenamos el array entero a valores L (Libre)
		inicializarSala(sala);
		
		// Declaramos la opcion del menu
		int opcionIntroducida;
		
		do {
			// Mostramos el menu
			mostrarMenu();
			
			System.out.println("Introduce una opción: ");
			opcionIntroducida = reader.nextInt();
			reader.nextLine();
			
			opcionesMenu(opcionIntroducida, sala);
		} while (opcionIntroducida != 7);
		
	}
	/*
	 * Funcion para mostrar las opciones del menu
	 */
	public static void mostrarMenu() {
		System.out.println("TEATRO");
		System.out.println("1. Mostrar sala");
		System.out.println("2. Reservar asiento suelto");
		System.out.println("3. Reservar grupo en una fila");
		System.out.println("4. Confirmar Reservas (R -> O)");
		System.out.println("5. Cancelar Reservas (R -> L)");
		System.out.println("6. Estadísticas");
		System.out.println("7. Salir");
	}
	
	/*
	 * Metodo para elegir las opciones
	 */
	public static void opcionesMenu(int opcionIntroducida, char[][] sala) {
		switch (opcionIntroducida) {
			case 1:
				// Llamamos a la funcion pintarSala
				 pintarSala(sala);
				break;
				
			case 2:
				// Llamamos a la funcion para reservar asiento suelto
				reservarAsiento(sala, reader);
				break;
			
			case 3:
				// Llamamos a la funcion para reservar grupo en fila
				reservarGrupoEnFila(sala, 1, 2); // FALTA POR HACER
				break;
				
			case 4:
				// Llamamos a la funcion para confirmar las reservas
				confirmarReservas(sala);
				break;
				
			case 5:
				// Llamamos a la funcion para cancelar las reservas
				cancelarReservas(sala);
				break;
			case 6:
				// Llamamos a la funcion para mostrar estadisticas
				mostrarEstadisticas(sala);
				break;
			case 7:
				System.out.println("Hasta pronto, gracias por usarme.");
				break;
			default:
				System.out.println("Opcion no válida, pruebe de nuevo");
				break;
		}
	}

	public static void reservarAsiento(char[][] sala, Scanner reader) {
		// Pedimos al usuario la fila y la columna del asiento
		System.out.println("Introduce fila y columna, separados por coma (Ejemplo FILA,COLUMNA 2,6): ");
		String asientoIntroducido = null;
		asientoIntroducido = reader.nextLine();
		
		// Nos quedamos con las filas y columnas por separado
		int filaSuelta;
		int columnaSuelta;
		filaSuelta = asientoIntroducido.charAt(0);	
		columnaSuelta = asientoIntroducido.charAt(2);
		
		// Convertimos las filas y columnas a numeros enteros
		filaSuelta = filaSuelta - '0';
		columnaSuelta = columnaSuelta - '0';
		
		// Llamamos a la funcion esPosicionValida para comprobar que la fila y columna introducida por el usuario es correcta
		if (esPosicionValida(sala, filaSuelta, columnaSuelta)) {
		sala[filaSuelta - 1][columnaSuelta - 1] = 'R';
		System.out.println("Asiento reservado correctamente");
			} else {
		System.out.println("Asiento no válido, pruebe de nuevo");
		}
	}
	
	public static void reservarGrupoEnFila (char[][] sala, int filaGrupo, int numeroPersonas) {
		System.out.println("FUNCIÓN EN MANTENIMIENTO, LO SENTIMOS");
		
	}
	
	/*
	 * Metodo que comprueba que la fila y columna está dentro del rango
	 */
	public static boolean esPosicionValida(char[][] sala,int fila, int columna) {
		// Comprobamos que la fila y la columna introducida por el usuario es correcta
		if (fila < 1 || fila > sala.length || columna < 1 || columna > sala[0].length) {
			return false;
		}
		
		// Comprobamos que la butaca esta libre
		if (sala[fila - 1][columna - 1] != 'L') {
			return false;
		}
		
		return true;
	}
	
	/* 
	 * Metodo para contar el estado de la sala en L, R u O
	 */
	public static int contarEstado(char[][] sala, char estado) {
		// Bucle para contar el numero de butacas en el estado introducido por el usuario
		int contador = 0;
		int butacasUsadas = 0;
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == estado) {
					contador++;
					butacasUsadas++;
				}
			}
		}
		System.out.println(contador + " butacas");
		
		return butacasUsadas;
	}

	/*
	 * Metodo que inicializa la matriz de todas las butacas a libre (L)
	 */
	public static void inicializarSala(char[][] sala) {
		// Bucle para inicializar el array lleno del char L
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				sala[i][j] = 'L';
			}
		}
	}
	
	
	/*
	 * Metodo que pinta la sala
	 */
	public static void pintarSala(char[][] sala) {
		System.out.println("SALA DE BUTACAS:");
		// Pintamos la sala con un bucle con encabezado de columnas y filas
		System.out.println("0 1 2 3 4 5 6 7 8 9");
		for (int i = 0; i < sala.length; i++) {
			System.out.print((i + 1) + "  ");
			for (int j = 0; j < sala[i].length; j++) {
				System.out.print(sala[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * Metodo para confirmar reservas R -> O
	 */
	public static void confirmarReservas(char[][] sala) {
		// Recorremos la matriz y cambiamos las R por O
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == 'R') {
					sala[i][j] = 'O';
				}
			}
		}
		System.out.println("¡Reservas confirmadas!");
	}
	
	/*
	 * Metodo para cancelar reservas
	 */
	public static void cancelarReservas(char[][] sala) {
		// Recorremos la matriz y cambiamos las R por L
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == 'R') {
					sala[i][j] = 'L';
				}
			}
		}
		System.out.println("Reservas canceladas");
		
	}
	
	/*
	 * Metodo para mostrar estadisticas
	 */
	public static void mostrarEstadisticas(char[][] sala) {
		// Numero de butacas ocupadas 'O', reservadas 'R' y libres 'L'
		System.out.println("Butacas ocupadas: ");
		contarEstado(sala,'O');
		System.out.println("Butacas reservadas: ");
		contarEstado(sala,'R');
		System.out.println("Butacas libres: ");
		contarEstado(sala,'L');

		// Porcentaje de ocupación real 'O' respecto al total
		int porcentajeOcupacion;
		int butacasOcupadas;
		butacasOcupadas = contarEstado(sala, 'O');
		
		porcentajeOcupacion = (butacasOcupadas / TOTAL_BUTACAS) * 100;
		System.out.println("Porcentaje de ocupación real: " + porcentajeOcupacion + "%");
		

	}
	
	
}
