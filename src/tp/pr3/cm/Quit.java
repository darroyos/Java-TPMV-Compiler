package tp.pr3.cm;

import tp.pr3.mv.Engine;

public class Quit implements Command {
	
	@Override
	public void execute(Engine engine) {
		engine.executeQuit();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("QUIT"))
			return new Quit();
		else
			return null;
	}
	
	@Override
	public String textHelp() {
		return this.toString() + ": Cierra la aplicacion" + System.lineSeparator();
	}
	
	@Override
	public String toString() {
		return "QUIT";
	}

}
