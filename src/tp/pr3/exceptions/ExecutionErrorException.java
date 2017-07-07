package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class ExecutionErrorException extends Exception {
	public ExecutionErrorException() {
		super("Error de ejecucion");
	}
	
	public ExecutionErrorException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "Excepcion-errorEjecucion";
	}

}
