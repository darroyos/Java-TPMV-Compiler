package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class DivByZeroException extends ExecutionErrorException {
	public DivByZeroException() {
		super("Division entre 0 no permitida");
	}
	
	public DivByZeroException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "Excepcion-divisionXcero";
	}
}
