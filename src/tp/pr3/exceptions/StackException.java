package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class StackException extends ExecutionErrorException {
	public StackException() {
		super("Error en la pila de operandos");
	}
	
	public StackException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "Excepcion-pila";
	}
}
