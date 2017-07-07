package tp.pr3.cm;

import tp.pr3.mv.Engine;
import tp.pr3.exceptions.ArrayException;
import java.io.FileNotFoundException;

public class Load implements Command {
	private String fichName;
	
	public Load() {
		this.fichName = "FICH";
	}
	
	public Load(String fichName) {
		this.fichName = fichName;
	}
	
	@Override
	public void execute(Engine engine) throws FileNotFoundException, ArrayException {
		engine.load(this.fichName);
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD"))
			return new Load(s[1]); // s[1] fichero que se va a cargar
		else
			return null;
	}

	@Override
	public String textHelp() {
		return this.toString() + ": Carga el fichero de nombre FICH como programa fuente."
				+ " No realiza ningun tipo de comprobacion sintactica."
				+ System.lineSeparator();
	}
	
	@Override
	public String toString() {
		return "LOAD " + this.fichName;
	}

}
