package tp.pr3.cm;

/**
 * Tiene un único método public static Command parse(String line), que
 * se encarga de analizar el parámetro line y generar, a partir de él, el correspondiente
 * comando. Si line no se corresponde con la sintáxis de ningún comando entonces el
 * método devuelve null.
 *
 */
public class CommandParser {
	
	private final static Command[] commands = 
		{ new Help(), new Quit(), new Compile(), new Load(), new Run(), new Replace() };
	
	public static Command parse(String line) {
		String[] tokens;
		Command command;
		
		// Quitar blancos y dividir en tokens
		line = line.trim();
		tokens = line.split(" +");
		
		if (tokens.length == 0 || tokens.length > 2) 
			return null;
		
		else {
			for (Command c : commands) {
				command = c.parse(tokens);
				if (command != null)
					return command;
			}
			return null;
		}
		
	}
	
	public static void showHelp() {
		for (Command c : commands)
			System.out.print(c.textHelp());
	}

}
