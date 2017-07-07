package tp.pr3.cm;

import tp.pr3.mv.Engine;

public class Help implements Command {
	
	@Override
	public void execute(Engine engine) {
		engine.executeHelp();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("HELP"))
			return new Help();
		else
			return null;
	}
	
	@Override
	public String textHelp() {
		return this.toString() + ": Muestra esta ayuda" + System.lineSeparator();
	}
	
	@Override
	public String toString() {
		return "HELP";
	}

}
