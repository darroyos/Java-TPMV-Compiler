package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class LexicalAnalysisException extends Exception {
	public LexicalAnalysisException() {
		super("Error por incorrecciones en el programa fuente");
	}
	
	public LexicalAnalysisException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "Excepcion-analisisLexico";
	}

}
