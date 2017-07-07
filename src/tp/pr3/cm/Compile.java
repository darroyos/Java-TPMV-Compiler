package tp.pr3.cm;

import tp.pr3.exceptions.LexicalAnalysisException;
import tp.pr3.exceptions.ArrayException;
import tp.pr3.mv.Engine;

public class Compile implements Command {
	
	@Override
	public void execute(Engine engine) throws LexicalAnalysisException, ArrayException {
		engine.executeCompile();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("COMPILE"))
			return new Compile();
		else
			return null;
	}

	@Override
	public String textHelp() {
		return this.toString() + ": Realiza el analisis lexico del programa fuente,"
				+ " generando un nuevo programa parseado y,"
				+ " posteriormente a partir del programa parseado genera un programa bytecode"
				+ System.lineSeparator();
	}
	
	@Override
	public String toString() {
		return "COMPILE";
	}

}
