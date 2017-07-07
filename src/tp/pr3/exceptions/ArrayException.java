package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class ArrayException extends Exception {
	public ArrayException() {
		super("Posición incorrecta del array");
	}
	
	public ArrayException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "Excepcion-array";
	}

}
