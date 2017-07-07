package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class BadFormatByteCodeException extends Exception {
	public BadFormatByteCodeException() {
		super("El ByteCode no se ajusta a la sintaxis especificada");
	}
	
	public BadFormatByteCodeException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "Excepcion-formatoBytecode";
	}

}
