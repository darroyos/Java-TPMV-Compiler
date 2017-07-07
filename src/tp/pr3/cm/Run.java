package tp.pr3.cm;

import tp.pr3.mv.Engine;
import tp.pr3.exceptions.ExecutionErrorException;
import tp.pr3.exceptions.ArrayException;

public class Run implements Command {
	
	@Override
	public void execute(Engine engine) throws ExecutionErrorException, ArrayException {
		engine.executeRun();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("RUN"))
			return new Run();
		else
			return null;
	}
	
	@Override
	public String textHelp() {
		return this.toString() + ": Ejecuta el programa" + System.lineSeparator();
	}
	
	@Override
	public String toString() {
		return "RUN";
	}

}
