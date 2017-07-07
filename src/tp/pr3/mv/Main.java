/*
 * Práctica 3: Compilador para la TPMV
 * 
 * *****************************************************************************************
 * DESCRIPCIÓN DE LA PRÁCTICA
 * 
 * En las prácticas anteriores hemos utilizado programas bytecode, directamente escritos
 * por el usuario, y ejecutados a través de una máquina virtual. En esta práctica vamos a
 * permitir que el usuario escriba programas en un lenguaje imperativo simple, de forma que
 * estos programas sean traducidos a programas bytecode que más tarde ejecutará la tpmv.
 * Los programas imperativos (a partir de ahora programas fuente) se cargarán de ficheros
 * de texto y serán secuencias de instrucciones.
 * *****************************************************************************************
 * 
 * @author David Arroyo Segovia
 */

package tp.pr3.mv;

public class Main {

	public static void main(String[] args) {
		
		Engine e = new Engine();
		e.start();
	}

}
